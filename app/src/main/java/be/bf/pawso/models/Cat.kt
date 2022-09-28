package be.bf.pawso.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Cat(
    @PrimaryKey
    val id : String,
    val name : String,
    val age : String,
    val gender : String,
    val color : String,
    val weight : String,
    val image : String,
    val adjective : String,
    val shelterId : String?,
    var userId: Int?
) : Serializable
