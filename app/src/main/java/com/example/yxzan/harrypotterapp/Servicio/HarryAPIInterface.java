package com.example.yxzan.harrypotterapp.Servicio;

import com.example.yxzan.harrypotterapp.Models.EmployeeModel;
import com.example.yxzan.harrypotterapp.Models.MovieModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Desarrollo on 23/9/2016.
 */
public interface HarryAPIInterface {
    @GET("movies")
    Call<ArrayList<MovieModel>>getAllMovies();

    @GET("movies/{idMovie}")
    Call<MovieModel> getMovie(@Path("idMovie") int idMovie);

    @GET("movies/{idMovie}/employees")
    Call<ArrayList<EmployeeModel>> getMovieAllEmployees(@Path("idMovie") int idMovie);

    @GET("employees")
    Call<ArrayList<EmployeeModel>> getAllEmployees();

    @GET("employees/{idEmployee}")
    Call<EmployeeModel> getEmployee(@Path("idEmployee") int idEmployee);

    @GET("employees/{idEmployee}/movies")
    Call<ArrayList<MovieModel>> getEmployeeAllMovie(@Path("idEmployee") int idEmployee);

}
