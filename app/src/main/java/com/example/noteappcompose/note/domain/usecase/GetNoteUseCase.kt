package com.example.noteappcompose.note.domain.usecase

import com.example.noteappcompose.note.domain.model.Note
import com.example.noteappcompose.note.domain.repository.NoteRepository

class GetNoteUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note? = repository.getNote(id)
}
