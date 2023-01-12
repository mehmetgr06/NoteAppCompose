package com.example.noteappcompose.note.presentation.notes

import com.example.noteappcompose.note.domain.model.Note
import com.example.noteappcompose.note.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSection : NotesEvent()
}
