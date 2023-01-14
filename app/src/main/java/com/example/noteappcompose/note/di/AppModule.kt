package com.example.noteappcompose.note.di

import android.app.Application
import androidx.room.Room
import com.example.noteappcompose.note.data.repository.NoteRepositoryImpl
import com.example.noteappcompose.note.data.source.NoteDatabase
import com.example.noteappcompose.note.domain.repository.NoteRepository
import com.example.noteappcompose.note.domain.usecase.AddNoteUseCase
import com.example.noteappcompose.note.domain.usecase.DeleteNoteUseCase
import com.example.noteappcompose.note.domain.usecase.GetNoteUseCase
import com.example.noteappcompose.note.domain.usecase.GetNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideAddNoteUseCase(noteRepository: NoteRepository): AddNoteUseCase {
        return AddNoteUseCase(noteRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteNoteUseCase(noteRepository: NoteRepository): DeleteNoteUseCase {
        return DeleteNoteUseCase(noteRepository)
    }

    @Provides
    @Singleton
    fun provideGetNotesUseCase(noteRepository: NoteRepository): GetNotesUseCase {
        return GetNotesUseCase(noteRepository)
    }

    @Provides
    @Singleton
    fun provideGetNoteUseCase(noteRepository: NoteRepository): GetNoteUseCase {
        return GetNoteUseCase(noteRepository)
    }

}
