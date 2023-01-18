package uz.sardor.meningkundaligim.presenter.sreens.extraData.adapter

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.sardor.meningkundaligim.databinding.ExtradataCardBinding
import uz.sardor.meningkundaligim.domain.model.ExtraDataEnity
import uz.sardor.meningkundaligim.domain.model.NoteEntity

class ExtraDataAdapter : ListAdapter<ExtraDataEnity, ExtraDataAdapter.Holder>(Diff) {
    private var click: ((ExtraDataEnity) -> Unit)? = null
    private var listener: TodoListener? = null

    inner class Holder(val binding: ExtradataCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onbind(entity: ExtraDataEnity) = with(binding) {
            val pos = getItem(adapterPosition)
            tvExtraData.text = entity.extradata
            checkbox.isChecked = entity.isCheked
            tvExtraData.paint.isStrikeThruText = entity.isCheked

            checkbox.setOnCheckedChangeListener { check, isCheked ->
                if (check.isPressed) {
                    listener?.checkBoxCliked(getItem(adapterPosition), isCheked)
                }
            }



        }
    }

    object Diff : DiffUtil.ItemCallback<ExtraDataEnity>() {
        override fun areItemsTheSame(oldItem: ExtraDataEnity, newItem: ExtraDataEnity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ExtraDataEnity, newItem: ExtraDataEnity): Boolean {
            return oldItem == newItem
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(
        ExtradataCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.onbind(getItem(position))
    }


    fun setChekBox(block: (ExtraDataEnity) -> Unit) {
        click = block
    }


    fun setListener(listener: TodoListener) {
        this.listener = listener
    }


}