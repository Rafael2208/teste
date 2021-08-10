package com.msf.githubissues.model.bd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.msf.githubissues.model.Issue

@Dao
interface IssueDao {
    @Query("SELECT * FROM issues")
    fun loadMovies(): List<Issue>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIssues(issue: List<Issue>)

}