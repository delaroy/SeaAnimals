package com.bamidele.seaanimals.data.model

import android.os.Parcel
import android.os.Parcelable
import com.bamidele.seaanimals.data.model.ImageGallery
import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter

import com.google.gson.annotations.SerializedName


class SeaAnimalsResponse() : Parcelable {


    @SerializedName("Habitat Impacts")
    @Expose
    var habitatImpacts: String? = null

    @SerializedName("Image Gallery")
    @JsonAdapter(ListTypeAdapterFactory::class)
    @Expose
    var imageGallery: ArrayList<ImageGallery>? = null

    @SerializedName("Species Illustration Photo")
    @Expose
    var speciesIllustrationPhoto: SpeciesIllustrationPhoto? = null

    @SerializedName("Species Name")
    @Expose
    var speciesName: String? = null

    constructor(parcel: Parcel) : this() {
        habitatImpacts = parcel.readString()
        speciesName = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(habitatImpacts)
        parcel.writeString(speciesName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SeaAnimalsResponse> {
        override fun createFromParcel(parcel: Parcel): SeaAnimalsResponse {
            return SeaAnimalsResponse(parcel)
        }

        override fun newArray(size: Int): Array<SeaAnimalsResponse?> {
            return arrayOfNulls(size)
        }
    }
}