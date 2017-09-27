package com.example.yxzan.harrypotterapp;

/**
 * Created by Desarrollo on 23/9/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yxzan.harrypotterapp.Models.EmployeeModel;
import com.example.yxzan.harrypotterapp.Models.MovieModel;
import com.example.yxzan.harrypotterapp.Servicio.HarryAPIInterface;
import com.example.yxzan.harrypotterapp.Servicio.ServicioMovie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private static int positionMovie, movieOrEmployees;

    private static final String EXTRA_NAME = "com.example.yxzan.harrypotterapp.name";
    private static final String EXTRA_DRAWABLE = "com.example.yxzan.harrypotterapp.drawable";

    public static void createInstance(Activity activity, ModelAdapter title, int position, int movieOrEmployee) {
        positionMovie =  position;
        movieOrEmployees = movieOrEmployee;
        Intent intent = getLaunchIntent(activity, title);
        activity.startActivity(intent);
    }


    public static Intent getLaunchIntent(Context context, ModelAdapter modelAdapter) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_NAME, modelAdapter.getName());
        intent.putExtra(EXTRA_DRAWABLE, modelAdapter.getImgUrl());

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setToolbar();// Añadir action bar
        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String name = i.getStringExtra(EXTRA_NAME);
        String idDrawable = i.getStringExtra(EXTRA_DRAWABLE);

        CollapsingToolbarLayout collapser =
                (CollapsingToolbarLayout) findViewById(R.id.collapser);
        collapser.setTitle(name); // Cambiar título
        loadImageParallax(idDrawable);// Cargar Imagen

    }

    @Override
    protected void onResume() {
        super.onResume();

        HarryAPIInterface harryAPIInterface = ServicioMovie.llamadaServicio();
        Log.e("movieOrEmployees", String.valueOf(movieOrEmployees));
        if (movieOrEmployees == 1) {
            detallePelicula(harryAPIInterface);
        }else {
            detalleEmployee(harryAPIInterface);
        }
    }

    private void detalleEmployee(HarryAPIInterface harryAPIInterface) {
        harryAPIInterface.getEmployee(positionMovie+1).enqueue(new Callback<EmployeeModel>() {
            @Override
            public void onResponse(Call<EmployeeModel> call, Response<EmployeeModel> response) {
                TextView release_date = (TextView) findViewById(R.id.release_date);
                release_date.setText(String.valueOf(response.body().getFirst_name()+" "+response.body().getLast_name()));

                TextView titleRelease_date = (TextView) findViewById(R.id.textViewRelease_date);
                titleRelease_date.setText(R.string.activity_detail_budget_employee);

                TextView running_time = (TextView) findViewById(R.id.running_time);
                running_time.setText(String.valueOf(response.body().getCreated_at()));

                TextView titleRunning_time = (TextView) findViewById(R.id.textViewRunning_time);
                titleRunning_time.setText(R.string.activity_detail_created_at);

                TextView budget = (TextView) findViewById(R.id.budget);
                budget.setText(response.body().getUpdated_at());

                TextView titleBudget = (TextView) findViewById(R.id.textViewBudget);
                titleBudget.setText(R.string.activity_detail_updated_at);

                CardView created_at = (CardView) findViewById(R.id.card_created_at);
                created_at.setVisibility(View.GONE);

                CardView updated_at = (CardView) findViewById(R.id.card_update_at);
                updated_at.setVisibility(View.GONE);

                CardView gross_revenue = (CardView) findViewById(R.id.card_gross_revenue);
                gross_revenue.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<EmployeeModel> call, Throwable t) {

            }
        });
    }

    private void detallePelicula(HarryAPIInterface harryAPIInterface) {
        harryAPIInterface.getMovie(positionMovie+1).enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {

                TextView release_date = (TextView) findViewById(R.id.release_date);
                release_date.setText(String.valueOf(response.body().getRelease_date()));

                TextView running_time = (TextView) findViewById(R.id.running_time);
                running_time.setText(String.valueOf(response.body().getRunning_time()));

                TextView budget = (TextView) findViewById(R.id.budget);
                budget.setText(response.body().getBudget());

                TextView created_at = (TextView) findViewById(R.id.created_at);
                created_at.setText(response.body().getCreated_at());

                TextView updated_at = (TextView) findViewById(R.id.updated_at);
                updated_at.setText(response.body().getUpdated_at());

                TextView gross_revenue = (TextView) findViewById(R.id.gross_revenue);
                gross_revenue.setText(response.body().getGross_revenue());
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }

    private void setToolbar() {
        //Añadir la Toolbar

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Se carga una imagen para el detalle
     */
    private void loadImageParallax(String id) {
        ImageView image = (ImageView) findViewById(R.id.image_paralax);
        // Usando Glide para la carga asíncrona
        Glide.with(this)
                .load(id)
                .centerCrop()
                .into(image);
    }
}
