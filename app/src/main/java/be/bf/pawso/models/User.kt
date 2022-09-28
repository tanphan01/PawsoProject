package be.bf.pawso.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    indices = [Index(value = ["email"], unique = true)]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    var id : Int?,
    val name : String,
    val email : String,
    val password : String,
    val birthdate : Long,
    val description : String
) : Serializable
