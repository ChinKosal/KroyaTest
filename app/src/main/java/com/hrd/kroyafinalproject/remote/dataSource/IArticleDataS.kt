package com.hrd.kroyafinalproject.remote.dataSource

import com.hrd.kroyafinalproject.data_user.ArticalRespone

interface IArticleDataS {
    suspend fun getarticle(): ArticalRespone
}