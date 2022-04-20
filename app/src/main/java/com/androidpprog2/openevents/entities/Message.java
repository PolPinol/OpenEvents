package com.androidpprog2.openevents.entities;

public class Message {
    private String content;
    private int user_id_send;
    private int user_id_recived;
    private boolean alignLeft;

    public Message(String content, int user_id_send, int user_id_recived, boolean alignLeft) {
        this.content = content;
        this.user_id_send = user_id_send;
        this.user_id_recived = user_id_recived;
        this.alignLeft = alignLeft;
    }

    public int getUser_id_recived() {
        return user_id_recived;
    }

    public int getUser_id_send() {
        return user_id_send;
    }

    public String getContent() {
        return content;
    }

    public boolean isAlignLeft() {
        return alignLeft;
    }
}
