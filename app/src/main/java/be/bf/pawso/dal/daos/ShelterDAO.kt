package be.bf.pawso.dal.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import be.bf.pawso.models.Shelter

@Dao
interface ShelterDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(shelter: Shelter): Long

//    @Update
//    fun updateUsers(vararg users: User)
//
//    @Delete
//    fun deleteUsers(vararg user: User)
//
//    @Transaction
//    @Query("SELECT * FROM Shelter")
//    fun findAllCatInShelter() : Flow<List<Cat>>

//    @Query("SELECT * FROM users WHERE listUserId = :id")
//    fun findByListUserId(id : Long) : Flow<List<User>>

//    @Query("SELECT * FROM users WHERE id = :id")
//    fun findOneById(id: Long): Flow<User>

}