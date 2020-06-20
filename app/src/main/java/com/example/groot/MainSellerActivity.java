package com.example.groot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.groot.Fragment.DocumentFragment;
import com.example.groot.Fragment.MainFragment;
import com.example.groot.Fragment.MapFragment;
import com.example.groot.Fragment.SellerFragment;
import com.example.groot.Fragment.SettingFragment;
import com.example.groot.service.DB.DBManager;
import com.example.groot.service.WebApiService;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainSellerActivity extends AppCompatActivity {
    private ImageButton btn_home, btn_setting, btn_map, btn_camera, btn_document, btn_seller;
    private final String API_URL_BASE = "http://222.239.199.86:8080/";
    private PopupWindow mPopupWindow ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_navigator);

        btn_home = (ImageButton) findViewById(R.id.btn_home);
        btn_setting = (ImageButton) findViewById(R.id.btn_setting);
        btn_map = (ImageButton) findViewById(R.id.btn_map);
        btn_camera = (ImageButton) findViewById(R.id.btn_camera);
        btn_document = (ImageButton) findViewById(R.id.btn_document);
        btn_seller = (ImageButton) findViewById(R.id.btn_seller);

        final MainFragment fragement_main = new MainFragment();
        setFragment(fragement_main);

        // 홈 버튼 클릭시 수행
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragment fragement_main = new MainFragment();
                setFragment(fragement_main);
            }
        });

        // 설정 버튼 클릭시 수행
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingFragment fragement_setting = new SettingFragment();
                setFragment(fragement_setting);
            }
        });

        // 지도 버튼 클릭시 수행
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapFragment fragement_map = new MapFragment();
                setFragment(fragement_map);

            }
        });

        // 카메라 버튼 클릭시 수행
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popupView = getLayoutInflater().inflate(R.layout.dialog_activity, null);
                mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //popupView 에서 (LinearLayout 을 사용) 레이아웃이 둘러싸고 있는 컨텐츠의 크기 만큼 팝업 크기를 지정
                mPopupWindow.setFocusable(true); // 외부 영역 선택시 PopUp 종료
                mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                final ViewGroup root = (ViewGroup) getWindow().getDecorView().getRootView();
                applyDim(root, 0.5f); // 팝업 뒤 배경 어둡게 적용

                mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // 팝업을 종료했을 때
                    @Override
                    public void onDismiss() {
                        clearDim(root); // 팝업 뒤 배경 원래대로
                    }
                });

                Button cancel = (Button) popupView.findViewById(R.id.Camera);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                        clearDim(root);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // 카메라 사용하기
                        startActivityForResult(intent, 1);

                    }
                });

                Button ok = (Button) popupView.findViewById(R.id.Album);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPopupWindow.dismiss();
                        clearDim(root);
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(intent, 2);
                    }
                });

            }
        });

        // 문서 버튼 클릭시 수행
        btn_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentFragment fragement_document = new DocumentFragment();
                setFragment(fragement_document);
            }
        });

        // 판매자 버튼 클릭시 수행
        btn_seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SellerFragment fragement_seller = new SellerFragment();
                setFragment(fragement_seller);
            }
        });
    }

    public static void applyDim(@NonNull ViewGroup parent, float dimAmount){
        Drawable dim = new ColorDrawable(Color.BLACK);
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha((int) (255 * dimAmount));

        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.add(dim);
    }

    public static void clearDim(@NonNull ViewGroup parent) {
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.clear();
    }

    public void setFragment(Fragment frag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, frag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            requestFlowerData(imageBitmap);
        }

        if (requestCode == 2 && resultCode == RESULT_OK && data.getData() != null) {
            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                requestFlowerData(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void requestFlowerData(final Bitmap imageBitmap) {
        File storage = getCacheDir();
        String fileName = DBManager.getUserInfo()+".jpg";

        File file = new File(storage, fileName);
        try { //bitmap을 파일로 만드는 작업
            file.createNewFile();
            OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL_BASE)
                .build();
        WebApiService service = retrofit.create(WebApiService.class);

        // creates RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("picture", file.getName(), requestFile);
        System.out.println(file.getName());

        Call<ResponseBody> call = service.postFile(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                String data = null;
                try {
                    JSONParser parser = new JSONParser();
                    String responseJson = response.body().string();
                    JSONObject obj = (JSONObject)parser.parse(responseJson);
                    data = (String)obj.get("image");
                } catch (Exception e){
                    e.printStackTrace();
                }

                if(data == null){
                    System.err.println("data를 불러오지 못함");
                }

                DocumentFragment fragement_document = new DocumentFragment();
                setFragment(fragement_document);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println(t.getMessage());
            }

        });
    }
}
