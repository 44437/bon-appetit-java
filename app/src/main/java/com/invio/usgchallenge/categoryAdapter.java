package com.bonappetit.usgchallenge;

import android.content.Context;
import android.content.Intent;
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

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.CardViewObjects> {
    private List<String> strCategoryL;
    private List<String> strCategoryThumbL;
    private Context context;

    public categoryAdapter(Context context, List<String> strCategoryL, List<String> strCategoryThumbL) {
        this.strCategoryL = strCategoryL;
        this.strCategoryThumbL = strCategoryThumbL;
        this.context = context;
    }

    @NonNull
    @Override
    public categoryAdapter.CardViewObjects onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_cardview, parent, false);
        return new categoryAdapter.CardViewObjects(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewObjects holder, int position) {
        final String CategoryName = strCategoryL.get(position).trim();

        Picasso.with(holder.categoryImageView.getContext()).load(strCategoryThumbL.get(position)).into(holder.categoryImageView);
        holder.categoryTextView.setText(CategoryName);
        holder.categoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, foodList.class);
                intent.putExtra("search", "c=" + CategoryName);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return strCategoryL.size();
    }

    public class CardViewObjects extends RecyclerView.ViewHolder {
        public TextView categoryTextView;
        public ImageView categoryImageView;
        public CardView categoryCardView;


        public CardViewObjects(@NonNull View itemView) {
            super(itemView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            categoryCardView = itemView.findViewById(R.id.categoryCardView);
            categoryImageView = itemView.findViewById(R.id.categoryImageView);
        }
    }
}
