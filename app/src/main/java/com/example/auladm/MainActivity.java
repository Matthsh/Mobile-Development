package com.example.auladm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.auladm.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONArray>, Response.ErrorListener {
    private EditText edit;
    private List<User> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = findViewById(R.id.name);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://jsonplaceholder.typicode.com/users/2", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        User user = null;
                        try {
                            user = new User(
                                    response.getInt("id"),
                                    response.getString("name"),
                                    response.getString("username"),
                                    response.getString("email")
                            );
                            edit.setText(user.getName());
                        } catch (JSONException e) {
                            throw new RuntimeException("Erro no JSON: " + e);
                        }
                        System.out.println("Chegou");

                    }
                }, this);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
        Button btn = findViewById(R.id.btBuscaTodos);
        btn.setOnClickListener(view ->  {
            getAllUsers();
        });
    }
    private void getAllUsers() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, "http://jsonplaceholder.typicode.com/users", null,
                this, this);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Ocorreu uma falha na requisição" + error.getMessage(), Toast.LENGTH_LONG);
    }

    @Override
    public void onResponse(JSONArray response) {
        for (int i = 0; i < response.length(); i++) {
            User user = null;
            try {
                JSONObject json = response.getJSONObject(i);
                user = new User(
                        json.getInt("id"),
                        json.getString("name"),
                        json.getString("username"),
                        json.getString("email")
                );
                users.add(user);
            } catch (JSONException e) {
                throw new RuntimeException("Erro no JSON: " + e);
            }
        }
        System.out.println(users);
    }
}