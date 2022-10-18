package com.shevy.notesapp.screens.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.shevy.notesapp.APP
import com.shevy.notesapp.R
import com.shevy.notesapp.adapter.NoteAdapter
import com.shevy.notesapp.databinding.FragmentStartBinding
import com.shevy.notesapp.model.NoteModel

class StartFragment : Fragment() {
    lateinit var binding: FragmentStartBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[StartViewModel::class.java]
        viewModel.initDatabase()
        recyclerView = binding.rvNotes
        adapter = NoteAdapter()
        recyclerView.adapter = adapter
        viewModel.getAllNotes().observe(viewLifecycleOwner) { listNotes ->
            adapter.setList(listNotes.asReversed())  //asReversed - перевернуть заметки (отображаются с последней)
        }

        binding.btnAddNotes.setOnClickListener{
            APP.navController.navigate(R.id.action_startFragment_to_addNoteFragment)
        }
    }


    companion object{
        fun clickNote(noteModel: NoteModel){
            val bundle = Bundle() // чтобы передать объект
            bundle.putSerializable("note", noteModel) // передаём bundle объект
            APP.navController.navigate(R.id.action_startFragment_to_detailFragment, bundle)
        }
    }
}