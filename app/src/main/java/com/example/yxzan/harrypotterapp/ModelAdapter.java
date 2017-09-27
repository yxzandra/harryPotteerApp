package com.example.yxzan.harrypotterapp;
/**
 * POJO de las chicas
 */
public class ModelAdapter {

    private String name;
    private String imgUrl;


    public ModelAdapter(String name, String imgUrl) {
        this.imgUrl = imgUrl;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
