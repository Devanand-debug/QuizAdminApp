package com.example.adminapp;

import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    public List<String> sets;
    private String cat;
    private GridListener listener;

    public GridAdapter(List<String> sets, String cat, GridListener listener) {
        this.sets = sets;
        this.cat = cat;
        this.listener = listener;
    }


    @Override
    public int getCount() {
        return sets.size() + 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {

        View view1;
        if (view == null) {
            view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.set_item, viewGroup, false);
        } else {
            view1 = view;
        }

        if (i == 0) {
            ((TextView) view1.findViewById(R.id.textview)).setText("+");
        } else {
            ((TextView) view1.findViewById(R.id.textview)).setText(String.valueOf(i));
        }

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (i == 0) {
                    // set add code
                    listener.addset();
                } else {
                    // View parent = null;
                    Intent intent = new Intent(viewGroup.getContext(), QuestionsActivity.class);
                    intent.putExtra("cat", cat);
                    intent.putExtra("setId", sets.get(i - 1));
                    viewGroup.getContext().startActivity(intent);
                }
            }
        });

        view1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (i != 0) {
                    listener.onLongClick(sets.get(i - 1),i);
                }
                return false;
            }
        });
       return view1;
    }

    public interface GridListener{

        public void addset();

        void onLongClick(String setId,int i);
    }
}
