package uz.sardor.meningkundaligim.presenter.sreens.editScreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.sardor.meningkundaligim.R
import uz.sardor.meningkundaligim.databinding.FragmentEditFragementBinding
import java.util.*


class EditFragement : Fragment() {
    private val binding by lazy { FragmentEditFragementBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<EditViewModel>()
     val counterTodo  =  MutableLiveData<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val calendar = Calendar.getInstance()
        val currentday = calendar.get(Calendar.DAY_OF_MONTH)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentYear = calendar.get(Calendar.YEAR)
        var counter = 0

        binding.sendData.setOnClickListener {
            if (validate(binding.edTitle.text.toString(),"$currentday ${currentMonth+1} $currentYear ")){
                viewModel.insert(
                    binding.edTitle.text.toString(),
                    "$currentday $currentMonth $currentYear "
                )
                setNull()
                counterTodo.value = counter++
            }else{
                Toast.makeText(context,"Please Enter ... ",Toast.LENGTH_SHORT).show()
            }
        }






        return binding.root
    }

    private fun validate(nameTodo: String, date: String) : Boolean{
         var result = true
        if (nameTodo.isBlank()){
            result = false
        }

        if (date.isBlank()){
            result = false
        }
        return result
    }

    private fun setNull(){
        binding.edTitle.text = null
    }
}