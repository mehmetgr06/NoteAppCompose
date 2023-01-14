package com.example.noteappcompose.note.presentation.add_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappcompose.note.domain.model.InvalidNoteException
import com.example.noteappcompose.note.domain.model.Note
import com.example.noteappcompose.note.domain.usecase.AddNoteUseCase
import com.example.noteappcompose.note.domain.usecase.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val getNoteUseCase: GetNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitleState = mutableStateOf(NoteTextFiledState(hint = "Enter title"))
    val noteTitleState: State<NoteTextFiledState> = _noteTitleState

    private val _noteContentState = mutableStateOf(NoteTextFiledState(hint = "Enter content"))
    val noteContentState: State<NoteTextFiledState> = _noteContentState

    private val _noteColorState = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColorState: State<Int> = _noteColorState

    private val _noteEventFlow = MutableSharedFlow<NoteUIEvent>()
    val noteEventFlow = _noteEventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    getNoteUseCase(noteId)?.let { note ->
                        currentNoteId = note.id
                        _noteTitleState.value = noteTitleState.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContentState.value = noteContentState.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _noteColorState.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: NoteScreenEvent) {
        when (event) {
            is NoteScreenEvent.EnteredTitle -> {
                _noteTitleState.value = noteTitleState.value.copy(
                    text = event.value
                )
            }
            is NoteScreenEvent.ChangeTitleFocus -> {
                _noteTitleState.value = noteTitleState.value.copy(
                    isHintVisible = event.focusState.isFocused.not() && noteTitleState.value.text.isBlank()
                )
            }
            is NoteScreenEvent.EnteredContent -> {
                _noteContentState.value = noteContentState.value.copy(
                    text = event.value
                )
            }
            is NoteScreenEvent.ChangeContentFocus -> {
                _noteContentState.value = noteContentState.value.copy(
                    isHintVisible = event.focusState.isFocused.not() && noteContentState.value.text.isBlank()
                )
            }
            is NoteScreenEvent.ChangeColor -> {
                _noteColorState.value = event.color
            }
            is NoteScreenEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        addNoteUseCase(
                            Note(
                                title = noteTitleState.value.text,
                                content = noteContentState.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColorState.value,
                                id = currentNoteId
                            )
                        )
                        _noteEventFlow.emit(NoteUIEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _noteEventFlow.emit(
                            NoteUIEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save the note"
                            )
                        )
                    }
                }
            }
        }
    }

}
