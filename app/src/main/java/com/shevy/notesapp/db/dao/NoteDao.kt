package com.shevy.notesapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shevy.notesapp.model.NoteModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table")
    fun getAllNotes(): LiveData<List<NoteModel>>

    // OnConflictStrategy.IGNORE нужен, чтобы при возникновении какой-то ошибки при записи в БД
    // программа это проигнорировала
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteModel: NoteModel)

    @Delete
    suspend fun delete(noteModel: NoteModel)
}