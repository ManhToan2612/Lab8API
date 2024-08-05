package com.toan04.lab5;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceInsertSP {
    @FormUrlEncoded
    @POST("insert2.php")
    Call<SvrResponseSP> insertSP(
            @Field("MaMP") String MaMP,
            @Field("TenSP") String TenSP,
            @Field("MoTa") String MoTa
    );
}
