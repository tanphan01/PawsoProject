package be.bf.pawso.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Shelter(
    @PrimaryKey
    var id : String,
    val nameShelter : String,
    val address : String,
) : Serializable
