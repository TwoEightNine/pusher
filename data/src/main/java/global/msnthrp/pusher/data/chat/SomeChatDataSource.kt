package global.msnthrp.pusher.data.chat

import androidx.room.*
import global.msnthrp.pusher.data.PusherDatabase
import global.msnthrp.pusher.domain.entity.User
import global.msnthrp.pusher.domain.interactor.chatlist.ChatDataSource

class SomeChatDataSource(
    pusherDatabase: PusherDatabase
) : ChatDataSource {

    private val userDao = pusherDatabase.userDao()

    override suspend fun addUser(user: User) {
        userDao.insertAll(user.toEntity())
    }

    override suspend fun getUsersList(): List<User> {
        return userDao.getAll().map { it.toDomain() }
    }

    private fun User.toEntity(): UserEntity {
        return UserEntity(
            name = this.name,
            token = this.token,
            photoUrl = this.photoUrl
        )
    }

    private fun UserEntity.toDomain(): User {
        return User(
            name = this.name,
            token = this.token,
            photoUrl = this.photoUrl
        )
    }

    @Entity(tableName = "user")
    data class UserEntity(
        @PrimaryKey(autoGenerate = true)
        val uid: Int = 0,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "token")
        val token: String,
        @ColumnInfo(name = "photo_url")
        val photoUrl: String?
    )

    @Dao
    interface UserDao {
        @Query("SELECT * FROM user")
        suspend fun getAll(): List<UserEntity>

        @Insert
        suspend fun insertAll(vararg users: UserEntity)
    }
}