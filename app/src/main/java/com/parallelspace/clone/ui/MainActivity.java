package com.parallelspace.clone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import com.parallelspace.clone.core.AppCloneManager;
import com.parallelspace.clone.core.ClonedAppInfo;
import com.parallelspace.clone.databinding.ActivityMainBinding;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ClonedAppsAdapter adapter;
    private AppCloneManager cloneManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        cloneManager = AppCloneManager.getInstance(this);

        setupRecyclerView();
        setupFab();
        loadClonedApps();
    }

    private void setupRecyclerView() {
        adapter = new ClonedAppsAdapter(app -> {
            // Launch cloned app
            cloneManager.launchApp(this, app.packageName, app.userId);
        }, app -> {
            // Remove cloned app
            cloneManager.removeApp(app.packageName, app.userId);
            loadClonedApps();
        });

        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recyclerView.setAdapter(adapter);
    }

    private void setupFab() {
        binding.fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AppListActivity.class);
            startActivity(intent);
        });
    }

    private void loadClonedApps() {
        List<ClonedAppInfo> apps = cloneManager.getClonedApps();
        adapter.setApps(apps);

        if (apps.isEmpty()) {
            binding.tvEmpty.setVisibility(View.VISIBLE);
            binding.recyclerView.setVisibility(View.GONE);
        } else {
            binding.tvEmpty.setVisibility(View.GONE);
            binding.recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadClonedApps();
    }
}
