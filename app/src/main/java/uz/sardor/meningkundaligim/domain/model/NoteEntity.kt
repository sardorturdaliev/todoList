package uz.sardor.meningkundaligim.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name = "dateSet")
    val date:String

)