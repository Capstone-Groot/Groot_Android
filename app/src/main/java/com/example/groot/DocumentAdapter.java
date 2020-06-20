package com.example.groot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groot.Fragment.Document_explain_Fragment;

import java.util.ArrayList;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.CustomViewHolder> {
    private ArrayList<DocumentData> arrayList;

    public DocumentAdapter(ArrayList<DocumentData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    // 생성될 때
    public DocumentAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        ///api 요청
        //requestHistory(parent, DBManager.getUserInfo());
        return holder;
    }

    @Override
    // 추가될 때 생명주기
    public void onBindViewHolder(@NonNull final DocumentAdapter.CustomViewHolder holder, int position) {
        holder.iv_flowerImage.setImageBitmap(arrayList.get(position).getIv_flowerImage());

        String flowerName = EnglishToKorean(arrayList.get(position).getType());
        holder.tv_name.setText(flowerName);

        String date = arrayList.get(position).getCreatedDate();
        date = date.substring(0,4) + "년 " + date.substring(5,7)+"월 " + date.substring(8,10) + "일 " + date.substring(11,19);
        holder.tv_content.setText(date);
        final long id = arrayList.get(position).getId();

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("fromFlower", KoreanToEnglish(holder.tv_name.getText().toString()));
                bundle.putString("flowerId", Long.toString(id));

                String curName = holder.tv_name.getText().toString();
                FragmentTransaction transaction = ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction();

                Document_explain_Fragment def = new Document_explain_Fragment();
                def.setArguments(bundle);

                transaction.replace(R.id.frame, def);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //꾹 누르면 삭제
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(holder.getAdapterPosition());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position) {
        try {
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_flowerImage;
        protected TextView tv_name;
        protected TextView tv_content;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_flowerImage = (ImageView) itemView.findViewById(R.id.iv_flowerImage);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

    public  String EnglishToKorean(String flowerName) {
        switch (flowerName){
            case "tulip" :
                flowerName = "튤립";
                break;
            case "daisy" :
                flowerName = "데이지";
                break;
            case "dandelion" :
                flowerName = "민들레";
                break;
            case "rose" :
                flowerName = "장미";
                break;
            case "sunflower" :
                flowerName = "해바라기";
                break;
            default:
                flowerName = "None";
                break;
        }
        return flowerName;
    }

    public  String KoreanToEnglish(String flowerName) {
        switch (flowerName){
            case "튤립" :
                flowerName = "tulip";
                break;
            case "데이지" :
                flowerName = "daisy";
                break;
            case "민들레" :
                flowerName = "dandelion";
                break;
            case "장미" :
                flowerName = "rose";
                break;
            case "해바라기" :
                flowerName = "sunflower";
                break;
            default:
                flowerName = "None";
                break;
        }
        return flowerName;
    }
}
