package com.example.noteappcompose.note.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
