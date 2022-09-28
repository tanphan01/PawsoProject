package be.bf.pawso.dal.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import be.bf.pawso.models.Message

@Dao
interface MessageDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(message: Message): Long

//    @Update
//    fun updateUsers(vararg users: User)
//
//    @Delete
//    fun deleteUsers(vararg user: User)
//
//    @Query("SELECT * FROM users")
//    fun findAll() : Flow<List<User>>

//    @Query("SELECT * FROM users WHERE listUserId = :id")
//    fun findByListUserId(id : Long) : Flow<List<User>>

//    @Query("SELECT * FROM users WHERE id = :id")
//    fun findOneById(id: Long): Flow<User>

}