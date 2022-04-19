package com.androidpprog2.openevents.chat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androidpprog2.openevents.R;
import com.androidpprog2.openevents.entities.Message;

public class ChatHolder extends RecyclerView.ViewHolder {
    private TextView contentText;
    private Message message;
    private Activity activity;
    private LinearLayout layout;

    public ChatHolder(LayoutInflater inflater, ViewGroup parent, Activity activity) {
        super(inflater.inflate(R.layout.list_messages, parent, false));
        contentText = (TextView) itemView.findViewById(R.id.message_id);
        layout = itemView.findViewById(R.id.layout_id);
        this.activity = activity;
    }

    public void bind(Message message) {
        this.message = message;
        contentText.setText(message.getContent());
        // assign margins depending on id

        ViewGroup.LayoutParams layoutParams = layout.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            if (message.isAlignLeft()) {
                ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = 20;
                ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = 100;
                Drawable drawable = activity.getDrawable(R.drawable.big_radius_inverse_button);
                layout.setBackground(drawable);
            } else {
                ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = 100;
                ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = 20;
                Drawable drawable = activity.getDrawable(R.drawable.big_radius_button);
                layout.setBackground(drawable);
            }
        }
    }
}