package com.example.auladm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.auladm.control.GetResponse;
import com.example.auladm.control.VolleyCallback;
import com.example.auladm.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GetResponse getResponse = new GetResponse(this);
    private List<User> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView jsonText = findViewById(R.id.jsonText);
        Button nextScreen = findViewById(R.id.btNext);


        nextScreen.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Activity3.class);
            startActivity(intent);
        });


        Button btn = findViewById(R.id.btBuscaTodos);
        btn.setOnClickListener(view -> {
            getResponse.requisicao("users", new VolleyCallback() {
                @Override
                public void onSuccess(JSONArray result) {
                    for (int i = 0; i < result.length(); i++){
                        try {
                            JSONObject json = result.getJSONObject(i);

                            User user = new User(
                                    json.getInt("id"),
                                    json.getString("name"),
                                    json.getString("username"),
                                    json.getString("email"));
                            users.add(user);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    jsonText.setText(users.toString());
                }

                @Override
                public void onError(String errorMessage) {
                    Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        });

    }
}