package com.example.groot.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.groot.DocumentAdapter;
import com.example.groot.DocumentData;
import com.example.groot.R;
import com.example.groot.service.DB.DBManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentFragment extends Fragment {

    private ArrayList<DocumentData> arrayList = new ArrayList<>();
    private DocumentAdapter documentAdapter = new DocumentAdapter(arrayList);
    private List<History> histories;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Bitmap bitmap;

    public DocumentFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_document, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(documentAdapter);

        requestHistory(view, DBManager.getUserInfo());

        return view;
    }

//    public void setCaptureImage(Bitmap flowerImage, String name, String content) {
//        DocumentData documentData = new DocumentData(flowerImage, name, content);
//        arrayList.add(documentData);
//
//    }

    private void requestImage(final Long id, DocumentData documentData){
        Thread uThread = new Thread() {
            @Override
            public void run(){
                try{

                    URL url = new URL("http://222.239.199.86:8080/api/flowers/"+id);

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream(); //inputStream 값 가져오기

                    bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 반환

                }catch (MalformedURLException e){
                    e.printStackTrace();

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        };
        uThread.start();

        try{
            uThread.join();
            documentData.setIv_flowerImage(bitmap);
        }catch (InterruptedException e){

            e.printStackTrace();

        }
    }

    private void requestHistory(final View v, String userId) {

        RequestQueue queue = Volley.newRequestQueue(v.getContext());
        String url = "http://222.239.199.86:8080/api/history/" + userId;
        final JSONObject body = new JSONObject();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        histories = parserJson(response);

                        for (int i = 0; i < histories.size(); i++) {
                            History history = histories.get(i);
                            DocumentData documentData = new DocumentData(history.createdDate, history.id, history.type);

                           requestImage(history.id, documentData);
                            arrayList.add(documentData);
                        }

                        //ㅇ
                        documentAdapter.notifyDataSetChanged();

                        System.out.println(histories);
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

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private List<History> parserJson(String response) {
        System.out.println(response);
        List<History> histories = new ArrayList<>();
        try {

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(response);
            JSONArray jsonArray = (JSONArray) obj;

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                histories.add(new History((String) jsonObject.get("createdDate"), (Long) jsonObject.get("id"), (String) jsonObject.get("type")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return histories;
    }

    class History {

        String createdDate;
        Long id;
        String type;

        History(String createdDate, Long id, String type) {
            this.createdDate = createdDate;
            this.id = id;
            this.type = type;
        }
    }

}
