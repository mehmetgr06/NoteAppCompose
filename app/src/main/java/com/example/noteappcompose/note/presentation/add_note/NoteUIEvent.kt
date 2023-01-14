package com.example.noteappcompose.note.presentation.add_note

sealed class NoteUIEvent {
    data class ShowSnackbar(val message: String) : NoteUIEvent()
    object SaveNote : NoteUIEvent()
}
