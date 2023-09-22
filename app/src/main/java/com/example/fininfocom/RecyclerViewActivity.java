package com.example.fininfocom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyler);

        recyclerView = findViewById(R.id.recyclerView);

        // Create a list of dummy data
        List<MyDataModel> dummyDataList = createDummyData();

        // Initialize the adapter with your data
        adapter = new MyAdapter(dummyDataList);

        // Set the layout manager and adapter for the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private List<MyDataModel> createDummyData() {
        List<MyDataModel> data = new ArrayList<>();
        // Add dummy data here
        data.add(new MyDataModel("John", 25, "New York"));
        data.add(new MyDataModel("Alice", 30, "Los Angeles"));
        data.add(new MyDataModel("Bob", 28, "Chicago"));
        // Add more items as needed
        return data;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sort_name) {
            // Sort the data by name and update the RecyclerView
            sortBy("name");
            return true;
        } else if (id == R.id.action_sort_age) {
            // Sort the data by age and update the RecyclerView
            sortBy("age");
            return true;
        } else if (id == R.id.action_sort_city) {
            // Sort the data by city and update the RecyclerView
            sortBy("city");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    private void sortBy(String property) {
        List<MyDataModel> dummyDataList = createDummyData();
        List<MyDataModel> sortedList = new ArrayList<>(dummyDataList); // Copy your original data

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
                // Default sorting logic here (if none of the cases match)
                break;
        }
        // Update your RecyclerView's data source with the sorted list
        adapter.updateData(sortedList);
    }
}
