package be.bf.pawso.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
class Message(
    @PrimaryKey
    val id : String,
    val message : String,
    val catId: Int)
