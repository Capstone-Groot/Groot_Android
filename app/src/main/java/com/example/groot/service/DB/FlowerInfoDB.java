package com.example.groot.service.DB;

import com.example.groot.service.DB.flower.Daisy;
import com.example.groot.service.DB.flower.Dandelion;
import com.example.groot.service.DB.flower.None;
import com.example.groot.service.DB.flower.Rose;
import com.example.groot.service.DB.flower.SunFlower;
import com.example.groot.service.DB.flower.Tulip;

public class FlowerInfoDB {

    public static FlowerInfo getFlowerInfoFactory(String kindOfFlower){
        switch (kindOfFlower){
            case "sunflower":
                return new SunFlower();
            case "daisy":
                return new Daisy();
            case "dandelion":
                return new Dandelion();
            case "tulip":
                return new Tulip();
            case "rose":
                return new Rose();
        }

        return new None();
    }
}
