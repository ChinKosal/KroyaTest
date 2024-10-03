package com.hrd.kroyafinalproject.remote.repository

import com.hrd.kroyafinalproject.remote.dataSource.IArticleDataS
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val datasouce: IArticleDataS
) : IRepository {

}