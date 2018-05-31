package net.kosen10s.example.item

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_article.view.*
import net.kosen10s.example.R
import net.kosen10s.example.entity.Article

class ArticleItem constructor(
        val article: Article
): Item() {

    override fun getLayout() = R.layout.item_article

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.title_text.text = article.title
        viewHolder.itemView.description_text.text = article.description
    }

}
