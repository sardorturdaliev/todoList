package uz.sardor.meningkundaligim.app

import android.app.Application
import uz.sardor.meningkundaligim.data.db.NoteDataBase

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        NoteDataBase.init(this)
    }
}