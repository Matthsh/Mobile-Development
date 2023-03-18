package com.example.auladm.control;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;



public class GetResponse {
    private Context mContext;
    JSONArray jsonObject;

    public GetResponse(Context context) {
        mContext = context;
    }
    public void requisicao(String path, VolleyCallback callback) {
        String url = "http://jsonplaceholder.typicode.com/" + path;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
            response -> {
                callback.onSuccess(response);
            },
                error -> callback.onError("Erro na requisição.")
        );
        RequestQueue queue = Volley.newRequestQueue(mContext);
        queue.add(request);
    }
}
