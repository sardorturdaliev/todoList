package uz.sardor.meningkundaligim.presenter.sreens.screenProject

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import uz.sardor.meningkundaligim.data.db.NoteDataBase
import uz.sardor.meningkundaligim.domain.model.NoteEntity
import uz.sardor.meningkundaligim.domain.repository.NoteDao

class AllProjectViewModel  : ViewModel(){
    val noteDao : NoteDao =  NoteDataBase.getInstance().getNoteDao()

    val getData : LiveData<List<NoteEntity>> = noteDao.getNote()




}