package uz.sardor.meningkundaligim.presenter.sreens.extraData

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.sardor.meningkundaligim.data.db.NoteDataBase
import uz.sardor.meningkundaligim.databinding.DialogCustoBinding
import uz.sardor.meningkundaligim.databinding.ExtradataCardBinding
import uz.sardor.meningkundaligim.databinding.FragmentExtraDataBinding
import uz.sardor.meningkundaligim.domain.model.ExtraDataEnity
import uz.sardor.meningkundaligim.domain.repository.ExtraDataDao
import uz.sardor.meningkundaligim.presenter.sreens.extraData.adapter.ExtraDataAdapter
import uz.sardor.meningkundaligim.presenter.sreens.extraData.adapter.TodoListener


class ExtraDataFragment : Fragment() {
    private var callback: OnBackPressedCallback? = null
    private val binding by lazy { FragmentExtraDataBinding.inflate(layoutInflater) }
    private val binding2 by lazy { ExtradataCardBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<ExtraDataViewModel>()
    private val extraDataAdapter = ExtraDataAdapter()
    private var chanePos: Int? = null
    private var list = ArrayList<ExtraDataEnity>()
    private val extraDao: ExtraDataDao = NoteDataBase.getInstance().getExtraDao()
    var id: Int? = null
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


        itemTouch()

        extraDataAdapter.setListener(object : TodoListener {
            override fun checkBoxCliked(extraDataEnity: ExtraDataEnity, isCheked: Boolean) {
                extraDataEnity.isCheked = isCheked
                extraDao.update(extraDataEnity)
            }
        })

        return binding.root
    }

    private fun getBundle() {
        id = arguments?.getInt("key")
        val title = arguments?.getString("keytitle")
        binding.edTitleGetDate.setText(title)
        Log.d("PPP", id.toString())
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
            list =
                it as ArrayList<ExtraDataEnity> /* = java.util.ArrayList<uz.sardor.meningkundaligim.domain.model.ExtraDataEnity> */


            extraDataAdapter.submitList(list)
            Log.d("rvdata", list.toString())
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
                viewModel.saveData(view.edName.text.toString(), key = it)
            }
            if (id != null) {
                getAllDataFromDb(id!!)
            }
            dialog.dismiss()
        }
    }


    private fun itemTouch() {
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.RIGHT) {
                    viewModel.deleteId(list[viewHolder.adapterPosition].id)
                }
                extraDataAdapter.notifyDataSetChanged()
            }
        }).attachToRecyclerView(binding.recyclerItem)
    }


    override fun onPause() {
        super.onPause()

    }


    override fun onResume() {
        super.onResume()
        chanePos?.apply {
            extraDataAdapter.notifyItemChanged(this)
        }
    }

}