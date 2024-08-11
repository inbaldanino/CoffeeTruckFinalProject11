package com.example.coffeetruckfinalproject11.database

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coffeetruckfinalproject11.models.CoffeeTruck
import com.example.coffeetruckfinalproject11.models.User
import com.example.coffeetruckfinalproject11.models.dto.CoffeeTruckCreationForm
import com.example.coffeetruckfinalproject11.models.dto.UserRegistrationForm
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Database<UserRegistrationForm> private constructor() {
    private val storage = FirebaseStorage.getInstance() //upload images,files etc..
    private val auth = FirebaseStorage.getInstance()  // register,sign in,forgot pass,etc...
    private val firebase = FirebaseStorage.getInstance() // extra info, coffee trucks, database

    private var user: MutableLiveData<User?> = MutableLiveData<User?>(null)

    private var authStateListener: FirebaseAuth.AuthStateListener =
        FirebaseAuth.AuthStateListener { userState ->
            if (userState.currentUser != null) {
                snapShotListener?.remove()
                snapShotListener = fireStore.collection("users")
                    .document(userState.currentUser!!.uid)
                    .addSnapshotListener { value, error ->
                        val currentUser = value?.toObject(User::class.java)
                        currentUser?.let {
                            user.postValue(it)
                        }
                    }
            } else {
                snapShotListener?.remove()
            }
        }

    fun startListeningToUser() {
        auth.addAuthStateListener(authStateListener)
    }

    fun stopListeningToUser() {
        auth.removeAuthStateListener(authStateListener)
        snapShotListener?.remove()
    }


    companion object {
        private var instance: Database<Any?>? = null
        fun getInstance(): Database<Any?> {
            if (instance == null) {
                instance = Database()
            }
            return this.instance!!
        }
    }

    suspend fun addCoffeeTruck(coffeeTruckCreationForm: CoffeeTruckCreationForm): CoffeeTruck =
        withContext(Dispatchers.IO) {
            val deferredValue = CompletableDeferred<CoffeeTruck>()
            val imageUrl = uploadImage(
                coffeeTruckCreationForm.photoUri,
                "trucks/${coffeeTruckCreationForm.user}_${coffeeTruckCreationForm.name}"
            )

            val truck = CoffeeTruck(
                name = coffeeTruckCreationForm.name,
                location = coffeeTruckCreationForm.location,
                kosher = coffeeTruckCreationForm.kosher,
                openingHours = coffeeTruckCreationForm.openingHours,
                photoUri = imageUrl,
                recommendations = coffeeTruckCreationForm.recommendations,
                tripSuggestions = coffeeTruckCreationForm.tripSuggestions,
                user = coffeeTruckCreationForm.user
            )

            val newDoc = fireStore.collection("trucks")
                .document()
            truck.id = newDoc.id
            newDoc.set(truck)
                .addOnSuccessListener {
                    deferredValue.complete(truck)
                }
                .addOnFailureListener {
                    deferredValue.completeExceptionally(it)
                }
            deferredValue.await()
        }

    private suspend fun uploadImage(
        uri: Uri,
        path: String,
    ): String {
        val deferredValue = CompletableDeferred<String>()
        val ref = storage.getReference(path)
        ref.putFile(uri)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener { imageUri ->
                    deferredValue.complete(imageUri.toString())
                }
                    .addOnFailureListener {
                        deferredValue.completeExceptionally(it)
                    }
            }
            .addOnFailureListener {
                deferredValue.completeExceptionally(it)
            }
        return deferredValue.await()
    }

    fun logout() {
        auth.signOut()
    }


    suspend fun login(
        email: String,
        password: String,
    ):
            AuthResult = withContext(Dispatchers.IO) {
        val deferredValue = CompletableDeferred<AuthResult>()

        auth
            .signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                deferredValue.complete(it)
            }
            .addOnFailureListener {
                deferredValue.completeExceptionally(it)
            }
        deferredValue.await()
    }



    suspend fun register(
        form: UserRegistrationForm,
        coroutineScope: CoroutineScope,
    ): User = withContext(Dispatchers.IO) {

        val deferredValue = CompletableDeferred<User>()
        auth
            .createUserWithEmailAndPassword(
                form.email,
                form.password
            )
            .addOnSuccessListener { authResult ->
                coroutineScope.launch(Dispatchers.IO) {
                    val user = User(
                        id = authResult.user!!.uid,
                        name = form.name,
                        email = form.email,
                    )
                    form.image?.let { uri ->
                        try {
                            val imageUrl = uploadImage(uri, "userImages/${authResult.user!!.uid}")
                            user.image = imageUrl
                        } catch (e: Exception) {
                            coroutineScope.launch(Dispatchers.Main) {
                                deferredValue.completeExceptionally(e)
                            }
                        }
                    }

                    fireStore.collection("users")
                        .document(user.id)
                        .set(user)
                        .addOnSuccessListener {
                            coroutineScope.launch(Dispatchers.Main) {
                                deferredValue.complete(user)
                            }
                        }
                        .addOnFailureListener {
                            coroutineScope.launch(Dispatchers.Main) {
                                deferredValue.completeExceptionally(it)
                            }
                        }
                }
            }
            .addOnFailureListener {
                coroutineScope.launch(Dispatchers.Main) {
                    deferredValue.completeExceptionally(it)
                }
            }
        deferredValue.await()
    }

    fun getUser(): LiveData<User?> {
        return user
    }

}