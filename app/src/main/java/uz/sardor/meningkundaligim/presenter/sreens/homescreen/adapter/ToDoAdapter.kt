package uz.sardor.meningkundaligim.presenter.sreens.homescreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.sardor.meningkundaligim.databinding.TodoCardBinding
import uz.sardor.meningkundaligim.domain.model.NoteEntity

class ToDoAdapter : ListAdapter<NoteEntity, ToDoAdapter.Holder>(Diff) {

    class Holder(val binding: TodoCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onbind(noteEntity: NoteEntity) {
            binding.tvAboutTask.text = noteEntity.title
            binding.tvDate.text = noteEntity.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(
        TodoCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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


}