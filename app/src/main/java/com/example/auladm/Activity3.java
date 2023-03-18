package com.example.auladm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.auladm.control.GetResponse;
import com.example.auladm.control.VolleyCallback;
import com.example.auladm.models.Todo;
import com.example.auladm.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity3 extends AppCompatActivity {

    List<Todo> todos = new ArrayList<>();
    private GetResponse getResponse = new GetResponse(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        TextView jsonText = findViewById(R.id.jsonText2);
        Button nextScreen = findViewById(R.id.btNext2);
        Button btn = findViewById(R.id.btBuscaTodos2);


//        nextScreen.setOnClickListener(view -> {
//            Intent intent = new Intent(getApplicationContext(), Activity4.class);
//            startActivity(intent);
//        });



        btn.setOnClickListener(view -> {
            getResponse.requisicao("todos", new VolleyCallback() {
                @Override
                public void onSuccess(JSONArray result) {
                    for (int i = 0; i < result.length(); i++){
                        try {
                            JSONObject json = result.getJSONObject(i);

                            Todo todo = new Todo(
                                    json.getInt("userId"),
                                    json.getInt("id"),
                                    json.getString("title"),
                                    json.getBoolean("completed"));
                            todos.add(todo);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    jsonText.setText(todos.toString());
                }

                @Override
                public void onError(String errorMessage) {
                    Toast.makeText(Activity3.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        });

    }
}