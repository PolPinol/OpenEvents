package com.androidpprog2.openevents.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.androidpprog2.openevents.R;
import com.androidpprog2.openevents.SearchedUserProfile;
import com.androidpprog2.openevents.api.APIManager;
import com.androidpprog2.openevents.api.ResponseListener;
import com.androidpprog2.openevents.entities.FriendRequest;
import com.androidpprog2.openevents.entities.Message;
import com.androidpprog2.openevents.friendRequests.FriendRequestAdapter;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements ResponseListener {
    private final static int MODE_GET_MESSAGES = 0;
    private final static int MODE_POST_MESSAGES = 1;

    private int modeRequest;

    private RecyclerView recyclerView;
    private ChatAdapter adapter;
    private List<Message> listMessages;

    private TextView contentTextView;
    private String content;
    private int user_id_send;
    private int user_id_recived;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().hide();

        listMessages = new ArrayList<>();

        contentTextView = findViewById(R.id.message_text);
        this.id = getIntent().getExtras().getInt("ARGUMENT_OTHER_ID");

        recyclerView = (RecyclerView) this.findViewById(R.id.chat_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ChatAdapter(null, this);
        recyclerView.setAdapter(adapter);

        // button send message
        Button sendButton = findViewById(R.id.button_send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeRequest = MODE_POST_MESSAGES;
                content = contentTextView.getText().toString();
                if (!content.isEmpty()) {
                    APIManager.createMessage(view.getContext(), ChatActivity.this, content, APIManager.getId() ,id);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        listMessages = new ArrayList<>();
        APIManager.getAllMessages(this, this, id);
    }

    private void updateUI() {
        if (adapter == null) {
            adapter = new ChatAdapter(listMessages, this);
            recyclerView.setAdapter(adapter);
            recyclerView.scrollToPosition(adapter.getItemCount()-1);
        } else {
            adapter = new ChatAdapter(listMessages, this);
            recyclerView.setAdapter(adapter);
            recyclerView.scrollToPosition(adapter.getItemCount()-1);

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResponse(String response) {
        if (modeRequest == MODE_GET_MESSAGES) {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    content = jsonArray.getJSONObject(i).getString("content");
                    user_id_send = jsonArray.getJSONObject(i).getInt("user_id_send");
                    user_id_recived = jsonArray.getJSONObject(i).getInt("user_id_recived");
                    boolean alignLeft = user_id_send != APIManager.getId();

                    Message message = new Message(content, user_id_send, user_id_recived, alignLeft);
                    listMessages.add(message);
                }

                modeRequest = MODE_POST_MESSAGES;
                updateUI();
            } catch (Exception e) {
                Toast.makeText(this, R.string.error_parsing_json, Toast.LENGTH_LONG).show();
            }
        } else if (modeRequest == MODE_POST_MESSAGES) {
            listMessages.add(new Message(content, APIManager.getId(),id, false));
            adapter.notifyDataSetChanged();
            contentTextView.setText("");
            recyclerView.scrollToPosition(adapter.getItemCount()-1);
        }
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(this, R.string.toast_api_error, Toast.LENGTH_LONG).show();
    }
}