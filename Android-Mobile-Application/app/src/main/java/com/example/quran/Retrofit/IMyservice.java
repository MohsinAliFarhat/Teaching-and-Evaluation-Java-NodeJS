package com.example.quran.Retrofit;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IMyservice {

    @POST("register")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("email") String email,
                                    @Field("name") String name,
                                    @Field("password") String password);




    //////
    @POST("login")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("email") String email,
                                 @Field("password") String password);

    @POST("send-email")
    @FormUrlEncoded
    Observable<String> sendNewMail(@Field("email") String email,
                                   @Field("code") String code);


    @POST("update-password")
    @FormUrlEncoded
    Observable<String> update_password(@Field("email")String email, @Field("pass")String pass);



    @POST("letters/adddb")
    @FormUrlEncoded
    Observable<String> update_letter_accuracy(@Field("Email")String Email, @Field("Level")String Level, @Field("Acc_value")Double Acc_value);


    @POST("diacritics/adddb")
    @FormUrlEncoded
    Observable<String> update_diacritic_accuracy(@Field("Email")String Email, @Field("Level")String Level, @Field("Acc_value")Double Acc_value);



    @POST("words/adddb")
    @FormUrlEncoded
    Observable<String> update_word_accuracy(@Field("Email")String Email, @Field("Level")String Level, @Field("Acc_value")Double Acc_value);


    @POST("letters/showAccuracy")
    @FormUrlEncoded
    Observable<String> get_letter_accuracy(@Field("Email")String Email);


    @POST("diacritics/showAccuracy")
    @FormUrlEncoded
    Observable<String> get_diacritics_accuracy(@Field("Email")String Email);



    @POST("words/showAccuracy")
    @FormUrlEncoded
    Observable<String> get_words_accuracy(@Field("Email")String Email);



}
