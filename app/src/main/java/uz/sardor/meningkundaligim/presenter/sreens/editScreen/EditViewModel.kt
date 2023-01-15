package uz.sardor.meningkundaligim.presenter.sreens.editScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.sardor.meningkundaligim.data.db.NoteDataBase
import uz.sardor.meningkundaligim.domain.model.NoteEntity
import uz.sardor.meningkundaligim.domain.repository.NoteDao

class EditViewModel : ViewModel() {
    private val noteDao:NoteDao = NoteDataBase.getInstance().getNoteDao()



    fun insert(title:String,date:String){
        val noteEntity = NoteEntity(0,title,date)
        noteDao.insert(noteEntity)
    }



    fun getData() : LiveData<List<NoteEntity>> {
        return noteDao.getNote()
    }



}