package com.parallelspace.clone.ui;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.parallelspace.clone.core.AppCloneManager;
import java.util.List;

public class AppListActivity extends AppCompatActivity {

    private AppCloneManager cloneManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cloneManager = AppCloneManager.getInstance(this);

        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setContentView(recyclerView);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Select App to Clone");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        List<ApplicationInfo> apps = cloneManager.getInstallableApps();
        InstalledAppsAdapter adapter = new InstalledAppsAdapter(this, apps, app -> {
            boolean success = cloneManager.cloneApp(app.packageName);
            if (success) {
                Toast.makeText(this, "App cloned successfully!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to clone app", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
