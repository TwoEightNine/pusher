package global.msnthrp.pusher.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import global.msnthrp.pusher.data.chat.SomeChatDataSource

@Database(
    entities = [
        SomeChatDataSource.UserEntity::class
    ],
    version = 1
)
abstract class PusherDatabase : RoomDatabase() {
    abstract fun userDao(): SomeChatDataSource.UserDao

    companion object {
        fun getDatabase(applicationContext: Context): PusherDatabase {
            return Room.databaseBuilder(
                applicationContext,
                PusherDatabase::class.java,
                "pusher-db"
            ).build()
        }
    }

}