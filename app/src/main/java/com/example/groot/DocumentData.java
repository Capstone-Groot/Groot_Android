package com.example.groot;

import android.graphics.Bitmap;

public class DocumentData {

    private String createdDate;
    private Long id;
    private String type;
    private Bitmap iv_flowerImage;

//    private String tv_name;
//    private String tv_content;

    public DocumentData( String createdDate, Long id, String type) {
//        this.tv_name = tv_name;
//        this.tv_content = tv_content;

//        this.iv_flowerImage = iv_flowerImage;
        this.createdDate = createdDate;
        this.id = id;
        this.type = type;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Bitmap getIv_flowerImage() {
        return iv_flowerImage;
    }

    public void setIv_flowerImage(Bitmap iv_flowerImage) {
        this.iv_flowerImage = iv_flowerImage;
    }
//
//    public String getTv_name() {
//        return tv_name;
//    }
//
//    public void setTv_name(String tv_name) {
//        this.tv_name = tv_name;
//    }
//
//    public String getTv_content() {
//        return tv_content;
//    }
//
//    public void setTv_content(String tv_content) {
//        this.tv_content = tv_content;
//    }
}
