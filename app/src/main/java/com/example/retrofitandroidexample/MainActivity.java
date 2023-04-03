package com.example.retrofitandroidexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.retrofitandroidexample.adapter.CountryList;
import com.example.retrofitandroidexample.model.Country;
import com.example.retrofitandroidexample.retrofit.RetrofitClientInstance;
import com.example.retrofitandroidexample.services.GetDataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnSubmit;
    private CountryList adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading ...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofit().create(GetDataService.class);
        Call<List<Country>> call = service.getCountries();
        
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "" + t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("error", t.toString());
            }
        });
    }

    private void generateDataList(List<Country> body) {
        recyclerView = findViewById(R.id.custom_rec_view);
        adapter = new CountryList(body, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}