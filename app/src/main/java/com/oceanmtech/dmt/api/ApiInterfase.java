package com.oceanmtech.dmt.api;

import com.oceanmtech.dmt.Model.BackgroundImageModel;
import com.oceanmtech.dmt.Model.Business_Add_Model;
import com.oceanmtech.dmt.Model.Business_Get_model;
import com.oceanmtech.dmt.Model.BussinessCat_Data_model;
import com.oceanmtech.dmt.Model.BussinessPostModel;
import com.oceanmtech.dmt.Model.Festival_M;
import com.oceanmtech.dmt.Model.GET_Greetings_Category;
import com.oceanmtech.dmt.Model.GET_Greetings_CategoryData;
import com.oceanmtech.dmt.Model.Home_Data_Model;
import com.oceanmtech.dmt.Model.OtpModel;
import com.oceanmtech.dmt.Model.Premium_model;
import com.oceanmtech.dmt.Model.SliderModel;
import com.oceanmtech.dmt.Model.Status;
import com.oceanmtech.dmt.Model.Trial_Model;
import com.oceanmtech.dmt.Model.UpdateModel;
import com.oceanmtech.dmt.Model.UserData3;
import com.oceanmtech.dmt.Model.UserRegister;
import com.oceanmtech.dmt.Model.Video_Model;
import com.oceanmtech.dmt.Notification.Notification_Model;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterfase {

    @FormUrlEncoded
    @POST("api.php")
    Call<UserData3> getCategory(
            @Field("action") String data,
            @Field("category_id") String category_id);

    @FormUrlEncoded
    @POST("api.php")
    Call<Home_Data_Model> getCategory1(
            @Field("action") String home_data);


    @FormUrlEncoded
    @POST("api.php")
    Call<Trial_Model> getTrial(
            @Field("action") String date,
            @Field("r_id") String r_id);


    @FormUrlEncoded
    @POST("api.php")
    Call<UserRegister> UserRegister(
            @Field("action") String user_register,
            @Field("unique_id") String unique_id,
            @Field("mobile_no") String mobile_no);


    @FormUrlEncoded
    @POST("api.php")
    Call<Festival_M> getFestival(
            @Field("action") String upcoming_festival_category);


/*    @FormUrlEncoded
    @POST("api.php")
    Call<FestivalGetData> getFestivalData(
            @Field("action") String upcoming_festival,
            @Field("category_id") String category_id);*/

    @FormUrlEncoded
    @POST("api.php")
    Call<Status> GetStatus(
            @Field("action") String status,
            @Field("unique_id") String unique_id);

//    @FormUrlEncoded
//    @POST("api.php")
//    Call<Business_Add_Model> Business_Add(
//            @Field("action") String add_business,
//            @Field("business_name") String business_name,
//            @Field("b_email") String b_email,
//            @Field("b_address") String b_address,
//            @Field("b_website") String b_website);

    @Multipart
    @POST("api.php")
    Call<Business_Add_Model> Business_Add(
            @Part("action") RequestBody add_business_new,
            @Part("user_id") RequestBody user_id,
            @Part("business_name") RequestBody business_name,
            @Part("b_email") RequestBody b_email,
            @Part("b_website") RequestBody b_website,
            @Part("b_address") RequestBody b_address,
            @Part MultipartBody.Part b_logo,
            @Part("b_mobile_number") RequestBody b_mobile_number);

    @FormUrlEncoded
    @POST("api.php")
    Call<Business_Get_model> get_Business(
            @Field("action") String business,
            @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("api.php")
    Call<SliderModel> get_slider(@Field("action") String slider);

    @Multipart
    @POST("api.php")
    Call<UpdateModel> Business_Update(
            @Part("action") RequestBody update_business,
            @Part("b_id") RequestBody b_id,
            @Part("business_name") RequestBody business_name,
            @Part("b_email") RequestBody b_email,
            @Part("b_website") RequestBody b_website,
            @Part("b_address") RequestBody b_address,
            @Part MultipartBody.Part b_logo,
            @Part("b_mobile_number") RequestBody b_mobile_number);

    @GET("http://sms.alphacomputers.biz/api/v3/index.php")
    Call<OtpModel> get_otp(@Query(value = "method") String method,
                           @Query(value = "api_key") String api_key,
                           @Query(value = "to") String MobileNo,
                           @Query(value = "sender") String sender,
                           @Query(value = "message") String message,
                           @Query(value = "format") String format,
                           @Query(value = "flash") String flash);

    @FormUrlEncoded
    @POST("api.php")
    Call<Premium_model> setPaidUser(@Field("action") String premium,
                                    @Field("user_id") String user_id,
                                    @Field("purchase_date") String purchase_date,
                                    @Field("payment_id") String payment_id);

    @FormUrlEncoded
    @POST("api.php")
    Call<GET_Greetings_Category> getgreetingscategory(
            @Field("action") String greetings_category);

    @FormUrlEncoded
    @POST("api.php")
    Call<GET_Greetings_CategoryData> getgreetingscategorydata(
            @Field("action") String greetings_category_data,
            @Field("category_id") String category_id);


//BussinessPost

    @FormUrlEncoded
    @POST("api.php")
    Call<BussinessPostModel> getBussinesspostcat(
            @Field("action") String business_category);

    @FormUrlEncoded
    @POST("api.php")
    Call<BussinessCat_Data_model> getbussinesscategorydata(
            @Field("action") String business_category_data,
            @Field("category_id") String category_id);


    //Notification

    @FormUrlEncoded
    @POST("api.php")
    Call<Notification_Model> getnotification(
            @Field("action") String notification,
            @Field("token") String token);



    /////VIDEO/////
    @FormUrlEncoded
    @POST("api.php")
    Call<Video_Model> getVideo(
            @Field("action") String video,
            @Field("category_id") String category_id);

    @GET("getBgList")
    Call<BackgroundImageModel> GetbgImage();
}


