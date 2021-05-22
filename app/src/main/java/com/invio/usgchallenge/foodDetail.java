package com.bonappetit.usgchallenge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.biubiubiu.justifytext.library.JustifyTextView;


public class foodDetail extends AppCompatActivity {
    private ImageView detailFoodStar;
    private LinearLayout detailLinearV;
    private PhotoView detailImageView;
    private TextView productName, productCategory;
    private JustifyTextView productDescription;
    private AVLoadingIndicatorView foodDetailLoading;
    private String idToSearch, listSearch;
    starControl starControl = new starControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        idMatch();
        hide();

        foodDetailLoading.show();
        Intent i = getIntent();
        idToSearch = i.getStringExtra("id").trim();
        listSearch = i.getStringExtra("search").trim();

        starControl();
        getData(idToSearch);

        detailFoodStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starControl.addOrRemove(foodDetail.this, idToSearch);
                starControl();
            }
        });


    }


    private void getData(String id) {
        boolean internetConnection = new connectionControl().internetConnection(this);
        if (!internetConnection)
            alertInternetConnection();
        else {
            final String url = "YOURAPI" + id;
            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        JSONArray categories = response.getJSONArray("meals");
                        for (int i = 0; i < categories.length(); i++) {
                            JSONObject category = categories.getJSONObject(i);

                            String CThumb = category.getString("strMealThumb");
                            Picasso.with(detailImageView.getContext()).load(CThumb).into(detailImageView);

                            String CName = category.getString("strMeal").trim();
                            productName.setText(CName);

                            String categoryString = category.getString("strCategory").trim();
                            productCategory.setText(categoryString);

                            String description = category.getString("strInstructions").trim();
                            productDescription.setText(description);
                        }
                        upload();

                    } catch (JSONException e) {
                        Log.e("Exception", e.toString());

                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            });
            Volley.newRequestQueue(foodDetail.this).add(objectRequest);
        }
    }

    public void alertInternetConnection() {
        AlertDialog.Builder ad = new AlertDialog.Builder(foodDetail.this);
        ad.setMessage("Please connect to the internet.");
        ad.setTitle("Network Error");
        ad.setIcon(R.drawable.ic_baseline_error_24);
        ad.setCancelable(false);
        ad.setPositiveButton("Refresh", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getData(idToSearch);
            }
        });
        ad.setNegativeButton("Exıt", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        ad.create().show();
    }

    public void turnBack(View view) {
        Intent i = new Intent(foodDetail.this, foodList.class);
        i.putExtra("search", listSearch);
        startActivity(i);
        finish();
    }

    public void idMatch() {
        foodDetailLoading = findViewById(R.id.foodDetailLoading);
        productName = findViewById(R.id.productName);
        productCategory = findViewById(R.id.productCategory);
        productDescription = findViewById(R.id.productDescription);
        detailFoodStar = findViewById(R.id.detailFoodStar);
        detailLinearV=findViewById(R.id.detailLinearV);
        detailImageView = findViewById(R.id.detailImageView);
    }

    public void starControl() {
        boolean attached = starControl.control(this, idToSearch);
        if (attached)
            detailFoodStar.setImageResource(R.drawable.ic_baseline_star_24);
        else {
            detailFoodStar.setImageResource(R.drawable.ic_baseline_star_border_24);
        }
    }

    public void upload() {
        foodDetailLoading.setVisibility(View.GONE);
        detailLinearV.setVisibility(View.VISIBLE);
    }
    public void hide() {
        detailLinearV.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(foodDetail.this, foodList.class);
        i.putExtra("search", listSearch);
        startActivity(i);
        finish();
    }
}