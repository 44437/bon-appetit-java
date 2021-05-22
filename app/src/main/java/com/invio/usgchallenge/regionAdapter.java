package com.bonappetit.usgchallenge;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class regionAdapter extends RecyclerView.Adapter<regionAdapter.CardViewObjects> {
    private List<String> areasArray;
    private Context context;

    public regionAdapter(Context context, List<String> areasArray) {
        this.areasArray = areasArray;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewObjects onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.region_cardview, parent, false);
        return new CardViewObjects(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewObjects holder, int position) {

        final String zone = areasArray.get(position);
        holder.regionTextView.setText(zone);
        holder.regionCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, foodList.class);
                intent.putExtra("search", "a=" + zone);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return areasArray.size();
    }

    public class CardViewObjects extends RecyclerView.ViewHolder {
        public TextView regionTextView;
        public CardView regionCardView;

        public CardViewObjects(@NonNull View itemView) {
            super(itemView);
            regionTextView = itemView.findViewById(R.id.regionText);
            regionCardView = itemView.findViewById(R.id.regionCardView);

        }
    }
}
