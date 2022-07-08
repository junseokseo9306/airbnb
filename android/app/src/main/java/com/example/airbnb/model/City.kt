package com.example.airbnb.model

import android.os.Parcel
import android.os.Parcelable

data class City(
    val cityName: String,
    val image: String,
    val currentCoordinate: Coordinate,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readParcelable(Coordinate::class.java.classLoader) ?: Coordinate(0.0, 0.0)
    ) {
    }

    data class Coordinate(
        val latitude: Double,
        val longitude: Double,
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readDouble(),
            parcel.readDouble()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeDouble(latitude)
            parcel.writeDouble(longitude)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Coordinate> {
            override fun createFromParcel(parcel: Parcel): Coordinate {
                return Coordinate(parcel)
            }

            override fun newArray(size: Int): Array<Coordinate?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cityName)
        parcel.writeString(image)
        parcel.writeParcelable(currentCoordinate, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<City> {
        override fun createFromParcel(parcel: Parcel): City {
            return City(parcel)
        }

        override fun newArray(size: Int): Array<City?> {
            return arrayOfNulls(size)
        }
    }
}