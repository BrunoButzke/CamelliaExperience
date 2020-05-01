package com.example.esquentahackathon.jsonFiles

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Level(
    @Expose
    @SerializedName("beginner")
    var beginner: List<Questions>,
    @Expose
    @SerializedName("Intermediate")
    var Intermediate: List<Questions>,
    @Expose
    @SerializedName("Advanced")
    var Advanced: List<Questions>
): Parcelable

@Parcelize
class Questions(
    @Expose
    @SerializedName("question")
    var question: String,
    @Expose
    @SerializedName("respostas")
    var answers: List<String>,
    @Expose
    @SerializedName("correctIndex")
    var correctIndex: Int
): Parcelable