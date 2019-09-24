package com.vaibhavdhunde.practice.myposts.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vaibhavdhunde.practice.myposts.R
import com.vaibhavdhunde.practice.myposts.databinding.FragmentDetailsBinding
import com.vaibhavdhunde.practice.myposts.util.EventObserver
import com.vaibhavdhunde.practice.myposts.util.MyPostsViewModelFactory
import com.vaibhavdhunde.practice.myposts.util.obtainViewModel
import kotlinx.android.synthetic.main.fragment_posts.*
import org.jetbrains.anko.design.longSnackbar
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class DetailsFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val viewModelFactory: MyPostsViewModelFactory by instance()

    private lateinit var viewModel: DetailsViewModel

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_details, container, false)

        viewModel = obtainViewModel(DetailsViewModel::class.java, viewModelFactory)

        binding = FragmentDetailsBinding.bind(rootView).apply {
            viewmodel = this@DetailsFragment.viewModel
            lifecycleOwner = this@DetailsFragment.viewLifecycleOwner
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        loadPosts()
    }

    private fun setupEvents() {
        binding.viewmodel?.showMessage?.observe(this, EventObserver { message ->
            fragment_posts?.longSnackbar(message)
        })
    }

    private fun loadPosts() {
        binding.viewmodel?.loadPost(getPostId())
    }

    private fun getPostId(): Int {
        return arguments!!.let {
            DetailsFragmentArgs.fromBundle(it).postId
        }
    }

}
