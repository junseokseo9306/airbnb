package com.example.airbnb.data

import android.os.Parcel
import android.os.Parcelable
import com.example.airbnb.model.City

data class Image(
    val imageUrl: String,
)

data class CityInfo(
    val city: City,
    val travelTime: Int = 30,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(City::class.java.classLoader) ?: City(
            "",
            "",
            City.Coordinate(0.0, 0.0)
        ),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(city, flags)
        parcel.writeInt(travelTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CityInfo> {
        override fun createFromParcel(parcel: Parcel): CityInfo {
            return CityInfo(parcel)
        }

        override fun newArray(size: Int): Array<CityInfo?> {
            return arrayOfNulls(size)
        }
    }
}