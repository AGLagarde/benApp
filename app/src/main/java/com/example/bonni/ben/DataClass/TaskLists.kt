package com.example.bonni.ben.DataClass
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.io.Reader


data class TaskLists(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("tasks")
    var tasks: List<Task>
){
  class Deserializer : ResponseDeserializable<TaskLists> {
    override fun deserialize(reader: Reader) = Gson().fromJson(reader, TaskLists::class.java)
  }

  class ListDeserializer : ResponseDeserializable<List<TaskLists>> {
    override fun deserialize(reader: Reader): List<TaskLists> {
      val type = object : TypeToken<List<TaskLists>>() {}.type
      return Gson().fromJson(reader, type)
    }
  }
}


