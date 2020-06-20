package com.example.groot.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.groot.R;
import com.example.groot.ViewPagerAdapter;
import com.example.groot.service.DB.FlowerInfo;
import com.example.groot.service.DB.FlowerInfoDB;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document_explain_Fragment extends Fragment {

    private List<Integer> imageList;
    private View v;
    private ImageView compareImageView;
    private TextView flowerName;
    private TextView flowerExplain;
    private TextView flowerLanguage;
    private TextView flowerClassification;
    private TextView flowerStore;
    private String result;
    private String resultId;
    private Bitmap bitmap;
    private List<Store> stores;
    private String storesList = "";

    public Document_explain_Fragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_document_explain, container, false);
        compareImageView = v.findViewById(R.id.compareImageView);
        flowerName = v.findViewById(R.id.flowerName);
        flowerExplain = v.findViewById(R.id.flowerExplain);
        flowerLanguage = v.findViewById(R.id.flowerLanguage);
        flowerClassification = v.findViewById(R.id.flowerClassification);
        flowerStore = v.findViewById(R.id.flowerStore);


        if (getArguments() != null) {
            result = getArguments().getString("fromFlower");
            resultId = getArguments().getString(("flowerId"));

            FlowerInfoDB flowerInfoDB = new FlowerInfoDB();
            FlowerInfo flowerInfo = flowerInfoDB.getFlowerInfoFactory(result);

            imageList = flowerInfo.getImageList();

            ViewPager viewPager = v.findViewById(R.id.viewpager);
            viewPager.setClipToPadding(false);

            viewPager.setAdapter(new ViewPagerAdapter(getContext(), imageList));

            compareImageView.setBackground(new ShapeDrawable(new OvalShape()));
            compareImageView.setClipToOutline(true);
            compareImageView.bringToFront();

            flowerName.setText(flowerInfo.getName());
            flowerExplain.setText(flowerInfo.getExplain());
            flowerLanguage.setText(flowerInfo.getLanguage());
            flowerClassification.setText(flowerInfo.getClassification());
            requestImage(resultId, compareImageView);

            requestFlowerStore(KoreanToEnglish(flowerName.getText().toString()));


        }

        return v;
    }

    private void requestImage(final String id, ImageView view) {
        Thread uThread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://222.239.199.86:8080/api/flowers/" + id);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream(); //inputStream 값 가져오기

                    bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 반환

                } catch (MalformedURLException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        uThread.start();

        try {
            uThread.join();
            view.setImageBitmap(bitmap);
        } catch (InterruptedException e) {

            e.printStackTrace();

        }
    }

    private void requestFlowerStore(final String flowerName) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://222.239.199.86:8080/api/store/" + flowerName;
        final JSONObject body = new JSONObject();
        stores = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONParser parser = new JSONParser();
                            JSONArray array = (JSONArray) parser.parse(response);

                            for (int i = 0; i < array.size(); i++) {
                                JSONObject object = (JSONObject) array.get(i);
                                stores.add(new Store((String) object.get("name"), (String) object.get("address"), (String) object.get("phoneNumber"), (String) object.get("explain")));
                            }

                            System.out.println(stores.toString());

                            for (Store store : stores) {
                                storesList += store.name + " ";
                                storesList += store.phoneNumber + "\n";
                            }

                            flowerStore.setText(storesList);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        System.out.println(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override //response를 UTF8로 변경해주는 소스코드
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    // log error
                    return Response.error(new ParseError(e));
                } catch (Exception e) {
                    // log error
                    return Response.error(new ParseError(e));
                }
            }
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

    public String EnglishToKorean(String flowerName) {
        switch (flowerName) {
            case "tulip":
                flowerName = "튤립";
                break;
            case "daisy":
                flowerName = "데이지";
                break;
            case "dandelion":
                flowerName = "민들레";
                break;
            case "rose":
                flowerName = "장미";
                break;
            case "sunflower":
                flowerName = "해바라기";
                break;
            default:
                flowerName = "None";
                break;
        }
        return flowerName;
    }

    public String KoreanToEnglish(String flowerName) {
        switch (flowerName) {
            case "튤립":
                flowerName = "tulip";
                break;
            case "데이지":
                flowerName = "daisy";
                break;
            case "민들레":
                flowerName = "dandelion";
                break;
            case "장미":
                flowerName = "rose";
                break;
            case "해바라기":
                flowerName = "sunflower";
                break;
            default:
                flowerName = "None";
                break;
        }
        return flowerName;
    }

    class Store {
        private String name;
        private String address;
        private String phoneNumber;
        private String explain;

        Store(String name, String address, String phoneNumber, String explain) {
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.explain = explain;
        }
    }
}
