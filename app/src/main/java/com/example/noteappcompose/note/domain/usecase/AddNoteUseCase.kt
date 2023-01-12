package com.example.noteappcompose.note.domain.usecase

import com.example.noteappcompose.note.domain.model.InvalidNoteException
import com.example.noteappcompose.note.domain.model.Note
import com.example.noteappcompose.note.domain.repository.NoteRepository

class AddNoteUseCase(
    private val noteRepository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("Title cannot be empty")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("Content cannot be empty")
        }
        noteRepository.insertNote(note)
    }
}
