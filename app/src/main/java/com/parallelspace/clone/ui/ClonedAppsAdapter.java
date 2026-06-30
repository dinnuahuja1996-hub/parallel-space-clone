package com.parallelspace.clone.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.parallelspace.clone.R;
import com.parallelspace.clone.core.ClonedAppInfo;
import java.util.ArrayList;
import java.util.List;

public class ClonedAppsAdapter extends RecyclerView.Adapter<ClonedAppsAdapter.ViewHolder> {

    public interface OnAppClickListener {
        void onLaunch(ClonedAppInfo app);
    }

    public interface OnAppLongClickListener {
        void onRemove(ClonedAppInfo app);
    }

    private List<ClonedAppInfo> apps = new ArrayList<>();
    private final OnAppClickListener clickListener;
    private final OnAppLongClickListener longClickListener;

    public ClonedAppsAdapter(OnAppClickListener clickListener, OnAppLongClickListener longClickListener) {
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
    }

    public void setApps(List<ClonedAppInfo> apps) {
        this.apps = apps;
        notifyDataSetChanged();
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
        ClonedAppInfo app = apps.get(position);
        holder.tvName.setText(app.appName);
        holder.tvPackage.setText(app.packageName);
        holder.itemView.setOnClickListener(v -> clickListener.onLaunch(app));
        holder.itemView.setOnLongClickListener(v -> {
            longClickListener.onRemove(app);
            return true;
        });
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
