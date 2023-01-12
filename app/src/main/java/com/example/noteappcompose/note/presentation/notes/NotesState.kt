package com.example.noteappcompose.note.presentation.notes

import com.example.noteappcompose.note.domain.model.Note
import com.example.noteappcompose.note.domain.util.NoteOrder
import com.example.noteappcompose.note.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
