package com.msf.githubissues.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.msf.githubissues.R
import com.msf.githubissues.model.Issue
import kotlinx.android.synthetic.main.issue_content.view.*

class IssueRecyclerAdapter(private val list: List<Issue>) : RecyclerView.Adapter<IssueRecyclerAdapter.IssueHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.issue_content, parent, false)
        return IssueHolder(view)
    }

    override fun getItemCount(): Int = list.size

    private fun getItem(position: Int): Issue = list[position]


    override fun onBindViewHolder(holder: IssueHolder, position: Int) {
        holder.bind(getItem(position))
        with(holder.mView){
            setOnClickListener{
                it.findNavController().navigate(ListIssuesFragmentDirections.actionListIssuesFragmentToIssueFragment(getItem(position)))
            }
        }
    }

    inner class IssueHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        fun bind(issue: Issue){
            itemView.txt_label_issue_number.text = "Issue #${issue.number}"
            itemView.issue_title.text = issue.title
            itemView.img_state.setImageResource(getImgState(issue))
        }
    }

    private fun getImgState(issue: Issue): Int{
        return if(issue.isOpen()) R.drawable.ic_open else R.drawable.ic_lock
    }

}
