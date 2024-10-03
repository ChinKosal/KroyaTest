package com.hrd.kroyafinalproject.di

import com.hrd.kroyafinalproject.connst.Const
import com.hrd.kroyafinalproject.remote.ArticleService
import com.hrd.kroyafinalproject.remote.dataSource.ArticleDataSImp
import com.hrd.kroyafinalproject.remote.dataSource.IArticleDataS
import com.hrd.kroyafinalproject.remote.repository.IRepository
import com.hrd.kroyafinalproject.remote.repository.RepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule{
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }
    @Singleton
    @Provides
    // For create Retrofit
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Const.BASE_URL)
            .client(client)
            .build()
    }
    @Singleton
    @Provides
    // Create Service that received from Retrofit
    fun provideApiService(retrofit: Retrofit): ArticleService {
        return retrofit.create(ArticleService::class.java)
    }
    @Singleton
    @Provides
    // After create Service from Retrofit we need to Create DataSource and DataSource received Service and return DataSource Implement
    fun provideDataSource(userService: ArticleService):IArticleDataS{
        return ArticleDataSImp(userService)
    }
    @Singleton
    @Provides
    // After create DataSource we need to create Repository that received DataSource Implement and return Repository Implement
    fun provideLoginRepo(dataSource: IArticleDataS): IRepository {
        return RepositoryImp(dataSource)
    }
}