package com.vaibhavdhunde.practice.myposts.ui.details

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

class DetailsViewModel(private val repository: PostsRepository) : ViewModel() {

    private val _post = MutableLiveData<Post>()
    val post: LiveData<Post> = _post

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _isDataAvailable = MutableLiveData<Boolean>()
    val isDataAvailable: LiveData<Boolean> = _isDataAvailable

    private val _showMessage = MutableLiveData<Event<String>>()
    val showMessage: LiveData<Event<String>> = _showMessage

    fun loadPost(postId: Int) {
        _isDataLoading.value?.let { isLoading ->
            if (isLoading) return
        }

        _isDataAvailable.value?.let { isAvailable ->
            if (isAvailable) return
        }

        viewModelScope.launch {
            _isDataLoading.postValue(true)
            try {
                val post = repository.getPostById(postId)
                onPostLoaded(post)
            } catch (e: ApiException) {
                onDataNotAvailable()
                showMessage(e.message!!)
            } catch (e: NetworkException) {
                onDataNotAvailable()
                showMessage(e.message!!)
            }
        }
    }

    private fun onPostLoaded(post: Post?) {
        if (post == null) {
            onDataNotAvailable()
            return
        }
        _post.postValue(post)
        _isDataLoading.postValue(false)
        _isDataAvailable.postValue(true)
    }

    private fun onDataNotAvailable() {
        _post.postValue(null)
        _isDataLoading.postValue(false)
        _isDataAvailable.postValue(false)
    }

    private fun showMessage(message: String) {
        _showMessage.postValue(Event(message))
    }
}