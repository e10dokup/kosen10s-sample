package net.kosen10s.example.service

import net.kosen10s.example.entity.TopHeadlines
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HackerNewsService {
    @GET("/v2/top-headlines")
    fun getTopHeadlines(@Query("sources") sources: String, @Query("apiKey") apiKey: String): Call<TopHeadlines>
}