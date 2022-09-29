package be.bf.pawso.models

import java.io.Serializable

data class CatWithShelter(
    val cat : Cat,
    val shelter : Shelter
) : Serializable