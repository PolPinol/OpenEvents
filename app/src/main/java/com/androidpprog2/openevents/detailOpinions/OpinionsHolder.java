package com.androidpprog2.openevents.detailOpinions;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.androidpprog2.openevents.R;
import com.androidpprog2.openevents.entities.UserOpinion;
import com.squareup.picasso.Picasso;

public class OpinionsHolder extends RecyclerView.ViewHolder {
    //Attributes
    private TextView nameUser;
    private TextView surnameUser;
    private TextView emailUser;
    private TextView commentUser;
    private RatingBar ratingBarUser;
    private Activity activity;
    private UserOpinion userOpinion;

    public OpinionsHolder(LayoutInflater inflater, ViewGroup parent, Activity activity) {
        super(inflater.inflate(R.layout.list_opinions, parent, false));

        nameUser = (TextView) itemView.findViewById(R.id.name_user);
        surnameUser = (TextView) itemView.findViewById(R.id.surname_user);
        commentUser = (TextView) itemView.findViewById(R.id.user_comment);
        emailUser = (TextView) itemView.findViewById(R.id.user_email);
        ratingBarUser = (RatingBar) itemView.findViewById(R.id.ratingBar_user);
        this.activity = activity;
    }

    public void bind(UserOpinion userOpinion) {
        this.userOpinion = userOpinion;
        nameUser.setText(userOpinion.getName());
        surnameUser.setText(userOpinion.getSurname());
        emailUser.setText(userOpinion.getEmail());


        if (userOpinion.getComment() != null && !userOpinion.getComment().isEmpty() &&
                !userOpinion.getComment().equals("null")) {
            commentUser.setVisibility(View.VISIBLE);
            commentUser.setText(userOpinion.getComment());
        } else {
            commentUser.setVisibility(View.GONE);
        }

        if (userOpinion.getRating() != null && !userOpinion.getRating().isEmpty()) {
            try {
                ratingBarUser.setRating(Integer.parseInt(userOpinion.getRating()));
            } catch (Exception e) {
                ratingBarUser.setVisibility(View.GONE);
            }
        } else {
            ratingBarUser.setVisibility(View.GONE);
        }


    }
}
