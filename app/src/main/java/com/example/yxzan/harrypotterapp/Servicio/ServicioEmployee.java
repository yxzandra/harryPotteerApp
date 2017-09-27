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
public class ServicioEmployee {
    public ServicioEmployee() {
    }
    public HarryAPIInterface llamadaServicio() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("harrypotterapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(HarryAPIInterface.class);
    }

    public boolean allEmployee() {
        HarryAPIInterface harryAPIInterface = llamadaServicio();
        harryAPIInterface.getAllEmployees().enqueue(new Callback<ArrayList<EmployeeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<EmployeeModel>> call, Response<ArrayList<EmployeeModel>> response) {

            }

            @Override
            public void onFailure(Call<ArrayList<EmployeeModel>> call, Throwable t) {

            }
        });
        return true;
    }

    public boolean employee (int idEmployee){
        HarryAPIInterface harryAPIInterface = llamadaServicio();
        harryAPIInterface.getEmployee(idEmployee).enqueue(new Callback<EmployeeModel>() {
            @Override
            public void onResponse(Call<EmployeeModel> call, Response<EmployeeModel> response) {

            }

            @Override
            public void onFailure(Call<EmployeeModel> call, Throwable t) {

            }
        });
        return true;
    }

    public boolean EmployeeAllMovie(int idEmployee){
        HarryAPIInterface harryAPIInterface = llamadaServicio();
        harryAPIInterface.getEmployeeAllMovie(idEmployee).enqueue(new Callback<ArrayList<MovieModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MovieModel>> call, Response<ArrayList<MovieModel>> response) {

            }

            @Override
            public void onFailure(Call<ArrayList<MovieModel>> call, Throwable t) {

            }
        });
        return true;
    }
}
