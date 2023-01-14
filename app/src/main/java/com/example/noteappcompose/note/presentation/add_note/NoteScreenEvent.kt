package com.example.noteappcompose.note.presentation.add_note

import androidx.compose.ui.focus.FocusState

sealed class NoteScreenEvent {
    data class EnteredTitle(val value: String) : NoteScreenEvent()
    data class ChangeTitleFocus(val focusState: FocusState): NoteScreenEvent()
    data class EnteredContent(val value: String) : NoteScreenEvent()
    data class ChangeContentFocus(val focusState: FocusState) : NoteScreenEvent()
    data class ChangeColor(val color: Int) : NoteScreenEvent()
    object SaveNote : NoteScreenEvent()
}
