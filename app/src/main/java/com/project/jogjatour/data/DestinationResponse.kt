package com.project.jogjatour.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DestinationResponse (

    @SerializedName("data")
    @Expose
    var wisata:  MutableList<Destination>,
    var detaillWisata:  Destination?
)

data class Destination (

    @SerializedName("nama_pariwisata")
    @Expose
    var namaPariwisata: String?,
    @SerializedName("alamat_pariwisata")
    @Expose
    var alamatPariwisata: String?,
    @SerializedName("detail_pariwisata")
    @Expose
    var detailPariwisata: String? ,
    @SerializedName("gambar_pariwisata")
    @Expose
    var gambarPariwisata: String?) : Serializable
//    : Parcelable {
//
//
//    constructor(parcel: Parcel) : this(
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString()
//    )
//
//    override fun writeToParcel(dest: Parcel?, flags: Int) {
//        dest?.writeString(namaPariwisata)
//        dest?.writeString(alamatPariwisata)
//        dest?.writeString(detailPariwisata)
//        dest?.writeString(gambarPariwisata)
//    }
//
//    override fun describeContents() = 0
//
//    companion object CREATOR : Parcelable.Creator<Destination> {
//        override fun createFromParcel(parcel: Parcel): Destination {
//            return Destination(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Destination?> {
//            return arrayOfNulls(size)
//        }
//    }
//}