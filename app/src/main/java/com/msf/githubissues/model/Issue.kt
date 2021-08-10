package com.msf.githubissues.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.msf.githubissues.util.ConvertUtil

const val OPEN = "open"

@Entity(tableName = "issues")
class Issue() : Parcelable{

    @SerializedName("id")
    @PrimaryKey
    @NonNull
    var id: Long? = null

    @SerializedName("html_url")
    var htmlUrl: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("body")
    var body: String? = null

    @SerializedName("state")
    var state: String? = null

    @SerializedName("number")
    var number: Long? = null

    @SerializedName("user")
    @Embedded
    lateinit var user: User

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Long::class.java.classLoader) as? Long
        htmlUrl = parcel.readString()
        title = parcel.readString()
        createdAt = parcel.readString()
        body = parcel.readString()
        state = parcel.readString()
    }

    fun dateFormated():String{
        return ConvertUtil.getDateFormatted(createdAt!!)
    }

    fun isOpen():Boolean{
        return state.equals(OPEN)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(htmlUrl)
        parcel.writeString(title)
        parcel.writeString(createdAt)
        parcel.writeString(body)
        parcel.writeString(state)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Issue> {
        override fun createFromParcel(parcel: Parcel): Issue {
            return Issue(parcel)
        }

        override fun newArray(size: Int): Array<Issue?> {
            return arrayOfNulls(size)
        }
    }
}