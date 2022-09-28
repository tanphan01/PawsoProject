package be.bf.pawso.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Shelter(
    @PrimaryKey
    var id : String,
    val nameShelter : String,
    val address : String,
)
