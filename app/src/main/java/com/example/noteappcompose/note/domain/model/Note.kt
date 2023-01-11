package com.example.noteappcompose.note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteappcompose.ui.theme.BabyBlue
import com.example.noteappcompose.ui.theme.LightGreen
import com.example.noteappcompose.ui.theme.RedOrange
import com.example.noteappcompose.ui.theme.RedPink
import com.example.noteappcompose.ui.theme.Violet

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}
