package be.bf.pawso.models

import androidx.room.Embedded
import androidx.room.Relation
import java.io.Serializable

data class CatWithShelter(
    val cat : Cat,
    val shelter : Shelter
) : Serializable