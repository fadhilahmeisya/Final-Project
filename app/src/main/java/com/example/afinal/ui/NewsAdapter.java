package com.example.afinal.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.afinal.R;
import com.example.afinal.data.Article;
import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ArticleViewHolder> {

    private List<Article> articles = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article currentArticle = articles.get(position);
        holder.articleTitle.setText(currentArticle.getTitle());
        holder.articleDescription.setText(currentArticle.getDescription());

        Glide.with(holder.itemView.getContext())
                .load(currentArticle.getUrlToImage())
                .placeholder(R.drawable.ic_launcher_background) // ganti dengan placeholder yang sesuai
                .into(holder.articleImage);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null && position != RecyclerView.NO_POSITION) {
                listener.onItemClick(articles.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Article article);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        private final ImageView articleImage;
        private final TextView articleTitle;
        private final TextView articleDescription;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.iv_article_image);
            articleTitle = itemView.findViewById(R.id.tv_title);
            articleDescription = itemView.findViewById(R.id.tv_description);
        }
    }
}