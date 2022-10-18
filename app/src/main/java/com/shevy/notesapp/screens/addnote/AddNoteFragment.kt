package com.shevy.notesapp.screens.addnote

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.shevy.notesapp.APP
import com.shevy.notesapp.R
import com.shevy.notesapp.databinding.FragmentAddNoteBinding
import com.shevy.notesapp.model.NoteModel

class AddNoteFragment : Fragment() {
    lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[AddNoteViewModel::class.java]

        binding.btnAdd.setOnClickListener {
            val title = binding.etAddTitle.text.toString()
            val description = binding.etAddDescription.text.toString()

            viewModel.insert(NoteModel(title = title, description = description)) {}

            Log.d(
                "AddNoteFragmentDB",
                "Result = ${viewModel.insert(NoteModel(title = title, description = description)){}}"
            )
            APP.navController.navigate(R.id.action_addNoteFragment_to_startFragment)

        }

        binding.btnBack.setOnClickListener {
            APP.navController.navigate(R.id.action_addNoteFragment_to_startFragment)
        }


    }

}