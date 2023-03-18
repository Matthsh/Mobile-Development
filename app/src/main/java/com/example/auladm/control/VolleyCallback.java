package com.example.auladm.control;

import org.json.JSONArray;

public interface VolleyCallback {
    void onSuccess(JSONArray result);
    void onError(String errorMessage);
}
