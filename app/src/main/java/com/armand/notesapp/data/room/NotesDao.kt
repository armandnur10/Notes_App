package com.armand.notesapp.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.armand.notesapp.data.entity.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNotes(notes: Notes)

    @Query("SELECT * FROM notes")
    fun getAllData() : LiveData<List<Notes>>

    @Query("SELECT * FROM notes ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority(): LiveData<List<Notes>>


    @Query("SELECT * FROM notes ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority(): LiveData<List<Notes>>

    @Query("DELETE FROM notes")
    suspend fun deleteAllData()

    @Query("SELECT * FROM notes WHERE title Like :query")
    fun searchByQuery(query: String): LiveData<List<Notes>>

    @Delete
    suspend fun deleteNote(notes: Notes) {

    }
    @Update
    suspend fun updateNote(notes: Notes)


}