package be.bf.pawso.dal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import be.bf.pawso.dal.daos.CatDAO
import be.bf.pawso.dal.daos.MessageDAO
import be.bf.pawso.dal.daos.ShelterDAO
import be.bf.pawso.dal.daos.UserDAO
import be.bf.pawso.models.*

@Database(entities = [Cat::class, User::class, Shelter::class, Message::class], version = 9, exportSchema = false)
abstract class DbHelper: RoomDatabase() {

    //--- DAO ---
    abstract fun cats(): CatDAO
    abstract fun users(): UserDAO
    abstract fun messages(): MessageDAO
    abstract fun shelters() : ShelterDAO

    companion object {
        const val DB_NAME = "room_database"
        private var instance: DbHelper? = null
        fun instance(context: Context): DbHelper {
            if (instance == null) {
                instance = Room
                    .databaseBuilder(context, DbHelper::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}
