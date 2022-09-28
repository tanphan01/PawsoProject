package be.bf.pawso.models

import androidx.room.Embedded
import androidx.room.Relation

data class CatWithMessage (
    @Embedded val cat : Cat,
    @Relation(
        parentColumn = "id",
        entityColumn = "catId"
    )
    val messages : List<Message>
)