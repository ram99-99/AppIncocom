package com.example.fininfocom;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private DatabaseReference databaseReference;
    private static final String TAG = "RecyclerViewActivity";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d( TAG,"onCreateOptionsMenu called ");
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d( TAG,"lonCreate called ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyler);
        databaseReference = FirebaseDatabase.getInstance().getReference("Dummy-Data"); // Data node
        insertDummyDataIntoFirebase();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        loadDataAndShowRecyclerView();

    }

    private void loadDataAndShowRecyclerView() {
        Log.d( TAG,"loadDataAndPopulateRecyclerView called ");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<MyDataModel> dataFromFirebase = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MyDataModel data = snapshot.getValue(MyDataModel.class);
                    dataFromFirebase.add(data);
                }
                adapter.updateData(dataFromFirebase);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d( TAG,"onCancelled called ");
                // Handle any errors that occur during the read operation
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d( TAG,"onOptionsItemSelected called ");
        int id = item.getItemId();
        if (id == R.id.action_sort_name) {
            sortBy("name");
            return true;
        } else if (id == R.id.action_sort_age) {
            sortBy("age");
            return true;
        } else if (id == R.id.action_sort_city) {
            sortBy("city");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void sortBy(String property) {
        Log.d( TAG,"sortBy called ");
        List<MyDataModel> dataFromAdapter = adapter.getData();
        List<MyDataModel> sortedList = new ArrayList<>(dataFromAdapter);

        switch (property) {
            case "name":
                Collections.sort(sortedList, new Comparator<MyDataModel>() {
                    @Override
                    public int compare(MyDataModel o1, MyDataModel o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                break;
            case "age":
                Collections.sort(sortedList, new Comparator<MyDataModel>() {
                    @Override
                    public int compare(MyDataModel o1, MyDataModel o2) {
                        return Integer.compare(o1.getAge(), o2.getAge());
                    }
                });
                break;
            case "city":
                Collections.sort(sortedList, new Comparator<MyDataModel>() {
                    @Override
                    public int compare(MyDataModel o1, MyDataModel o2) {
                        return o1.getCity().compareTo(o2.getCity());
                    }
                });
                break;
            default:
                break;
        }

        adapter.updateData(sortedList);
    }

    private void insertDummyDataIntoFirebase() {
        Log.d( TAG,"insertDummyDataIntoFirebase called ");
       List<MyDataModel> myAdapters = new ArrayList<>();
        myAdapters.add(new MyDataModel("RamBabu", 20, "Eluru"));
        myAdapters.add(new MyDataModel("Arun", 30, "Hydrabad"));
        myAdapters.add(new MyDataModel("Nishanth", 55, "Vazg"));
        myAdapters.add(new MyDataModel("phani", 40, "vijayawada"));
        databaseReference.setValue(myAdapters); // Setting the data into Firebase
        Toast.makeText(RecyclerViewActivity.this, "Data Inserted Firebase", Toast.LENGTH_SHORT).show();
    }
}
