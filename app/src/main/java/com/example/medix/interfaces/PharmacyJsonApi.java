package com.example.medix.interfaces;

import com.example.medix.models.Pharmacy;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PharmacyJsonApi {

    @GET("b/62b2906d449a1f38211489dc/2")//62b2906d449a1f38211489dc/1
    Call<Pharmacy> getData();

}
