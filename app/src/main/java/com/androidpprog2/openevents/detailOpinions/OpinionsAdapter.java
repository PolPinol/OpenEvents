package com.androidpprog2.openevents.detailOpinions;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidpprog2.openevents.detailOpinions.OpinionsHolder;
import com.androidpprog2.openevents.entities.Event;
import com.androidpprog2.openevents.entities.UserOpinion;

import java.util.List;

public class OpinionsAdapter extends RecyclerView.Adapter<OpinionsHolder> {

    private List<UserOpinion> userOpinionList;
    private Activity activity;

    public OpinionsAdapter(List<UserOpinion> userOpinionList, Activity activity) {
        this.userOpinionList = userOpinionList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public OpinionsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        return new OpinionsHolder(layoutInflater, parent, activity);
    }

    @Override
    public void onBindViewHolder(OpinionsHolder holder, int position) {
        UserOpinion userOpinion = userOpinionList.get(position);
        holder.bind(userOpinion);
    }

    @Override
    public int getItemCount() {
        return userOpinionList == null ? 0 : userOpinionList.size();
    }
}
