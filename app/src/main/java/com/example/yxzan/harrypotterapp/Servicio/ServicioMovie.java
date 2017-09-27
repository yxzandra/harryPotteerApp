package com.example.yxzan.harrypotterapp.Servicio;

import com.example.yxzan.harrypotterapp.Models.EmployeeModel;
import com.example.yxzan.harrypotterapp.Models.MovieModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yxzan on 9/25/2016.
 */
public class ServicioMovie {
    public ServicioMovie() {
    }

    public static HarryAPIInterface llamadaServicio() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://harrypotterapi.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(HarryAPIInterface.class);
    }

    public boolean allMovies() {
        HarryAPIInterface harryAPIInterface = llamadaServicio();
        harryAPIInterface.getAllMovies().enqueue(new Callback<ArrayList<MovieModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MovieModel>> call, Response<ArrayList<MovieModel>> response) {

            }

            @Override
            public void onFailure(Call<ArrayList<MovieModel>> call, Throwable t) {

            }
        });
        return true;

    }

    public boolean movie(int idMovie) {
        HarryAPIInterface harryAPIInterface = llamadaServicio();
        harryAPIInterface.getMovie(idMovie).enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });

        return true;

    }

    public boolean movieAllEmployees(int idMovie) {
        HarryAPIInterface harryAPIInterface = llamadaServicio();
        harryAPIInterface.getMovieAllEmployees(idMovie).enqueue(new Callback<ArrayList<EmployeeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<EmployeeModel>> call, Response<ArrayList<EmployeeModel>> response) {

            }

            @Override
            public void onFailure(Call<ArrayList<EmployeeModel>> call, Throwable t) {

            }
        });
        return true;
    }

}


