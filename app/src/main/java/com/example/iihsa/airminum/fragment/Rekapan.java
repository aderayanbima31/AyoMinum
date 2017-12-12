package com.example.iihsa.airminum.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iihsa.airminum.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class Rekapan extends Fragment {
    View rootview;
    ArrayList<Entry> entries ;
    ArrayList<String> PieEntryLabels ;
    PieDataSet pieDataSet ;
    PieData pieData ;
    @Bind(R.id.chart1)
    PieChart pieChart;
    public Rekapan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_rekapan, container, false);
        getActivity().setTitle("Rekapan");
        ButterKnife.bind(this, rootview);
        entries = new ArrayList<>();
        PieEntryLabels = new ArrayList<String>();
        AddValuesToPIEENTRY();
        AddValuesToPieEntryLabels();
        pieDataSet = new PieDataSet(entries, "");
        pieData = new PieData(PieEntryLabels, pieDataSet);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setData(pieData);
        pieChart.animateY(3000);
        return rootview;
    }
    public void AddValuesToPIEENTRY(){

        entries.add(new BarEntry(2f, 0));
        entries.add(new BarEntry(4f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(8f, 3));
        entries.add(new BarEntry(7f, 4));
        entries.add(new BarEntry(3f, 5));
        entries.add(new BarEntry(3f, 5));


    }

    public void AddValuesToPieEntryLabels(){

        PieEntryLabels.add("Minggu");
        PieEntryLabels.add("Senin");
        PieEntryLabels.add("Selasa");
        PieEntryLabels.add("Rabu");
        PieEntryLabels.add("Kamis");
        PieEntryLabels.add("Jum'at");
        PieEntryLabels.add("Sabtu");


    }
}
