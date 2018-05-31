package net.kosen10s.example.view.articles

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_articles.*
import net.kosen10s.example.R
import net.kosen10s.example.item.ArticleItem
import net.kosen10s.example.presenter.ArticlesActivityPresenter
import net.kosen10s.example.presenter.MainActivityPresenter

class ArticlesActivity: AppCompatActivity() {

    private val presenter by lazy {
        ArticlesActivityPresenter(this)
    }

    private val adapter = GroupAdapter<ViewHolder>().apply {
        setOnItemClickListener({ item, _ ->
            val articleItem = item as? ArticleItem ?: return@setOnItemClickListener
            Snackbar.make(articles_recycler, articleItem.article.title, Snackbar.LENGTH_SHORT).show()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_articles)
        articles_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        articles_recycler.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        presenter.getArticles {
            it.forEach {
                adapter.add(ArticleItem(it))
            }

        }
    }


}