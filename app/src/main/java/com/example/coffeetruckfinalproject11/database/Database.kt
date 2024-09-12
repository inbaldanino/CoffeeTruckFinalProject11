package com.example.coffeetruckfinalproject11.database

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coffeetruckfinalproject11.Resource
import com.example.coffeetruckfinalproject11.model.CoffeeTruck
import com.example.coffeetruckfinalproject11.models.User
import com.example.coffeetruckfinalproject11.model.dto.CoffeeTruckCreationForm
import com.example.coffeetruckfinalproject11.model.dto.UserRegistrationForm
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

class Database private constructor() {
    private val storage = FirebaseStorage.getInstance() // Upload images, files, etc.
    private val auth = FirebaseAuth.getInstance()  // Register, sign in, forgot pass, etc.
    private val fireStore = FirebaseFirestore.getInstance() // Extra info, coffee trucks, database

    private var user: MutableLiveData<User?> = MutableLiveData<User?>(null)

    private var snapShotListener: ListenerRegistration? = null

    private var authStateListener: FirebaseAuth.AuthStateListener =
        FirebaseAuth.AuthStateListener { userState ->
            if (userState.currentUser != null) {
                snapShotListener?.remove()
                snapShotListener = fireStore.collection("users")
                    .document(userState.currentUser!!.uid)
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            return@addSnapshotListener
                        }

                        val currentUser = value?.toObject(User::class.java)
                        currentUser?.let {
                            user.postValue(Resource(false, it))
                        }
                    }
            } else {
                user.postValue(Resource(false, null))
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
        private var instance: Database? = null

        fun getInstance(): Database {
            if (instance == null) {
                instance = Database()
            }
            return instance!!
        }
    }

    suspend fun saveCoffeeTruck(truck:CoffeeTruck):Void? = withContext(Dispatchers.IO) {
        //for async operations
        val value = CompletableDeferred<Void?>()
        fireStore.collection("trucks")
            .document(truck.id)
            .set(truck)
            .addOnSuccessListener {
                value.complete(null)
            }
            .addOnFailureListener{
                value.completeExceptionally(it)
            }
        value.await()
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
    ): AuthResult = withContext(Dispatchers.IO) {
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
                            deferredValue.completeExceptionally(e)
                        }
                    }

                    fireStore.collection("users")
                        .document(user.id)
                        .set(user)
                        .addOnSuccessListener {
                            deferredValue.complete(user)
                        }
                        .addOnFailureListener {
                            deferredValue.completeExceptionally(it)
                        }
                }
            }
            .addOnFailureListener {
                deferredValue.completeExceptionally(it)
            }
        deferredValue.await()
    }

    fun getUser(): LiveData<User?> {
        return user
    }
}
