package com.example.bonni.ben.DataClass

import com.google.gson.annotations.SerializedName

data class Task(
  @SerializedName("done")
  var done: Boolean,
  @SerializedName("id")
  var id: Int,
  @SerializedName("name")
  var name: String
)
