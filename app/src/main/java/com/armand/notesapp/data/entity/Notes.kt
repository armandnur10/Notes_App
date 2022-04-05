package com.armand.notesapp.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
@Entity
@Parcelize
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title: String,
    var priority: Priority,
    var description: String,
    var date: String
):Parcelable
