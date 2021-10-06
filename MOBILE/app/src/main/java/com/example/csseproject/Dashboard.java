package com.example.csseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {

    private CardView supplier1,orders1,items1,acceptOrders1;
    ImageView logout,setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        supplier1=findViewById(R.id.img_supplier);
        orders1=findViewById(R.id.img_Orders);
        items1=findViewById(R.id.img_Items);
        acceptOrders1=findViewById(R.id.img_AcceptOrders);
        logout=findViewById(R.id.logout);
        setting=findViewById(R.id.setting);

        supplier1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        orders1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewOrder.class));
            }
        });
        items1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewAllItems.class));
            }
        });
        acceptOrders1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AcceptOrders.class));

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences=getSharedPreferences("Checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("remember","false");
                editor.apply();

                Toast.makeText(Dashboard.this, "Thank You Come Again Log Out...", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();

            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Changeprofile.class));
            }
        });
    }
}