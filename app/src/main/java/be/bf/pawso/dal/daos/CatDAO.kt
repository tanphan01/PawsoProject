package be.bf.pawso.dal.daos

import androidx.room.*
import be.bf.pawso.models.Cat
import be.bf.pawso.models.CatWithShelter
import be.bf.pawso.models.Shelter
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(cat: Cat): Long

    @Query("SELECT * FROM Cat " +
            "JOIN Shelter ON Cat.shelterId = Shelter.id " +
            "WHERE Cat.userId = :userId")
    fun getCatsByUser(userId : Int) : Flow<Map<Cat, Shelter>>

//    @Update
//    fun updateCats(vararg cats: Cat)
//
//    @Delete
//    fun deleteCats(vararg cats: Cat)
//
//    @Transaction
//    @Query("SELECT * FROM Cat")
//    fun findAllMessage() : Flow<List<Cat>>

//    @Query("SELECT * FROM cats WHERE listCatId = :id")
//    fun findByListCatId(id : Long) : Flow<List<Cat>>

//    @Query("SELECT * FROM cats WHERE id = :id")
//    fun findOneById(id: Long): Flow<Cat>

}