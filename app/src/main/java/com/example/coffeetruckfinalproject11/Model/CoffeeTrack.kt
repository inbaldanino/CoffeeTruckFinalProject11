package com.example.coffeetruckfinalproject11.Model

import android.os.Parcel
import android.os.Parcelable

data class CoffeeTruck(
    val name: String,
    val location: String,
    val kosher: String,
    val openingHours: String,
    val photoUri: String?, // Use a URI or path for the image
    val recommendations: String,
    val tripSuggestions: String,
    val reviews: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(location)
        parcel.writeString(kosher)
        parcel.writeString(openingHours)
        parcel.writeString(photoUri)
        parcel.writeString(recommendations)
        parcel.writeString(tripSuggestions)
        parcel.writeString(reviews)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CoffeeTruck> {
        override fun createFromParcel(parcel: Parcel): CoffeeTruck {
            return CoffeeTruck(parcel)
        }

        override fun newArray(size: Int): Array<CoffeeTruck?> {
            return arrayOfNulls(size)
        }
    }
}
