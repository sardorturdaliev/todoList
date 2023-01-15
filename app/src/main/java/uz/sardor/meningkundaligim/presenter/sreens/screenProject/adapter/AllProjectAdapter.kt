package uz.sardor.meningkundaligim.presenter.sreens.screenProject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.sardor.meningkundaligim.databinding.CardItemsBinding
import uz.sardor.meningkundaligim.databinding.TodoCardBinding
import uz.sardor.meningkundaligim.domain.model.NoteEntity

class AllProjectAdapter : ListAdapter<NoteEntity, AllProjectAdapter.Holder>(Diff) {
    private var onExtraItemSelected: ((Int) -> Unit)? = null

    inner class Holder(val binding: CardItemsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onbind(noteEntity: NoteEntity) {
            binding.titleTodoTv.text = noteEntity.title

            itemView.setOnClickListener {
                onExtraItemSelected?.invoke(getItem(adapterPosition).id)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(
        CardItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.onbind(getItem(position))
    }


    object Diff : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }
    }


    fun setOnClickExtraDataListener(block: ((Int) -> Unit)) {
        onExtraItemSelected = block
    }
}