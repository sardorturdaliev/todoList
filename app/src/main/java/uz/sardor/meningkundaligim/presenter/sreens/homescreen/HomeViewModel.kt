package uz.sardor.meningkundaligim.presenter.sreens.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.sardor.meningkundaligim.data.db.NoteDataBase
import uz.sardor.meningkundaligim.domain.model.NoteEntity
import uz.sardor.meningkundaligim.domain.repository.NoteDao
import uz.sardor.meningkundaligim.presenter.sreens.editScreen.EditFragement

class HomeViewModel : ViewModel() {
    private val noteDao: NoteDao = NoteDataBase.getInstance().getNoteDao()
    val getDataDB : LiveData<List<NoteEntity>> = noteDao.getNote()






}