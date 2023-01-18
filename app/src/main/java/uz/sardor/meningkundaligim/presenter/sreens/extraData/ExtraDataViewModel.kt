package uz.sardor.meningkundaligim.presenter.sreens.extraData

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import uz.sardor.meningkundaligim.data.db.NoteDataBase
import uz.sardor.meningkundaligim.domain.model.ExtraDataEnity
import uz.sardor.meningkundaligim.domain.repository.ExtraDataDao

class ExtraDataViewModel : ViewModel() {

    val extraDao: ExtraDataDao = NoteDataBase.getInstance().getExtraDao()
    val getData: LiveData<List<ExtraDataEnity>> = extraDao.getExtraData(0)


    fun saveData(name: String, key: Int) {
        val list = ExtraDataEnity(0, name, key,false)
        extraDao.insert(list)
    }

    fun getDateAll(id: Int): LiveData<List<ExtraDataEnity>> {
        return extraDao.getExtraData(id)
    }

    fun delete(extraDataEnity: ExtraDataEnity){
        extraDao.delete(extraDataEnity)
    }

    fun updateCheckBox(name: String, key: Int) {
        val list = ExtraDataEnity(0, name, key,false)
        extraDao.update(list)
    }
    fun getDataD(id: Int) : ArrayList<ExtraDataEnity>{
        return extraDao.getExtraData(id) as ArrayList<ExtraDataEnity> /* = java.util.ArrayList<uz.sardor.meningkundaligim.domain.model.ExtraDataEnity> */
    }



    fun deleteId(id:Int){
        extraDao.deleteItem(id)
    }



}