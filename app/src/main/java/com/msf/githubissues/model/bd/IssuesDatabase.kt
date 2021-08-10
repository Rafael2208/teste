package com.msf.githubissues.model.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.msf.githubissues.model.Issue

var dbInstance: IssuesDatabase? = null

const val DATABASE_NAME = "issues_db"

@Database(entities = [Issue::class], version = 1, exportSchema = false)
abstract class IssuesDatabase : RoomDatabase() {

    companion object{
        fun getInstance(context: Context): IssuesDatabase {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                        IssuesDatabase::class.java, DATABASE_NAME)
                        .build()
            }
            return dbInstance!!
        }

    }

    abstract fun issueDao() : IssueDao
}