package com.example.apkfinansialsertifikasi;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apkfinansialsertifikasi.adapter.DetailCashFlowAdapter;
import com.example.apkfinansialsertifikasi.helpers.DatabaseHelper;
import com.example.apkfinansialsertifikasi.helpers.DetailCashFlow;
import com.example.apkfinansialsertifikasi.helpers.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DetailCashFlowActivity extends AppCompatActivity {

    //Declare RecyclerView
    private RecyclerView recyclerViewCashFlow;
    //Declare List
    private List<DetailCashFlow> detailCashFlowList;
    //Declare PatientRecyclerAdapter
    private DetailCashFlowAdapter detailCashFlowAdapter;
    //Declare DatabaseHelper
    private DatabaseHelper databaseHelper;

    Button kembaliCashFlowBtn;

    DetailCashFlow detailCashFlow;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cash_flow);

        Objects.requireNonNull(getSupportActionBar()).setSubtitle("Detail Cash Flow");


        try{

            if(getIntent().hasExtra("User")){
                user = getIntent().getParcelableExtra("User");
            }

            initViews();
            initObjects();

            detailCashFlowList.clear();
            detailCashFlowList.addAll(databaseHelper.getAllCashFlow());

            detailCashFlowAdapter.notifyDataSetChanged();

            kembaliCashFlowBtn.setOnClickListener(v -> finish());
        }catch(Exception e){
            Log.d(" cash Flow activity", e.toString());
        }
    }

    private void initObjects() {
        detailCashFlowList = new ArrayList<DetailCashFlow>();
        detailCashFlowAdapter = new DetailCashFlowAdapter(detailCashFlowList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewCashFlow.setLayoutManager(mLayoutManager);
        recyclerViewCashFlow.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCashFlow.setHasFixedSize(true);
        recyclerViewCashFlow.setAdapter(detailCashFlowAdapter);

        databaseHelper = new DatabaseHelper(getApplicationContext());

    }

    private void initViews() {
        kembaliCashFlowBtn = findViewById(R.id.kembali_detail_cash_flow_button);
        recyclerViewCashFlow = findViewById(R.id.detail_cash_flow_recycler_view);
    }
}