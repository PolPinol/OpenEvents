package com.androidpprog2.openevents.api;

import com.android.volley.VolleyError;

public interface ResponseListener {
    void onResponse(String response);
    void onError(VolleyError error);
}
