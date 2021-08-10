package com.msf.githubissues.view

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.msf.githubissues.R
import com.msf.githubissues.databinding.FragmentIssueBinding
import com.msf.githubissues.util.EspressoIdlingResource
import com.squareup.picasso.Picasso

class IssueFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val dataBinding:FragmentIssueBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_issue, container,false)
        val args  = arguments?.let { IssueFragmentArgs.fromBundle(it) }
        dataBinding.issue = args!!.issueClicked
        Picasso.get().load(dataBinding.issue!!.user.avatarUrl).into(dataBinding.imgAvatar)
        dataBinding.txtLinkExt.movementMethod = LinkMovementMethod.getInstance()
        dataBinding.txtLinkExt.text = Html.fromHtml("<a href='${dataBinding.issue!!.htmlUrl}'>Visualizar</a>")
        return dataBinding.root
    }


}
