package uz.sardor.meningkundaligim.domain.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.sardor.meningkundaligim.domain.model.NoteEntity

@Dao()
interface NoteDao {
    @Insert()
    fun insert(noteEntity: NoteEntity)


    @Delete()
    fun delete(noteEntity: NoteEntity)


    @Update()
    fun update(noteEntity: NoteEntity)


    @Query("select * from note_table")
    fun getNote() : LiveData<List<NoteEntity>>

}