package com.example.iihsa.airminum.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.ImageView;

import com.example.iihsa.airminum.R;
import com.example.iihsa.airminum.constants.Const;
import com.example.iihsa.airminum.receiver.AlarmManagerUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Reminder extends Fragment {
    @Bind(R.id.analogClock)
    AnalogClock analogClock;
    View rootview;
    int hour=9;
    int[] jam;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    public Reminder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_reminder, container, false);
        getActivity().setTitle("Reminder");
        ButterKnife.bind(this,rootview );
        AlarmManagerUtil alarmUtil = new AlarmManagerUtil();
        alarmUtil.initAlarmNotification(this.getActivity());
        cure();
        return rootview;
    }
    private void cure() {
        dialog = new AlertDialog.Builder(this.getActivity());
        inflater = getActivity().getLayoutInflater();
        dialogView = inflater.inflate(R.layout.alert, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.drawable.logo);
        dialog.setTitle("Ayo Minum");
        dialog.setMessage("Silahkan Pilih Kemasan Air Minum Anda :");
        final ImageView imageView1 = (ImageView) dialogView.findViewById(R.id.gelas);
        final ImageView imageView2 = (ImageView) dialogView.findViewById(R.id.aqua);
        final ImageView imageView3 = (ImageView) dialogView.findViewById(R.id.botolkecil);
        final ImageView imageView4 = (ImageView) dialogView.findViewById(R.id.botobsar);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView1.setBackgroundColor(getResources().getColor(R.color.white));
                imageView2.setBackgroundColor(getResources().getColor(R.color.white_greyish));
                imageView3.setBackgroundColor(getResources().getColor(R.color.white_greyish));
                imageView4.setBackgroundColor(getResources().getColor(R.color.white_greyish));
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView1.setBackgroundColor(getResources().getColor(R.color.white_greyish));
                imageView2.setBackgroundColor(getResources().getColor(R.color.white));
                imageView3.setBackgroundColor(getResources().getColor(R.color.white_greyish));
                imageView4.setBackgroundColor(getResources().getColor(R.color.white_greyish));
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView1.setBackgroundColor(getResources().getColor(R.color.white_greyish));
                imageView2.setBackgroundColor(getResources().getColor(R.color.white_greyish));
                imageView3.setBackgroundColor(getResources().getColor(R.color.white));
                imageView4.setBackgroundColor(getResources().getColor(R.color.white_greyish));
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView1.setBackgroundColor(getResources().getColor(R.color.white_greyish));
                imageView2.setBackgroundColor(getResources().getColor(R.color.white_greyish));
                imageView3.setBackgroundColor(getResources().getColor(R.color.white_greyish));
                imageView4.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });

        dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
       dialog.setNegativeButton("Batal",new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int i) {
               dialog.dismiss();
           }
       });
        dialog.show();
    }

    @OnClick(R.id.input)
    void input(){
        cure();
    }
}
