package com.example.medix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.medix.fragments.SearchFrag;
import com.example.medix.fragments.explore_page;
import com.example.medix.fragments.home_frag;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {


    //fragments
    BottomNavigationView nav;
    home_frag home = new home_frag();
    explore_page explore = new explore_page();
    //    MenuActivity menu = new MenuActivity();
    SearchFrag search = new SearchFrag();
//    ProfileActivity profile = new ProfileActivity();

    //recycler view stuffss
//    ArrayList<model> p_list = new ArrayList<>();
//    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
//        btn = findViewById(R.id.btn);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Heello", Toast.LENGTH_SHORT).show();
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame, home).commit();
//            }
//        });


        nav = findViewById(R.id.navBar);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, search).commit();

        nav.setSelectedItemId(R.id.home);

        //recycler View Stuffs
//        recyclerView = findViewById(R.id.recyclerView);
//        setPInfo();// supply info Api from backend
//        setAdapter();

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
//                        Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();

                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, search).commit();
                        return true;


                    case R.id.search:
//                        Toast.makeText(MainActivity.this, "settings", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, home).commit();
                        return true;

//                    case R.id.profile:
//                        Toast.makeText(MainActivity.this, "profile", Toast.LENGTH_SHORT).show();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, profile).commit();
//                        return true;
                    case R.id.explore:
//                        Toast.makeText(MainActivity.this, "explore page which i have no idea what to do.....>}????", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new MapFragment()).commit();

                        return true;

                }
                return false;
            }
        });


    }

//    private void setAdapter() {
//        adapter adapter = new adapter(p_list);
////        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);
//    }
//
//    private void setPInfo() {
//        //adding random data
//        //this is supposed to be data from Api ??? HOW????????
//        p_list.add(new model("ohm Pharmacy"));
//        p_list.add(new model("pharmacy 1"));
//        p_list.add(new model("Pharmacy 2"));
//
//    }

}