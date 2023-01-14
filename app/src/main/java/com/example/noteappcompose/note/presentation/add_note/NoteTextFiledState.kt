package com.example.noteappcompose.note.presentation.add_note

data class NoteTextFiledState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
)
