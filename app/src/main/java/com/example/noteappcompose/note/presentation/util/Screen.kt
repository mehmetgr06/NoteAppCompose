package com.example.noteappcompose.note.presentation.util

sealed class Screen(route: String) {
    object NotesScreen: Screen("notes_screen")
    object AddNoteScreen: Screen("add_note_screen")
}
