package com.example.learningroom

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getItemById(id: Int): User

    @Insert
    fun insertUser(user: User)

    @Query("DELETE FROM user WHERE id = :id")
    fun delete(id: Int)
}