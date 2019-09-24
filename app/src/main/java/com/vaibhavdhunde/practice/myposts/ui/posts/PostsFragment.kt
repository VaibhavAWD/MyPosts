package com.vaibhavdhunde.practice.myposts.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vaibhavdhunde.practice.myposts.R
import com.vaibhavdhunde.practice.myposts.databinding.FragmentPostsBinding
import com.vaibhavdhunde.practice.myposts.util.EventObserver
import com.vaibhavdhunde.practice.myposts.util.MyPostsViewModelFactory
import com.vaibhavdhunde.practice.myposts.util.obtainViewModel
import kotlinx.android.synthetic.main.fragment_posts.*
import org.jetbrains.anko.design.longSnackbar
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class PostsFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val viewModelFactory: MyPostsViewModelFactory by instance()

    private lateinit var viewModel: PostsViewModel

    private lateinit var binding: FragmentPostsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_posts, container, false)

        viewModel = obtainViewModel(PostsViewModel::class.java, viewModelFactory)

        val postsListAdapter = PostsListAdapter(viewModel)

        binding = FragmentPostsBinding.bind(rootView).apply {
            viewmodel = this@PostsFragment.viewModel
            adapter = postsListAdapter
            lifecycleOwner = this@PostsFragment.viewLifecycleOwner
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setupNavigation()
        loadPosts()
    }

    private fun setupEvents() {
        binding.viewmodel?.showMessage?.observe(this, EventObserver { message ->
            fragment_posts?.longSnackbar(message)
        })
    }

    private fun setupNavigation() {
        binding.viewmodel?.openPostDetails?.observe(this, EventObserver { postId ->
            navigateToFragmentDetails(postId)
        })
    }

    private fun loadPosts() {
        binding.viewmodel?.loadPosts()
    }

    private fun navigateToFragmentDetails(postId: Int) {
        val action = PostsFragmentDirections.actionOpenPostDetails(postId)
        findNavController().navigate(action)
    }

}
