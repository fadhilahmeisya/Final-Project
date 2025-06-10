package com.example.afinal.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.afinal.databinding.FragmentNewsBinding;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private NewsViewModel newsViewModel;
    private NewsAdapter newsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.getAllArticles().observe(getViewLifecycleOwner(), articles -> {
            newsAdapter.setArticles(articles);
            binding.progressBar.setVisibility(View.GONE);
            if (articles.isEmpty()) {
                binding.btnRefresh.setVisibility(View.VISIBLE);
            } else {
                binding.btnRefresh.setVisibility(View.GONE);
            }
        });

        binding.btnRefresh.setOnClickListener(v -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.btnRefresh.setVisibility(View.GONE);
            newsViewModel.refreshNews();
        });
    }

    private void setupRecyclerView() {
        newsAdapter = new NewsAdapter();
        binding.recyclerViewNews.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewNews.setAdapter(newsAdapter);

        newsAdapter.setOnItemClickListener(article -> {
            if (article.getUrl() != null && !article.getUrl().isEmpty()) {
                NewsFragmentDirections.ActionNewsFragmentToDetailFragment action =
                        NewsFragmentDirections.actionNewsFragmentToDetailFragment(article.getUrl());
                NavHostFragment.findNavController(this).navigate(action);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}