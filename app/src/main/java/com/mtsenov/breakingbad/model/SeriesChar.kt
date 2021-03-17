package com.mtsenov.breakingbad.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class SeriesChar() : Parcelable {
    @SerializedName("char_id")
    var char_id: Int? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("birthday")
    var birthday: String? = null

    @SerializedName("occupation")
    var occupation: List<String>? = null

    @SerializedName("img")
    var img: String? = null

    @SerializedName("status")
    var status: String? = null

    @SerializedName("nickname")
    var nickname: String? = null

    @SerializedName("appearance")
    var appearance: List<Int>? = null

    @SerializedName("portrayed")
    var portrayed: String? = null

    @SerializedName("category")
    var category: String? = null

    @SerializedName("better_call_saul_appearance")
    var better_call_saul_appearance: List<Int>? = null

    val occupationList: MutableList<String> = mutableListOf()
    val appearanceList: MutableList<Int> = mutableListOf()
    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        parcel.readStringList(occupationList)
        img = parcel.readString()
        status = parcel.readString()
        nickname = parcel.readString()
        parcel.readList(appearanceList,null)
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(this.name)
        dest?.writeStringList(this.occupation)
        dest?.writeString(this.img)
        dest?.writeString(this.status)
        dest?.writeString(this.nickname)
        dest?.writeList(this.appearance)
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<SeriesChar> {
        override fun createFromParcel(parcel: Parcel): SeriesChar {
            return SeriesChar(parcel)
        }

        override fun newArray(size: Int): Array<SeriesChar?> {
            return arrayOfNulls(size)
        }
    }
}