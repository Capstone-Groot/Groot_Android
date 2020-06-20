package com.example.groot.service.DB;

import java.util.ArrayList;
import java.util.List;

public class FlowerInfo {

    private List<Integer> imageList;
    private String name;
    private String explain;
    private String language;
    private String classification;

    public FlowerInfo(String name, String explain, String language, String classification) {
        this.imageList = new ArrayList<>();
        this.name = name;
        this.explain = explain;
        this.language = language;
        this.classification = classification;
    }

    public void insertImage(List<Integer> flowers){
        imageList.addAll(flowers);
    }

    public List<Integer> getImageList() {
        return imageList;
    }

    public String getClassification() {
        return classification;
    }

    public String getExplain() {
        return explain;
    }

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }
}
