package com.msf.githubissues.util

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutor(val execForDb: Executor){

    companion object{
        val sInstance: AppExecutor by lazy {
            AppExecutor(Executors.newSingleThreadExecutor())
        }

    }

    fun getDbIo(): Executor {
        return execForDb
    }
}