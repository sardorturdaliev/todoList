package uz.sardor.meningkundaligim.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.sardor.meningkundaligim.domain.model.ExtraDataEnity
import uz.sardor.meningkundaligim.domain.repository.NoteDao
import uz.sardor.meningkundaligim.domain.model.NoteEntity
import uz.sardor.meningkundaligim.domain.repository.ExtraDataDao

@Database(entities = [NoteEntity::class,ExtraDataEnity::class], version = 2)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
    abstract  fun getExtraDao() : ExtraDataDao

    companion object {
        private var instance: NoteDataBase? = null
        fun getInstance(): NoteDataBase = instance!!
        fun init(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, NoteDataBase::class.java, "note_app1")
                    .allowMainThreadQueries().build()
            }
        }
    }



}