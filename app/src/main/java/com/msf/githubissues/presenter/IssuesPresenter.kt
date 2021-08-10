package com.msf.githubissues.presenter

import android.content.Context
import android.net.ConnectivityManager
import com.msf.githubissues.model.ApiService
import com.msf.githubissues.model.Calls
import com.msf.githubissues.model.Issue
import com.msf.githubissues.model.bd.IssuesDatabase
import com.msf.githubissues.util.*
import com.msf.githubissues.view.ListIssuesFragment
import javax.inject.Inject

class IssuesPresenter(private val fragment: ListIssuesFragment) {

    private val injector: Injector = DaggerInjector.builder().networkModule(RetrofitModule).build()
    private var listener: View
    private var database: IssuesDatabase

    init {
        inject()
        listener = fragment
        database = IssuesDatabase.getInstance(fragment.context!!)
    }

    private fun inject() {
        injector.inject(this)
    }

    @Inject
    lateinit var apiService: ApiService

    fun getIssues() {
        if(isOnline()){
            Calls.callIssue(apiService.callIssues(), this)
        } else {
            AppExecutor.sInstance.getDbIo().execute {
                val issues = database.issueDao().loadMovies()
                updateListIssue(issues)
            }
        }
        EspressoIdlingResource.increment()
    }

    fun updateListIssue(issues: List<Issue>?){
        if(issues != null) {
            AppExecutor.sInstance.getDbIo().execute {
                database.issueDao().insertIssues(issues)
            }
            listener.createAdapter(issues)
        } else {
            listener.showEmptyMessage()
        }
        EspressoIdlingResource.decrement()
    }

    fun showFailure() {
        listener.showFailure()
        EspressoIdlingResource.decrement()
    }

    private fun isOnline(): Boolean {
        val connectivityManager = fragment.context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    internal interface View {
        fun showRecyclerView(showRecyclerView: Boolean)
        fun createAdapter(list: List<Issue>)
        fun showEmptyMessage()
        fun showFailure()
    }
}