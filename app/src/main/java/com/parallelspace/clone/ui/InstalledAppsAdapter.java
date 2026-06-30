package com.parallelspace.clone.ui;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class InstalledAppsAdapter extends RecyclerView.Adapter<InstalledAppsAdapter.ViewHolder> {

    public interface OnAppSelectListener {
        void onSelect(ApplicationInfo app);
    }

    private final Context context;
    private final List<ApplicationInfo> apps;
    private final OnAppSelectListener listener;
    private final PackageManager pm;

    public InstalledAppsAdapter(Context context, List<ApplicationInfo> apps, OnAppSelectListener listener) {
        this.context = context;
        this.apps = apps;
        this.listener = listener;
        this.pm = context.getPackageManager();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApplicationInfo app = apps.get(position);
        holder.tvName.setText(pm.getApplicationLabel(app));
        holder.tvPackage.setText(app.packageName);
        holder.itemView.setOnClickListener(v -> listener.onSelect(app));
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPackage;

        ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(android.R.id.text1);
            tvPackage = view.findViewById(android.R.id.text2);
        }
    }
}
