package com.shevy.notesapp.db.repository

import androidx.lifecycle.LiveData
import com.shevy.notesapp.model.NoteModel

// интерфейс нужен чтобы просто перечислять кол-во методов, полей
interface NoteRepository {
    val allNotes: LiveData<List<NoteModel>>

    // параметр  onSuccess() необходим, чтобы сообщить что всё завершено успешно
    suspend fun insertNote(noteModel: NoteModel, onSuccess: () -> Unit)

    suspend fun deleteNote(noteModel: NoteModel, onSuccess: () -> Unit)
}