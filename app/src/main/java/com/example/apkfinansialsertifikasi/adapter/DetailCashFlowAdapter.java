package com.example.apkfinansialsertifikasi.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apkfinansialsertifikasi.DetailCashFlowActivity;
import com.example.apkfinansialsertifikasi.R;
import com.example.apkfinansialsertifikasi.helpers.DetailCashFlow;

import java.util.List;

public class DetailCashFlowAdapter extends RecyclerView.Adapter<DetailCashFlowAdapter.DetailCashFlowViewHolder> {

    private List<DetailCashFlow> detailCashFlowList;
    String nominal = "";

    public DetailCashFlowAdapter(List<DetailCashFlow> detailCashFlowList) {
        this.detailCashFlowList = detailCashFlowList;
    }

    @NonNull
    @Override
    public DetailCashFlowAdapter.DetailCashFlowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_detail_cash_flow, parent, false);

        return new DetailCashFlowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailCashFlowAdapter.DetailCashFlowViewHolder holder, int position) {
        holder.keteranganTv.setText(detailCashFlowList.get(position).getKeterangan());
        holder.tanggalTv.setText(detailCashFlowList.get(position).getTanggal()+"/"+detailCashFlowList.get(position).getBulan()+"/"+detailCashFlowList.get(position).getTahun());

        if(detailCashFlowList.get(position).getTipe().contains("pemasukan")){
            nominal = "[+] Rp. "+detailCashFlowList.get(position).getNominal();
            holder.detailCashFlowIcon.setImageResource(R.drawable.ic_left_arrow);
        }else if(detailCashFlowList.get(position).getTipe().contains("pengeluaran")) {
            nominal = "[-] Rp. "+detailCashFlowList.get(position).getNominal();
            holder.detailCashFlowIcon.setImageResource(R.drawable.ic_right_arrows);
        }
        holder.nominalTv.setText(nominal);

    }

    @Override
    public int getItemCount() {
        Log.d(DetailCashFlowActivity.class.getSimpleName(), String.valueOf(detailCashFlowList.size()));
        return detailCashFlowList.size();
    }

    public class DetailCashFlowViewHolder extends RecyclerView.ViewHolder {

        public TextView nominalTv;
        public TextView keteranganTv;
        public TextView tanggalTv;
        public ImageView detailCashFlowIcon;


        public DetailCashFlowViewHolder(@NonNull View itemView) {
            super(itemView);

            nominalTv = itemView.findViewById(R.id.nominal_item_cash_flow);
            keteranganTv = itemView.findViewById(R.id.keterangan_item_cash_flow);
            tanggalTv = itemView.findViewById(R.id.tanggal_item_cash_flow);
            detailCashFlowIcon = itemView.findViewById(R.id.detail_cash_flow_image);
        }
    }
}
