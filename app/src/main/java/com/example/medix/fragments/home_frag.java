
package com.example.medix.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medix.MapFragment;
import com.example.medix.R;
import com.example.medix.interfaces.PharmacyJsonApi;
import com.example.medix.models.Feature;
import com.example.medix.models.Medicine;
import com.example.medix.models.Pharmacy;
import com.example.medix.models.Properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class home_frag extends Fragment {

    private Button go_button, sort_btn, mV_btn;
    private Button clear;


    private RecyclerView recyclerView;
    private ArrayList<Properties> meds;
    EditText editText;
    Adapter adapter;
//    private MapFragment mapFragment = new MapFragment();

    public home_frag() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_frag, container, false);


        //buttons
        sort_btn = view.findViewById(R.id.btn_sort);
        mV_btn = view.findViewById(R.id.btn_mV);
        clear = view.findViewById(R.id.clear);


        //recyclerView Stuffss
        meds = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
//        setPInfo();// supply info Api from backend
        setAdapter();

        //Search Function
        editText = view.findViewById(R.id.editText);
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                Log.d("TAG", "onTextChanged: " + charSequence.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                Log.d("TAG", "onTextChanged:2 " );
//                filter(editable.toString());
//            }
//        });


        //more buttons

        sort_btn.setVisibility(view.GONE);
        mV_btn.setVisibility(view.GONE);
        clear.setVisibility(view.GONE);
        
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "Helli", Toast.LENGTH_SHORT).show();
                editText.setText("");
                sort_btn.setVisibility(view.GONE);
                mV_btn.setVisibility(view.GONE);
                clear.setVisibility(view.GONE);

//                recyclerView

            }
        });

        sort_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "This is supposed to sort the list according to price", Toast.LENGTH_SHORT).show();

                Collections.sort(meds, new Comparator<Properties>() {
                    @Override
                    public int compare(Properties properties, Properties t1) {
                        return 0;
                    }
                });

            }
        });

        mV_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "will change fragment and MapView????", Toast.LENGTH_SHORT).show();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, new MapFragment()).commit();
//                getActivity().startActivity(new Intent(getActivity(),new MapFragment()));

            }
        });


        go_button = view.findViewById(R.id.btn_search);
//        if(editText.getText().toString()!=""){}
        go_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "Heelloo", Toast.LENGTH_SHORT).show();
                String text = editText.getText().toString();
                filter(text);

                sort_btn.setVisibility(View.VISIBLE);
                mV_btn.setVisibility(View.VISIBLE);
                clear.setVisibility(View.VISIBLE);
            }
        });

        return view;

    }

    private void filter(String enteredString) {
        Log.d("TAG", "filter:");
        ArrayList<Properties> filteredList = new ArrayList<>();
//        ArrayList<Medicine> medList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.jsonbin.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PharmacyJsonApi pharmacyJsonApi = retrofit.create(PharmacyJsonApi.class);

        Call<Pharmacy> call = pharmacyJsonApi.getData();
        call.enqueue(new Callback<Pharmacy>() {
            @Override
            public void onResponse(Call<Pharmacy> call, Response<Pharmacy> response) {

                Log.d("TAG", "onResponse: we got something");

                Pharmacy phar = response.body();

                List<Feature> features = phar.getFeatures();
                for (Feature feature : features) {
                    Log.d("TAG", "onResponse:medicine " + feature.getProperties().getMedicine().toString());
//                    Medicine meds = (Medicine) feature.getProperties().getMedicine();
                    for (Medicine meds : feature.getProperties().getMedicine()) {
//                        String medName = "";
//                        String medName = meds.getName();

                        if (meds.getName().equalsIgnoreCase(enteredString)) {
                            filteredList.add(feature.getProperties()); // pharmacy ko nam dinu paryo??
//                            filteredList.add(feature.getProperties())
//                            medList.add(meds);
                            Log.d("TAG", "onResponse:1 " + filteredList.size());
                            Log.d("price", "onResponse: " + meds.getPrice());

//                            Log.d("TAG1", "\n\n\n/////////////////FILTEREDLISTSSS//////////: " + filteredList+ "\n\n\n///////////////////");
                        }
//        }

                    }
                }
                Log.d("TAG", "onResponse: " + filteredList.size());
                adapter.pharmacy = filteredList;
                adapter.medName = enteredString;
                adapter.notifyDataSetChanged();
//                          Adapter.filteredList(filteredList);


            }

            @Override
            public void onFailure(Call<Pharmacy> call, Throwable t) {
                Log.d("TAG", "onFailure: Failed " + t.getMessage());
            }
        });

//        search function


    }

    //------------------------------------------------------------------------------------------------------------------------------
    private void setAdapter() {
        adapter = new Adapter(meds, recyclerView, "");
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

}