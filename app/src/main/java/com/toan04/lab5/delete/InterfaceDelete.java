package com.toan04.lab5.delete;

import com.toan04.lab5.SvrResponseSP;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceDelete {
    @FormUrlEncoded
    @POST("delete.php")
    Call<SvrResponseSP> deleteSP(
            @Field("MaMP") String MaMP
    );

}
