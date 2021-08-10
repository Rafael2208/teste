package com.msf.githubissues.model

import com.msf.githubissues.presenter.IssuesPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Calls {

    fun callIssue(issueCall: Call<List<Issue>>, presenter: IssuesPresenter){
        issueCall.enqueue(object : Callback<List<Issue>> {
            override fun onResponse(call: Call<List<Issue>>, response: Response<List<Issue>>) {
                presenter.updateListIssue(response.body())
            }

            override fun onFailure(call: Call<List<Issue>>, t: Throwable) {
                presenter.showFailure()
            }

        })
    }

}