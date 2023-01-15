package uz.sardor.meningkundaligim.presenter.sreens.screenProject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.sardor.meningkundaligim.R
import uz.sardor.meningkundaligim.databinding.CardItemsBinding
import uz.sardor.meningkundaligim.databinding.FragmentAllProjectBinding
import uz.sardor.meningkundaligim.presenter.sreens.extraData.ExtraDataFragment
import uz.sardor.meningkundaligim.presenter.sreens.screenProject.adapter.AllProjectAdapter

class AllProjectFragment : Fragment() {
    private val binding by lazy { FragmentAllProjectBinding.inflate(layoutInflater) }
    private val binding2 by lazy { CardItemsBinding.inflate(layoutInflater) }
    private val viewMode by viewModels<AllProjectViewModel>()
    private val allProjectAdapter = AllProjectAdapter()
    private lateinit var titile: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewMode.getData.observe(viewLifecycleOwner) {
            allProjectAdapter.submitList(it)
            Log.d("YYY", it.count().toString())
            binding.recyclerItem.adapter = allProjectAdapter
            binding.recyclerItem.layoutManager = LinearLayoutManager(context)
            binding.tvAll.text = it.count().toString()


            allProjectAdapter.setOnClickExtraDataListener { item ->
                val fm = ExtraDataFragment()
                val bundle = bundleOf("key" to item,"keytitle" to it[item-1].title)
                Log.d("idfind", bundle.toString())


             findNavController().navigate(R.id.extraDataFragment,bundle)

            }
        }










        binding.addItem.setOnClickListener {
            findNavController().navigate(R.id.editFragement)
        }


        return binding.root
    }
}