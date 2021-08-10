package com.msf.githubissues.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.msf.githubissues.R
import com.msf.githubissues.databinding.FragmentListIssuesBinding
import com.msf.githubissues.model.Issue
import com.msf.githubissues.presenter.IssuesPresenter
import com.msf.githubissues.util.EspressoIdlingResource

class ListIssuesFragment : Fragment(), IssuesPresenter.View{

    private lateinit var binding:FragmentListIssuesBinding
    private lateinit var presenter: IssuesPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_issues, container, false)
        with(binding.recyclerViewIssues){
            layoutManager = LinearLayoutManager(context)
        }
        showRecyclerView(false)
        presenter = IssuesPresenter(this)
        presenter.getIssues()
        return binding.root
    }

    override fun showRecyclerView(showRecyclerView: Boolean) {
        binding.recyclerViewIssues.visibility = if (showRecyclerView) View.VISIBLE else View.GONE
        binding.progressLoading.visibility = if (showRecyclerView) View.GONE else View.VISIBLE
    }

    override fun createAdapter(list: List<Issue>) {
        binding.recyclerViewIssues.adapter = IssueRecyclerAdapter(list)
        showRecyclerView(true)
    }

    override fun showEmptyMessage() {
        binding.progressLoading.visibility = View.GONE
        binding.errorMessage.visibility = View.VISIBLE
        binding.errorMessage.text = getString(R.string.no_issues_founded)
    }

    override fun showFailure() {
        binding.progressLoading.visibility = View.GONE
        binding.errorMessage.visibility = View.VISIBLE
        binding.errorMessage.text = getString(R.string.somenthing_get_wrong)
    }
}
