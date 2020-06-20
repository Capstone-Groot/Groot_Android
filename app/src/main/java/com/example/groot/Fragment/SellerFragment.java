package com.example.groot.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.groot.R;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SellerFragment extends Fragment {
    private TextView storeName, address, phoneNumber, storeExplain;
    private Button btn_send_seller_data;

    public SellerFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_seller, container, false);
        storeName = v.findViewById(R.id.storeName);
        address = v.findViewById(R.id.address);
        phoneNumber = v.findViewById(R.id.phoneNumber);
        storeExplain = v.findViewById(R.id.storeExplain);
        btn_send_seller_data = v.findViewById(R.id.btn_send_seller_data);

        btn_send_seller_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestStore(v, storeName.getText().toString(),
                        address.getText().toString(),
                        phoneNumber.getText().toString(),
                        storeExplain.getText().toString());
                storeName.setText("");
                address.setText("");
                phoneNumber.setText("");
                storeExplain.setText("");
            }
        });

        return v;
    }

    private void requestStore(final View v, final String storeName, final String address, final String phoneNumber, final String storeExplain) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://222.239.199.86:8080/api/store";
        final JSONObject body = new JSONObject();
        try {
            body.put("storeName", storeName);
            body.put("address", address);
            body.put("phoneNumber", phoneNumber);
            body.put("storeExplain", storeExplain);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);

                        Toast.makeText(getContext(), "전송 성공!!", Toast.LENGTH_SHORT).show();
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
