package net.kosen10s.example.presenter

import android.util.Log
import net.kosen10s.example.datasource.ArticlesDataSource
import net.kosen10s.example.entity.Article
import net.kosen10s.example.navigater.MainNavigator
import net.kosen10s.example.view.articles.ArticlesActivity
import net.kosen10s.example.view.main.MainActivity

class ArticlesActivityPresenter constructor(
        activity: ArticlesActivity
) {

    private val articlesDataSource = ArticlesDataSource()

    fun getArticles(onSuccess: (List<Article>) -> Unit) {
        articlesDataSource.getTopHeadlines(onSuccess = {
            onSuccess.invoke(it)
        }, onError = {
            Log.d("getArticles", it?.message)
        })
    }


}