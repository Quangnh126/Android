package com.example.btl_android;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    public EditText etUsername, etPassword;
    public String username, password;
    public String URL = "http://192.168.101.9:8080/Android/login.php";
//    public String URL = "http://10.21.160.54:8080/Android/login.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.username = "";
        this.password = "";
        this.etUsername = findViewById(R.id.etUsername);
        this.etPassword = findViewById(R.id.etPassword);

    }

    public void login(View view) {
        this.username = this.etUsername.getText().toString().trim();
        this.password = this.etPassword.getText().toString().trim();
        System.out.println(this.username);
        System.out.println(this.password);

        if(!this.username.equals("") && !this.password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                    Toast.makeText(Login.this, "Invalid Login username/password", Toast.LENGTH_SHORT).show();
                    if (response.equals("success")) {
                        Toast.makeText(Login.this, "Login username/password", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("fail")) {
                        Toast.makeText(Login.this, "Invalid Login username/password", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error.toString());
                    Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError{
                    System.out.println(username);
                    System.out.println(password);
                    Map<String, String> data = new HashMap<>();
                    data.put("username", username);
                    data.put("password", password);

                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
            Toast.makeText(Login.this, "Invalid Login username/password", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Field can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}