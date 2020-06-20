package com.example.groot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id, et_password, et_password2, et_name;
    private Button btn_register, btn_seller;
    private CheckBox cb_sellerAuth;
    private String sellerCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 아이디 값 찾아주기
        et_id = findViewById(R.id.et_id);
        et_password = findViewById(R.id.et_password);
        et_password2 = findViewById(R.id.et_password2);
        et_name = findViewById(R.id.et_name);
        btn_seller = findViewById(R.id.btn_seller);
        cb_sellerAuth = findViewById(R.id.cb_sellerAuth);

        // 회원가입 버튼 클릭 시 수정
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userId = et_id.getText().toString();
                String password = et_password.getText().toString();
                String password2 = et_password2.getText().toString();
                String userName = et_name.getText().toString();

                if(cb_sellerAuth.isChecked()) {
                    sellerCheck = "1";
                } else {
                    sellerCheck = "0";
                }

                if(password.equals(password2)){
                    requestRegister(userId, password, userName, sellerCheck);
                } else {
                    Toast.makeText(getApplicationContext(), "비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.uplus.co.kr/sys/comm/RetrieveAuthSMSPage.hpi")));
            }
        });
    }

    private void requestRegister(final String userId, final String password, final String userName, final String sellerCheck) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://222.239.199.86:8080/api/register";
        final JSONObject body = new JSONObject();
        try {
            body.put("userId", userId);
            body.put("password", password);
            body.put("userName", userName);
            body.put("sellerCheck", sellerCheck);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response);
                        // Display the first 500 characters of the response string.
//                        textView.setText("Response is: "+ response.substring(0,500));
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "중복되는 아이디 입니다.", Toast.LENGTH_SHORT).show();
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
