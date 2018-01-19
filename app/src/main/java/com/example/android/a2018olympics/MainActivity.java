package com.example.android.a2018olympics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //sports
        TextView sports = (TextView)findViewById(R.id.sports_category);

        sports.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent sportsIntent = new Intent(MainActivity.this, Sports.class);
                startActivity(sportsIntent);
            }
        });

        //greetings
        TextView greetings = (TextView)findViewById(R.id.greetings_category);
        greetings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent greetingsIntent = new Intent(MainActivity.this, Greetings.class);
                startActivity(greetingsIntent);
            }
        });

        //courtesy
        TextView courtesy = (TextView)findViewById(R.id.courtesy_category);
        courtesy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent courtesyIntent = new Intent(MainActivity.this, Courtesy.class);
                startActivity(courtesyIntent);
            }
        });

        //navigating
        TextView navigating = (TextView)findViewById(R.id.navigating_category);
        navigating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navigatingIntent = new Intent(MainActivity.this, Navigating.class);
                startActivity(navigatingIntent);
            }
        });

        //dining and shopping
        TextView dineShop = (TextView)findViewById(R.id.dine_shop_category);
        dineShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dineShopIntent = new Intent(MainActivity.this, DineAndShop.class);
                startActivity(dineShopIntent);
            }
        });

        //emergency
        TextView emergency = (TextView)findViewById(R.id.emergency_category);
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emergencyIntent = new Intent(MainActivity.this, Emergency.class);
                startActivity(emergencyIntent);
            }
        });
    }
}
