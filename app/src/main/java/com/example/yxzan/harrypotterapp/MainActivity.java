package com.example.yxzan.harrypotterapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yxzan.harrypotterapp.Models.EmployeeModel;
import com.example.yxzan.harrypotterapp.Models.MovieModel;
import com.example.yxzan.harrypotterapp.Servicio.HarryAPIInterface;
import com.example.yxzan.harrypotterapp.Servicio.ServicioMovie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Activity activity = this;
    private RecyclerView recycler;
    private LinearLayoutManager lManager;
    private CollapsingToolbarLayout collapser;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();// Añadir la Toolbar
    }

    @Override
    protected void onResume() {
        super.onResume();
        respuestaLlamadaServicio(1);
    }

    private void respuestaLlamadaServicio(final int setting) {
        final ArrayList<ModelAdapter> modelAdapter = new ArrayList<>();
        HarryAPIInterface harryAPIInterface = ServicioMovie.llamadaServicio();
        loading= ProgressDialog.show(MainActivity.this,"Cargando","Por favor espere", false, false);

        if (setting == 1){
            harryAPIInterface.getAllMovies().enqueue(new Callback<ArrayList<MovieModel>>() {
                @Override
                public void onResponse(Call<ArrayList<MovieModel>> call, Response<ArrayList<MovieModel>> response) {

                    ModelAdapter unaModelAdapterMovie;
                    for (MovieModel movieModel : response.body()) {
                        unaModelAdapterMovie = new ModelAdapter(movieModel.getName(), movieModel.getImage_url());
                        modelAdapter.add(unaModelAdapterMovie);
                    }
                   llamarRecyclerView(modelAdapter, setting);
                }

                @Override
                public void onFailure(Call<ArrayList<MovieModel>> call, Throwable t) {
                    loading.dismiss();
                }

            });
        }else{
            harryAPIInterface.getAllEmployees().enqueue(new Callback<ArrayList<EmployeeModel>>() {
                @Override
                public void onResponse(Call<ArrayList<EmployeeModel>> call, Response<ArrayList<EmployeeModel>> response) {
                    ModelAdapter unaModelAdapterEmployee;
                    for (EmployeeModel employeeModel : response.body()) {
                        unaModelAdapterEmployee = new ModelAdapter(employeeModel.getFirst_name()+employeeModel.getLast_name(), employeeModel.getImage_url());
                        modelAdapter.add(unaModelAdapterEmployee);
                    }
                    llamarRecyclerView(modelAdapter, setting);
                }

                @Override
                public void onFailure(Call<ArrayList<EmployeeModel>> call, Throwable t) {
                    loading.dismiss();
                }
            });
        }
    }

    private void llamarRecyclerView(ArrayList<ModelAdapter> modelAdapter, int movieOrEmployee) {
        SimpleAdapter adaptador = new SimpleAdapter(activity, modelAdapter, movieOrEmployee);
        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(activity);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        recycler.setAdapter(adaptador);
        loading.dismiss();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_employee:
                respuestaLlamadaServicio(2);
                return true;
            case R.id.action_movie:
                respuestaLlamadaServicio(1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
