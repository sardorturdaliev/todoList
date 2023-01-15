package uz.sardor.meningkundaligim.presenter.sreens.homescreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import uz.sardor.meningkundaligim.R
import uz.sardor.meningkundaligim.data.db.NoteDataBase
import uz.sardor.meningkundaligim.databinding.FragmentHomeBinding
import uz.sardor.meningkundaligim.domain.repository.NoteDao
import uz.sardor.meningkundaligim.presenter.sreens.homescreen.adapter.ToDoAdapter

class HomeFragment : Fragment() {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val toDoAdapter = ToDoAdapter()
    private val viewModel by viewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initRecycler()



        return binding.root
    }


    private fun initRecycler() {
        viewModel.getDataDB.observe(viewLifecycleOwner){
            toDoAdapter.submitList(it)
            //count Tasks
            binding.countAllTasks.text = it.count().toString()
        }
        binding.recyclerTodo.adapter = toDoAdapter
    }
}