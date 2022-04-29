package com.androidpprog2.openevents.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class APIManager {
    private static final String BASE_URL = "http://puigmal.salle.url.edu/api/v2";
    private static final String ENDPOINT_USERS = BASE_URL + "/users";
    private static final String ENDPOINT_USERS_LOGIN = BASE_URL + "/users/login";
    private static final String ENDPOINT_USERS_ID = BASE_URL + "/users/{id}";
    private static final String ENDPOINT_USERS_SEARCH = BASE_URL + "/users/search";
    private static final String ENDPOINT_USERS_STATS = BASE_URL + "/users/{id}/statistics";
    private static final String ENDPOINT_USERS_EVENTS = BASE_URL + "/users/{id}/events";
    private static final String ENDPOINT_USERS_EV_FUTURE = BASE_URL + "/users/{id}/events/future";
    private static final String ENDPOINT_USERS_EV_END = BASE_URL + "/users/{id}/events/finished";
    private static final String ENDPOINT_USERS_EV_CURRENT = BASE_URL + "/users/{id}/events/current";
    private static final String ENDPOINT_USERS_ASSIS = BASE_URL + "/users/{id}/assistances";
    private static final String ENDPOINT_USERS_ASSIS_FUTURE = BASE_URL + "/users/{id}/assistances/future";
    private static final String ENDPOINT_USERS_ASSIS_END = BASE_URL + "/users/{id}/assistances/finished";
    private static final String ENDPOINT_USERS_FRIENDS = BASE_URL + "/users/{id}/friends";
    private static final String ENDPOINT_EVENTS = BASE_URL + "/events";
    private static final String ENDPOINT_EVENTS_ID = BASE_URL + "/events/{id}";
    private static final String ENDPOINT_EVENTS_BEST = BASE_URL + "/events/best";
    private static final String ENDPOINT_EVENTS_SEARCH = BASE_URL + "/events/search";
    private static final String ENDPOINT_EVENTS_ASSIS = BASE_URL + "/events/{id}/assistances";
    private static final String ENDPOINT_EVENTS_ONE_ASSIS = BASE_URL + "/events/{event_id}/assistances/{user_id}";
    private static final String ENDPOINT_ASSISTANCES = BASE_URL + "assistances/{user_id}/{event_id}";
    private static final String ENDPOINT_MESSAGES = BASE_URL + "/messages";
    private static final String ENDPOINT_MESS_USERS = BASE_URL + "/messages/users";
    private static final String ENDPOINT_MESS_ID = BASE_URL + "/messages/{id}";
    private static final String ENDPOINT_FRIENDS = BASE_URL + "/friends";
    private static final String ENDPOINT_FRIENDS_REQ = BASE_URL + "/friends/requests";
    private static final String ENDPOINT_FRIENDS_ID = BASE_URL + "/friends/{id}";
    private static final String ENDPOINT_DELETE_FRIEND = BASE_URL + "/friends/{id}";
    private static final String BEARER = "Bearer ";
    private static final String ID = "{id}";
    private static final String USER_ID = "{user_id}";
    private static final String EVENT_ID = "{event_id}";

    private static String token;
    private static String email;
    private static int id;

    public APIManager() {

    }

    public static boolean isTokenNull() {
        return APIManager.token == null;
    }

    public static void setToken(String token) {
        Log.i("token", token);
        APIManager.token = token;
    }

    public static void setId(int id) {
        APIManager.id = id;
    }

    public static int getId() {
        return APIManager.id;
    }

    public static void setEmailAndGetId(Context context, ResponseListener listener, String email) {
        APIManager.email = email;
        getUsersFiltered(context, listener, email);
    }

    public static void logout() {
        APIManager.token = null;
    }

    // POST METHOD
    // Creates new User
    public static void postUser(Context context, ResponseListener listener, String name, String last_name, String password, String email, String image) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("last_name", last_name);
        map.put("password", password);
        map.put("email", email);
        map.put("image", image);

        makeRequest(context, ENDPOINT_USERS, Request.Method.POST, listener, map);
    }

    // POST METHOD
    // Return access token
    public static void authenticateUser(Context context, ResponseListener listener, String email, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);

        makeRequest(context, ENDPOINT_USERS_LOGIN, Request.Method.POST, listener, map);
    }

    // GET METHOD
    // Gets all users
    public static void getAllUsers(Context context, ResponseListener listener) {
        makeRequest(context, ENDPOINT_USERS, Request.Method.GET, listener, null);
    }

    // GET METHOD
    public static void getUserById(Context context, ResponseListener listener, int id) {
        makeRequest(context, ENDPOINT_USERS_ID.replace(ID, Integer.toString(id)), Request.Method.GET, listener, null);
    }

    // GET METHOD
    // Searches users with a name, last name or email matching the value of the query parameter
    public static void getUsersFiltered(Context context, ResponseListener listener, String s) {
        String url = ENDPOINT_USERS_SEARCH + "?s=" + s;

        makeRequest(context, url, Request.Method.GET, listener, null);
    }

    // GET METHOD
    // Gets the user statistics: average score given for events "puntuation", number of comments written for
    // events, and percentage of users with lower number of comments than this user.
    public static void getUserStats(Context context, ResponseListener listener, int id) {
        makeRequest(context, ENDPOINT_USERS_STATS.replace(ID, Integer.toString(id)), Request.Method.GET, listener, null);
    }

    // PUT METHOD
    // Edits specified fields of the authenticated user
    public static void editCurrentUser(Context context, ResponseListener listener, Map<String, String> map) {
        makeRequest(context, ENDPOINT_USERS, Request.Method.PUT, listener, map);
    }

    // DELETE METHOD
    public static void deleteUser(Context context, ResponseListener listener) {
        makeRequest(context, ENDPOINT_USERS, Request.Method.DELETE, listener, null);
    }

    // GET METHOD
    // Gets all events created by user with matching id
    public static void getAllEventsFromUser(Context context, ResponseListener listener, int id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", Integer.toString(id));

        makeRequest(context, ENDPOINT_USERS_EVENTS.replace(ID, Integer.toString(id)), Request.Method.GET, listener, map);
    }

    // GET METHOD
    // Gets events in the future created by user with matching id
    public static void getAllFutureEventsFromUser(Context context, ResponseListener listener, int id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", Integer.toString(id));

        makeRequest(context, ENDPOINT_USERS_EV_FUTURE.replace(ID, Integer.toString(id)), Request.Method.GET, listener, map);
    }

    // GET METHOD
    // Gets events in the past created by user with matching id
    public static void getAllPastEventsFromUser(Context context, ResponseListener listener, int id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", Integer.toString(id));

        makeRequest(context, ENDPOINT_USERS_EV_END.replace(ID, Integer.toString(id)), Request.Method.GET, listener, map);
    }

    // GET METHOD
    // Gets events occurring right now created by user with matching id
    public static void getAllCurrentEventsFromUser(Context context, ResponseListener listener, int id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", Integer.toString(id));

        makeRequest(context, ENDPOINT_USERS_EV_CURRENT.replace(ID, Integer.toString(id)), Request.Method.GET, listener, map);
    }

    // GET METHOD
    // Gets all events with assistance by user with matching id
    public static void getAllEventsWithAssistanceFromUser(Context context, ResponseListener listener, int id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", Integer.toString(id));

        makeRequest(context, ENDPOINT_USERS_ASSIS.replace(ID, Integer.toString(id)), Request.Method.GET, listener, map);
    }

    // GET METHOD
    // Gets all future events with assistance by user with matching id
    public static void getAllFutureEventsWithAssistanceFromUser(Context context, ResponseListener listener, int id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", Integer.toString(id));

        makeRequest(context, ENDPOINT_USERS_ASSIS_FUTURE.replace(ID, Integer.toString(id)), Request.Method.GET, listener, map);
    }

    // GET METHOD
    // Gets all past events with assistance by user with matching id
    public static void getAllPastEventsWithAssistanceFromUser(Context context, ResponseListener listener, int id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", Integer.toString(id));

        makeRequest(context, ENDPOINT_USERS_ASSIS_END.replace(ID, Integer.toString(id)), Request.Method.GET, listener, map);
    }

    // GET METHOD
    // Gets all users who are friends with user with matching id
    public static void getAllFriendsFromUser(Context context, ResponseListener listener, int id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", Integer.toString(id));

        makeRequest(context, ENDPOINT_USERS_FRIENDS.replace(ID, Integer.toString(id)), Request.Method.GET, listener, map);
    }

    // POST METHOD
    // Creates a new event
    public static void postEvent(Context context, ResponseListener listener, String name, String image,
                          String location, String description, String eventStart_date,
                          String eventEnd_date, String n_participators, String type) {

        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("image", image);
        map.put("location", location);
        map.put("description", description);
        map.put("eventStart_date", eventStart_date);
        map.put("eventEnd_date", eventEnd_date);
        map.put("n_participators", n_participators);
        map.put("type", type);

        makeRequest(context, ENDPOINT_EVENTS, Request.Method.POST, listener, map);
    }

    // GET METHOD
    // Gets all future events
    public static void getAllEvent(Context context, ResponseListener listener) {
        String url = ENDPOINT_EVENTS_SEARCH + "?location=";
        makeRequest(context, url, Request.Method.GET, listener, null);
    }

    // GET METHOD
    // Gets all future events
    public static void getAllCurrentEvent(Context context, ResponseListener listener) {
        makeRequest(context, ENDPOINT_EVENTS, Request.Method.GET, listener, null);
    }

    // GET METHOD
    // Gets event by id
    public static void getEventById(Context context, ResponseListener listener, int id) {
        makeRequest(context, ENDPOINT_EVENTS_ID.replace(ID, Integer.toString(id)), Request.Method.GET, listener, null);
    }

    // GET METHOD
    // Gets all future events in descending order based on the average score of the creator's old events.
    public static void getEventsBest(Context context, ResponseListener listener) {
        makeRequest(context, ENDPOINT_EVENTS_BEST, Request.Method.GET, listener, null);
    }

    // GET METHOD
    // Searches events with location, keyword in name, or date containing or matching the values of the query
    // parameters.
    public static void getEventsSearch(Context context, ResponseListener listener, String location, String keyword, String date) {
        String url = ENDPOINT_EVENTS_SEARCH + "?location=" + location + "&keyword=" + keyword + "&date=" + date;
        StringBuilder url2 = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();

        if (!location.isEmpty()) {
            list.add("location=" + location);
        }
        if (!keyword.isEmpty()) {
            list.add("keyword=" + keyword);
        }
        if (!date.isEmpty()) {
            list.add("date=" + date);
        }

        url2.append(ENDPOINT_EVENTS_SEARCH).append("?").append(list.get(0));
        if (list.size() != 1) {
            for (int i = 1; i < list.size(); i++) {
                url2.append("&").append(list.get(i));
            }
        }

        makeRequest(context, url2.toString(), Request.Method.GET, listener, null);
    }

    // PUT METHOD
    // Edits specified fields of the event with matching id
    public static void putEventById(Context context, ResponseListener listener, int id, Map<String, String> map) {
        makeRequest(context, ENDPOINT_EVENTS_ID.replace(ID, Integer.toString(id)), Request.Method.PUT, listener, map);
    }

    // DELETE METHOD
    // Deletes event with matching id
    public static void deleteEventById(Context context, ResponseListener listener, int id) {
        makeRequest(context, ENDPOINT_EVENTS_ID.replace(ID, Integer.toString(id)), Request.Method.DELETE, listener, null);
    }

    // GET METHOD
    // Gets all assistances for event with matching id
    public static void getEventAssistancesById(Context context, ResponseListener listener, int id) {
        makeRequest(context, ENDPOINT_EVENTS_ASSIS.replace(ID, Integer.toString(id)), Request.Method.GET, listener, null);
    }

    // GET METHOD
    // Gets assistance of user with matching id for event with matching id
    public static void getEventMatchingId(Context context, ResponseListener listener, int event_id, int user_id) {
        Map<String, String> map = new HashMap<>();
        map.put("event_id", Integer.toString(event_id));
        map.put("user_id", Integer.toString(user_id));

        makeRequest(context, ENDPOINT_EVENTS_ONE_ASSIS.replace(USER_ID, Integer.toString(user_id)).replace(EVENT_ID, Integer.toString(event_id)), Request.Method.GET, listener, map);

    }

    // POST EVENT
    // Creates assistance of authenticated user for event with matching id
    public static void postEventAssitanceById(Context context, ResponseListener listener, int id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", Integer.toString(id));

        makeRequest(context, ENDPOINT_EVENTS_ASSIS.replace(ID, Integer.toString(id)), Request.Method.POST, listener, map);
    }

    // PUT METHOD
    // Edits assistance of authenticated user for the event with matching id
    public static void editEventAssistanceById(Context context, ResponseListener listener, int id, Map<String, String> map) {
        makeRequest(context, ENDPOINT_EVENTS_ASSIS.replace(ID, Integer.toString(id)), Request.Method.PUT, listener, map);
    }

    // DELETE METHOD
    // Deletes assistance of authenticated user for event with matching id
    public static void deleteEventAssistanceById(Context context, ResponseListener listener, int id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", Integer.toString(id));

        makeRequest(context, ENDPOINT_EVENTS_ASSIS.replace(ID, Integer.toString(id)), Request.Method.DELETE, listener, map);
    }

    // GET METHOD
    // Gets assistance of user with matching id for event with matching id
    public static void getAssistancesById(Context context, ResponseListener listener, int user_id, int event_id) {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", Integer.toString(user_id));
        map.put("event_id", Integer.toString(event_id));

        makeRequest(context, ENDPOINT_ASSISTANCES.replace(USER_ID, String.valueOf(user_id)).replace(EVENT_ID,  String.valueOf(event_id)), Request.Method.GET, listener, map);

    }

    // POST METHOD
    // Creates assistance of user with matching id for event with matching id
    public static void createAssistance(Context context, ResponseListener listener, int event_id) {
        makeRequest(context, ENDPOINT_EVENTS_ASSIS.replace(ID, String.valueOf(event_id)), Request.Method.POST, listener, null);
    }

    // PUT METHOD
    // Edits assistance of user with matching id for the event with matching id
    public static void putCommentOrRate(Context context, ResponseListener listener, int user_id, int event_id, Map<String, String> map) {
        makeRequest(context, ENDPOINT_ASSISTANCES.replace(USER_ID, String.valueOf(user_id)).replace(EVENT_ID,  String.valueOf(event_id)), Request.Method.PUT, listener, map);
    }

    // DELETE METHOD
    // Deletes assistance of user with matching id for the event with matching id
    public static void deleteAssistance(Context context, ResponseListener listener, int event_id) {
        makeRequest(context, ENDPOINT_EVENTS_ASSIS.replace(ID, String.valueOf(event_id)), Request.Method.DELETE, listener, null);
    }

    // POST METHOD
    // Creates message
    public static void createMessage(Context context, ResponseListener listener, String content, int user_id_send, int user_id_recived) {
        Map<String, String> map = new HashMap<>();
        map.put("content", content);
        map.put("user_id_send", Integer.toString(user_id_send));
        map.put("user_id_recived", Integer.toString(user_id_recived));

        makeRequest(context, ENDPOINT_MESSAGES, Request.Method.POST, listener, map);
    }

    // GET METHOD
    // Gets all external users that are messaging the authenticated user
    public static void getUsersMessaging(Context context, ResponseListener listener) {
        makeRequest(context, ENDPOINT_MESS_USERS, Request.Method.GET, listener, null);

    }

    // GET METHOD
    // Gets all messages between the external user with matching id and the authenticated user
    public static void getAllMessages(Context context, ResponseListener listener, int id) {
        makeRequest(context, ENDPOINT_MESS_ID.replace(ID, Integer.toString(id)), Request.Method.GET, listener, null);
    }

    // GET METHOD
    // Gets all external users that have sent a friendship request to the authenticated user
    public static void getFriendRequests(Context context, ResponseListener listener) {
        makeRequest(context, ENDPOINT_FRIENDS_REQ, Request.Method.GET, listener, null);
    }

    // GET METHOD
    // Gets all external users that are friends with the authenticated user
    public static void getAllFriends(Context context, ResponseListener listener) {
        makeRequest(context, ENDPOINT_FRIENDS, Request.Method.GET, listener, null);
    }

    // POST METHOD
    // Creates friendship request to external user with match id from authenticated user
    public static void createFriendship(Context context, ResponseListener listener, int id) {
        makeRequest(context, ENDPOINT_FRIENDS_ID.replace(ID, Integer.toString(id)), Request.Method.POST, listener, null);
    }

    // PUT METHOD
    // Accepts friendship request from external user to authenticated user
    public static void acceptFriendshipRequest(Context context, ResponseListener listener, int id) {
        makeRequest(context, ENDPOINT_FRIENDS_ID.replace(ID, Integer.toString(id)), Request.Method.PUT, listener, null);
    }

    // DELETE METHOD
    // Rejects friendship request from external user to authenticated user
    public static void declineFriendshipRequest(Context context, ResponseListener listener, int id) {
        makeRequest(context, ENDPOINT_FRIENDS_ID.replace(ID, Integer.toString(id)), Request.Method.DELETE, listener, null);
    }

    // REQUEST METHOD
    private static void makeRequest(Context context, String url, int requestType, ResponseListener listener, Map<String, String> params) {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(requestType, url, listener::onResponse, listener::onError) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type","application/x-www-form-urlencoded");

                if (token != null) {
                    params.put("Authorization", BEARER + APIManager.token);
                }

                return params;
            }
        };

        queue.add(stringRequest);
    }
}

