package com.rayko.postalmaint.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CallDao {
    @Query("SELECT * FROM calls ORDER BY roomId DESC")
    fun getAllFlow(): Flow<List<CallEntity>>

    @Query("SELECT * FROM calls WHERE roomId = :id")
    fun getCall(id: Long): Flow<CallEntity?>

    @Query("SELECT * FROM calls ORDER BY roomId DESC LIMIT 1")
    fun getLastCall(): CallEntity?

    @Query("SELECT * FROM calls WHERE equip_type = :equipType AND equip_num = :equipID ORDER BY roomId DESC")
    fun getByEquipment(equipType: String, equipID: String): Flow<List<CallEntity>>

    @Insert
    suspend fun insert(call: CallEntity)

    @Update
    suspend fun update(call: CallEntity?)

    @Delete
    suspend fun delete(call: CallEntity?)

    @Query("DELETE FROM calls")
    suspend fun deleteAll()
}