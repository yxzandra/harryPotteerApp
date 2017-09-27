package com.example.yxzan.harrypotterapp.Models;

import java.util.Date;

/**
 * Created by yxzan on 9/25/2016.
 */
public class MovieModel {
    int idMovie,running_time;
    Date release_date;
    String name, image_url, created_at, updated_at, budget, gross_revenue ;

    public MovieModel(int idMovie, int running_time, Date release_date, String budget,
                      String gross_revenue, String name, String image_url, String created_at,
                      String updated_at) {
        this.idMovie = idMovie;
        this.running_time = running_time;
        this.release_date = release_date;
        this.budget = budget;
        this.gross_revenue = gross_revenue;
        this.name = name;
        this.image_url = image_url;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public int getRunning_time() {
        return running_time;
    }

    public void setRunning_time(int running_time) {
        this.running_time = running_time;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getGross_revenue() {
        return gross_revenue;
    }

    public void setGross_revenue(String gross_revenue) {
        this.gross_revenue = gross_revenue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
