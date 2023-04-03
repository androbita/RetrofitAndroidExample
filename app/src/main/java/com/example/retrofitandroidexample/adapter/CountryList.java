package com.example.retrofitandroidexample.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitandroidexample.R;
import com.example.retrofitandroidexample.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryList extends RecyclerView.Adapter<CountryList.CustomViewHolder> {

    private List<Country> dataCountry;
    private Context context;

    public CountryList(List<Country> dataCountry, Context context) {
        this.dataCountry = dataCountry;
        this.context = context;
    }

    @NonNull
    @Override
    public CountryList.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryList.CustomViewHolder holder, int position) {
//        String id = String.valueOf(dataCountry.get(position).getId());
        holder.txtId.setText(String.valueOf(dataCountry.get(position).getId()));
        holder.txtNamaCountry.setText(dataCountry.get(position).getNamaCountry());
    }

    @Override
    public int getItemCount() {
        return dataCountry.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        TextView txtId, txtNamaCountry;

        CustomViewHolder(View itemView){
            super(itemView);
            mView = itemView;

            txtId = mView.findViewById(R.id.txt_id);
            txtNamaCountry = mView.findViewById(R.id.txt_country);
        }
    }
}
