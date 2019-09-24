package com.vaibhavdhunde.practice.myposts.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaibhavdhunde.practice.myposts.api.ApiException
import com.vaibhavdhunde.practice.myposts.api.NetworkException
import com.vaibhavdhunde.practice.myposts.data.PostsRepository
import com.vaibhavdhunde.practice.myposts.model.Post
import com.vaibhavdhunde.practice.myposts.util.Event
import kotlinx.coroutines.launch

class PostsViewModel(private val repository: PostsRepository) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _isDataAvailable = MutableLiveData<Boolean>()
    val isDataAvailable: LiveData<Boolean> = _isDataAvailable

    private val _showMessage = MutableLiveData<Event<String>>()
    val showMessage: LiveData<Event<String>> = _showMessage

    fun loadPosts() {
        _isDataLoading.value?.let { isLoading ->
            if (isLoading) return@let
        }

        _isDataAvailable.value?.let { isAvailable ->
            if (isAvailable) return@let
        }

        viewModelScope.launch {
            _isDataLoading.postValue(true)
            try {
                val posts = repository.getPosts()
                onPostsLoaded(posts)
            } catch (e: ApiException) {
                onDataNotAvailable()
                showMessage(e.message!!)
            } catch (e: NetworkException) {
                onDataNotAvailable()
                showMessage(e.message!!)
            }
        }
    }

    private fun onPostsLoaded(posts: List<Post>?) {
        if (posts == null) {
            onDataNotAvailable()
            return
        }
        _posts.postValue(posts)
        _isDataLoading.postValue(false)
        if (posts.isEmpty()) {
            onDataNotAvailable()
        } else {
            _isDataAvailable.postValue(true)
        }
    }

    private fun onDataNotAvailable() {
        _posts.postValue(null)
        _isDataLoading.postValue(false)
        _isDataAvailable.postValue(false)
    }

    private fun showMessage(message: String) {
        _showMessage.postValue(Event(message))
    }
}