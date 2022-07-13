package com.example.medix.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.medix.MainActivity;
import com.example.medix.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchFrag extends Fragment {

    private Button search;
    private home_frag homeFrag = new home_frag();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        search = view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, homeFrag).commit();


//                Fragment fragment = new home_frag();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.replace(R.id.frame,fragment);
            }
        });

        return view;
    }
}