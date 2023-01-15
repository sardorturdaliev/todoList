package uz.sardor.meningkundaligim.presenter.sreens.extraData

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import uz.sardor.meningkundaligim.data.db.NoteDataBase
import uz.sardor.meningkundaligim.domain.model.ExtraDataEnity
import uz.sardor.meningkundaligim.domain.repository.ExtraDataDao

class ExtraDataViewModel : ViewModel() {

    val extraDao : ExtraDataDao = NoteDataBase.getInstance().getExtraDao()
    val getData : LiveData<List<ExtraDataEnity>> = extraDao.getExtraData(0)



    fun saveData(name:String,key:Int){
        val list = ExtraDataEnity(0,name,key)
        extraDao.insert(list)
    }

    fun  getDateAll(id : Int) : LiveData<List<ExtraDataEnity>>{
        return extraDao.getExtraData(id)
    }

//    fun updateCheckBox(name:String,key:Int,cheker:Boolean){
//        val list = ExtraDataEnity(0,name,cheker,key)
//        extraDao.update(list)
//    }





}