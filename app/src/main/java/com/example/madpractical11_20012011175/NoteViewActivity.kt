package com.example.madpractical11_20012011175

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import com.example.madpractical11_20012011175.databinding.ActivityNoteViewBinding

class NoteViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteViewBinding
    private lateinit var note:Note
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        note = intent.getSerializableExtra("Object") as Note
        binding = ActivityNoteViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(note){
            binding.noteTitle.text = this.title
            binding.noteSubtitle.text = this.subTitle
            binding.noteDescription.text = this.description
            binding.noteDate.text = this.modifiedTime
            this.calcReminder()
            if(this.isReminderEnable)
            {
                binding.noteReminder.visibility = View.VISIBLE
                binding.noteReminder.text = this.getReminderText()
            }
            else
                binding.noteReminder.visibility = View.GONE
        }
    }
}