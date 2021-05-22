package com.bonappetit.usgchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class foodListAdapter extends RecyclerView.Adapter<foodListAdapter.CardViewObjects> {
    private List<foodListClass> data;
    private String search;

    starControl control = new starControl();
    private Context context;

    public foodListAdapter(Context context, List<foodListClass> data, String search) {
        this.context = context;
        this.data = data;

        this.search = search;

    }

    @NonNull
    @Override
    public CardViewObjects onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_cardview, parent, false);

        return new CardViewObjects(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewObjects holder, int position) {

        final String mealName = data.get(position).mealName;
        final String mealId = data.get(position).mealId;
        boolean attached = control.control(context, mealId);


        if (attached)
            holder.foodStar.setImageResource(R.drawable.ic_baseline_star_24);

        holder.foodListTextView.setText(mealName);

        Picasso.with(holder.foodListImageView.getContext()).load(data.get(position).mealUrl).into(holder.foodListImageView);
        holder.foodStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control.addOrRemove(context, mealId);
                boolean temp = control.control(context, mealId);

                if (temp) {
                    holder.foodStar.setImageResource(R.drawable.ic_baseline_star_24);

                } else {
                    holder.foodStar.setImageResource(R.drawable.ic_baseline_star_border_24);
                }

            }
        });

        holder.foodListCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, foodDetail.class);
                intent.putExtra("id", mealId);
                intent.putExtra("search", search);
                context.startActivity(intent);
                ((Activity) context).finish();

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CardViewObjects extends RecyclerView.ViewHolder {
        public TextView foodListTextView;
        public ImageView foodListImageView, foodStar;
        public CardView foodListCardView;


        public CardViewObjects(@NonNull View itemView) {
            super(itemView);
            foodStar = itemView.findViewById(R.id.foodStar);
            foodListImageView = itemView.findViewById(R.id.foodListImageView);
            foodListTextView = itemView.findViewById(R.id.foodListTextView);
            foodListCardView = itemView.findViewById(R.id.foodListCardView);

        }
    }
}
