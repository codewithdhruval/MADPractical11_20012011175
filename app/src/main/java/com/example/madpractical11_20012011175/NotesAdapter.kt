package com.example.madpractical11_20012011175

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madpractical11_20012011175.databinding.NoteViewDesignBinding
import java.io.Serializable

class NotesAdapter (private val context: Context, private val array:ArrayList<Note>):
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    inner class NotesViewHolder(val binding: NoteViewDesignBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NoteViewDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        with(holder){
            with(array[position]){
                binding.noteTitle.text = this.title
                binding.noteSubTitle.text = this.subTitle
                binding.noteContent.text = this.description
                binding.noteReminder.text = this.modifiedTime
                val obj = this as Serializable
                this.calcReminder()
                if(this.isReminderEnable)
                {
                    binding.noteReminder.visibility = View.VISIBLE
                    binding.noteReminder.text = this.getReminderText()
                }
                else
                    binding.noteReminder.visibility = View.GONE
                binding.deleteNote.setOnClickListener{
                    (context as MainActivity).deleteNote(position)
                }
                binding.cardView.setOnClickListener {
                    Intent(this@NotesAdapter.context, NoteViewActivity::class.java).apply {
                        putExtra("Object",obj)
                        this@NotesAdapter.context.startActivity(this)
                    }
                }
                binding.editNote.setOnClickListener {
                    (context as MainActivity).showAlertDialog(
                        NoteMode.edit,
                        "Edit Note",
                        this,
                        position,
                        this@NotesAdapter
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return array.size
    }
}