package uz.sardor.meningkundaligim.presenter.sreens.extraData

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.sardor.meningkundaligim.R
import uz.sardor.meningkundaligim.databinding.DialogCustoBinding
import uz.sardor.meningkundaligim.databinding.ExtradataCardBinding
import uz.sardor.meningkundaligim.databinding.FragmentExtraDataBinding
import uz.sardor.meningkundaligim.presenter.sreens.extraData.adapter.ExtraDataAdapter


class ExtraDataFragment : Fragment() {
    private var callback: OnBackPressedCallback? = null
    private val binding by lazy { FragmentExtraDataBinding.inflate(layoutInflater) }
    private val binding2 by lazy { ExtradataCardBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<ExtraDataViewModel>()
    private val extraDataAdapter = ExtraDataAdapter()
    private lateinit var sharedPreferences: SharedPreferences
    private var id: Int? = null
    private var isCheked: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pressed()
        getBundle()
        if (id != null) {
            getAllDataFromDb(id!!)
        }
        binding.floating.setOnClickListener {
            setDialog()
        }


        return binding.root
    }

    private fun getBundle() {
        id = arguments?.getInt("key")
        val title = arguments?.getString("keytitle")
        binding.edTitleGetDate.setText(title)
    }

    override fun onDestroy() {
        super.onDestroy()
        callback?.remove()
    }

    private fun pressed() {
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            callback as OnBackPressedCallback
        )
    }

    private fun getAllDataFromDb(id: Int) {
        viewModel.getDateAll(id).observe(viewLifecycleOwner) {
            extraDataAdapter.submitList(it)
            Log.d("rvdata", it.toString())
            binding.recyclerItem.adapter = extraDataAdapter
            binding.recyclerItem.layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun setDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())
        val view = DialogCustoBinding.inflate(layoutInflater)
        alertDialog.setView(view.root)
        val dialog = alertDialog.show()
        dialog.create()
        view.btnSave.setOnClickListener {
            id?.let { it ->
                viewModel.saveData(
                    view.edName.text.toString(),
                    key = it,
                )
            }
            if (id != null) {
                getAllDataFromDb(id!!)
            }
            dialog.dismiss()
        }
    }




    override fun onPause() {
        super.onPause()

    }





    override fun onResume() {
        super.onResume()

    }

}