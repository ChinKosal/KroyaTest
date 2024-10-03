package com.hrd.kroyafinalproject.screen.viewModel

import androidx.lifecycle.ViewModel
import com.hrd.kroyafinalproject.remote.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val articleRepo: IRepository):ViewModel(){

}