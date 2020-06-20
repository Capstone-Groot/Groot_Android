package com.example.groot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.groot.service.DB.DBManager;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText et_id, et_password;
    private Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        // 회원가입 버튼을 클릭 시 수행
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userId = et_id.getText().toString();
                String userPassword = et_password.getText().toString();

                requestLogin(userId, userPassword);
            }
        });
    }

    private void requestLogin(final String userId, final String password) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://222.239.199.86:8080/api/login";
        final JSONObject body = new JSONObject();
        try {
            body.put("userId", userId);
            body.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response);
                        JSONParser parser = new JSONParser();
                        String check = null;
                        try {
                            JSONObject object = (JSONObject) parser.parse(response);
                            check = (String) object.get("sellerCheck");
                            if(DBManager.login(userId, check)){
                                Intent intent = new Intent(LoginActivity.this, MainSellerActivity.class);
                                startActivity(intent);
                            }else{
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                return body.toString().getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        queue.add(stringRequest);
    }
}