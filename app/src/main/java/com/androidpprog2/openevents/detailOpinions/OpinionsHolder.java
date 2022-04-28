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

public class OpinionsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final static String NO_IMAGE_URL = "https://st3.depositphotos.com/23594922/31822/v/600/depositphotos_318221368-stock-illustration-missing-picture-page-for-website.jpg";

    //Attributes
    private TextView nameUser;
    private TextView emailUser;
    private TextView commentUser;
    private ImageView imageUser;
    private RatingBar ratingBarUser;
    private Activity activity;
    private UserOpinion userOpinion;

    public OpinionsHolder(LayoutInflater inflater, ViewGroup parent, Activity activity) {
        super(inflater.inflate(R.layout.list_opinions, parent, false));

        imageUser = (ImageView) itemView.findViewById(R.id.image_user);
        nameUser = (TextView) itemView.findViewById(R.id.name_user);
        commentUser = (TextView) itemView.findViewById(R.id.user_comment);
        emailUser = (TextView) itemView.findViewById(R.id.user_email);
        ratingBarUser = (RatingBar) itemView.findViewById(R.id.ratingBar_user);
        this.activity = activity;
        itemView.setOnClickListener(this);
    }

    public void bind(UserOpinion userOpinion) {
        this.userOpinion = userOpinion;
        nameUser.setText(userOpinion.getName());
        commentUser.setText(userOpinion.getComment());
        emailUser.setText(userOpinion.getEmail());
        ratingBarUser.setRating(userOpinion.getRating());

        try {
            Picasso.get().load(userOpinion.getImage()).into(imageUser);
        } catch (Exception e) {
            Picasso.get().load(NO_IMAGE_URL).into(imageUser);
        }
    }

    @Override
    public void onClick(View view) {
    }
}
