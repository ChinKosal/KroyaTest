package com.hrd.kroyafinalproject.remote.dataSource

import com.hrd.kroyafinalproject.data_user.ArticalRespone
import com.hrd.kroyafinalproject.remote.ArticleService
import javax.inject.Inject

class ArticleDataSImp @Inject constructor(private val articleService: ArticleService) :
    IArticleDataS {
    override suspend fun getarticle(): ArticalRespone {
        TODO("Not yet implemented")
    }
}