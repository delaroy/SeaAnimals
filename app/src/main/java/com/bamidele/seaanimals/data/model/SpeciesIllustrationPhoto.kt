package com.bamidele.seaanimals.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class SpeciesIllustrationPhoto() : Parcelable {
    @SerializedName("src")
    @Expose
    var src: String? = null

    @SerializedName("alt")
    @Expose
    var alt: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    constructor(parcel: Parcel) : this() {
        src = parcel.readString()
        alt = parcel.readString()
        title = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(src)
        parcel.writeString(alt)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SpeciesIllustrationPhoto> {
        override fun createFromParcel(parcel: Parcel): SpeciesIllustrationPhoto {
            return SpeciesIllustrationPhoto(parcel)
        }

        override fun newArray(size: Int): Array<SpeciesIllustrationPhoto?> {
            return arrayOfNulls(size)
        }
    }
}