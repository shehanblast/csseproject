package com.example.csseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.csseproject.Model.Supplier;
import com.example.csseproject.ViewHolder.SupplierViewHolder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore firestore;
    private ArrayList<Supplier> suppliersList;
    private SupplierViewHolder supplierViewHolder;
    private EditText search;
    private ImageView logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.card_recycleView);
        search=findViewById(R.id.search_Supplier);
        logout=findViewById(R.id.logout);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        suppliersList=new ArrayList<>();
        supplierViewHolder=new SupplierViewHolder(MainActivity.this,suppliersList);
        recyclerView.setAdapter(supplierViewHolder);


        firestore=FirebaseFirestore.getInstance();
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    supplierViewHolder.getFilter().filter(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


//        DocumentReference doc=firestore.collection("suppliers").document(user.getUid());



//        doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                List<DocumentSnapshot> note= (List<DocumentSnapshot>) documentSnapshot.getData();
//                for(DocumentSnapshot ds:note){
//                    Supplier supplier=ds.toObject(Supplier.class);
//                    suppliersList.add(supplier);
//                 }
//            }
//        });



        firestore.collection("suppliers").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot doc:task.getResult()){
                                String id =doc.getId();
                                String supplierName=doc.getString("supplierName");
                                String supplierCompany=doc.getString("supplierCompany");
                                String supplierSpeciality=doc.getString("supplierSpeciality");
                                String supplierPic=doc.getString("supplierPic");

                                Supplier model=new Supplier(id,supplierName,supplierCompany,supplierSpeciality,supplierPic);
                                suppliersList.add(model);
                                supplierViewHolder.notifyDataSetChanged();

                            }
                        }
                    }
                });

    }
}