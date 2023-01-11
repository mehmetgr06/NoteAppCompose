package com.example.noteappcompose.note.data.repository

import com.example.noteappcompose.note.data.source.NoteDao
import com.example.noteappcompose.note.domain.model.Note
import com.example.noteappcompose.note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val noteDao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> = noteDao.getNotes()

    override suspend fun getNote(id: Int): Note? = noteDao.getNote(id)

    override suspend fun insertNote(note: Note) = noteDao.insertNote(note)

    override suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
}
