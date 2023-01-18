package uz.sardor.meningkundaligim.domain.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.sardor.meningkundaligim.domain.model.ExtraDataEnity
import uz.sardor.meningkundaligim.domain.model.NoteEntity

@Dao
interface ExtraDataDao {
    @Insert
    fun insert(extraDataEnity: ExtraDataEnity)

    @Delete
    fun delete(extraDataEnity: ExtraDataEnity)

    @Update
    fun update(extraDataEnity: ExtraDataEnity)

    @Query("SELECT * FROM `table_extradata`  where `key` = :extradataKey")
    fun getExtraData(extradataKey : Int) : LiveData<List<ExtraDataEnity>>


    @Query("DELETE FROM  'table_extradata' where `id` = :id")
    fun deleteItem(id : Int)





}