package com.example.medix.fragments;

import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medix.R;
import com.example.medix.models.Medicine;
import com.example.medix.models.Properties;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    ArrayList<Properties> pharmacy = new ArrayList<Properties>();
//    ArrayList<Medicine> medList = new ArrayList<Medicine>();
    RecyclerView recyclerView;
    String medName = "";

    public Adapter(ArrayList<Properties> P_List, RecyclerView recyclerView, String medName){
        this.medName = medName;
//        this.medList = medList;
        this.pharmacy = P_List;
        this.recyclerView = recyclerView;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ph_name, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ph_name = itemView.findViewById(R.id.Ph_name);
            price = itemView.findViewById(R.id.price);
        }
    }

    //inflating layout
    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        

        return new MyViewHolder(view);
    }

    // we can change the and edit the views
    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        Properties properties=pharmacy.get(position);

//        String name = pharmacy.g;
        holder.ph_name.setText(properties.getName());
        for(Medicine medicine: properties.getMedicine()){
            Log.d("TAG2", "onBindViewHolder:if "+ medName+medicine.getName());

            if(medName.equalsIgnoreCase(medicine.getName())){
                Log.d("TAG2", "onBindViewHolder:equals "+ medicine.getPrice());

                holder.price.setText("Rs." + medicine.getPrice().toString());
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int itemPosition = recyclerView.getChildLayoutPosition(view);
                String Ph_name = pharmacy.get(position).getName(); // ?????????
                String phoneNo = pharmacy.get(position).getPhone(); // ?????????
                String address = pharmacy.get(position).getAddrStreet(); // ?????????
                if(address==null){
                    address = "Unknown";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(recyclerView.getContext());
                builder.setMessage("Name: " + Ph_name +"\n"+ "Phone: " + phoneNo + "\n" + "address: " + address );
                builder.create();
                builder.show();


//                Toast.makeText(view.getContext(), "Hello"+ Ph_name, Toast.LENGTH_SHORT).show();
            }
        });



//        int price = properties.getMedicine().

//        for (Medicine med: pharmacy.get(position).getMedicine()){
//
//        }
//        int price = properties.getMedicine().getClass().getP
//        holder.price.setText();
    }

    @Override
    public int getItemCount() {
        return pharmacy.size();
    }
//    public static void filteredList(ArrayList<model> filteredList) {
//        Log.d("TAG", "filteredList: "+filteredList.size());
//    }

}
