package com.toan04.lab5.update;

import com.toan04.lab5.SvrResponseSP;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceUpdateSP {
    @FormUrlEncoded
    @POST("update.php")
    Call<SvrResponseSP> updateSP(
            @Field("MaMP") String MaMP,
            @Field("TenSP") String TenSP,
            @Field("MoTa") String MoTa
    );
}
