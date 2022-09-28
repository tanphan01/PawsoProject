package be.bf.pawso.dal.daos

import androidx.room.*
import be.bf.pawso.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(user: User): Long

    @Query("SELECT * FROM User WHERE email = :email ")
    fun getUserByEmail(email : String): Flow<User?>

    @Query("SELECT * FROM User WHERE id = :id")
    fun getUserById(id : Int) : Flow<User?>

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