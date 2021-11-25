package com.ingluise.ProyectoAndroidGrupo07;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private String[] mDataset;

    public CustomAdapter(String[] dataset) {
        mDataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.i(TAG, "Elemento " + position + " seleccionado");

        viewHolder.getTextView().setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv;
        private final ImageView iv;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "Elemento " + getAdapterPosition() + " seleccionado");
                }
            });

            tv = view.findViewById(R.id.textView19);
            iv = view.findViewById(R.id.imageView6);
            iv.setImageResource(R.drawable.splash_screen);
        }

        public TextView getTextView() {
            return tv;
        }

        public ImageView getImageView() {
            return iv;
        }
    }
}
