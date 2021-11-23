package com.oceanmtech.dmt.NewPremium;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.Model.Premium_model;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.api.ApiInterfase;
import com.oceanmtech.dmt.api.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentGatewayActivity extends AppCompatActivity implements PaymentResultListener {
    private RelativeLayout buttonConfirmOrder,buttonConfirmOrder1,buttonConfirmOrder2,buttonConfirmOrder3,buttonConfirmOrder4;
    EditText editTextPayment;
    ImageView closeicon;
    String aaveche;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);
        editTextPayment = findViewById(R.id.editTextPayment);
        aaveche = getIntent().getStringExtra("txt_year");
        editTextPayment.setText(aaveche);
        Checkout.preload(getApplicationContext());
        buttonConfirmOrder = findViewById(R.id.rl_all);
        buttonConfirmOrder1 = findViewById(R.id.rl_all_1);
        buttonConfirmOrder2 = findViewById(R.id.rl_all_2);
        buttonConfirmOrder3 = findViewById(R.id.rl_all_3);
        buttonConfirmOrder4 = findViewById(R.id.rl_all_4);
        closeicon = findViewById(R.id.closeicon);

        Date();


        closeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        buttonConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpPayment();
            }
        });

        buttonConfirmOrder1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpPayment1();
            }
        });

        buttonConfirmOrder2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpPayment2();
            }
        });

        buttonConfirmOrder3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpPayment3();
            }
        });

        buttonConfirmOrder4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpPayment4();
            }
        });


        // youngbreedtechnologies@gmail.com

    }
    private void setUpPayment() {
        final Activity activity = this;
        final Checkout c = new Checkout();

        JSONObject object = new JSONObject();
        try {
            object.put("name", "OceanmtechDMT");
            object.put("description", "product");
            object.put("image", "" + R.drawable.newlogo);
            object.put("currency", "INR");
            object.put("amount", "19900.00");
            JSONObject prefill = new JSONObject();
            prefill.put("email", new PrefManager(getApplicationContext()).getString(IConstant.BUSINESS_EMAIL));
            prefill.put("contact", new PrefManager(getApplicationContext()).getString(IConstant.MOBILE_NO));
            object.put("prefill", prefill);
            c.open(activity, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /////////////////

    private void setUpPayment1() {
        final Activity activity = this;
        final Checkout c = new Checkout();

        JSONObject object = new JSONObject();
        try {
            object.put("name", "OceanmtechDMT");
            object.put("description", "product");
            object.put("image", "" + R.drawable.newlogo);
            object.put("currency", "INR");
            object.put("amount", "39900.00");
            JSONObject prefill = new JSONObject();
            prefill.put("email", new PrefManager(getApplicationContext()).getString(IConstant.BUSINESS_EMAIL));
            prefill.put("contact", new PrefManager(getApplicationContext()).getString(IConstant.MOBILE_NO));
            object.put("prefill", prefill);
            c.open(activity, object);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //////////////////

    private void setUpPayment2() {
        final Activity activity = this;
        final Checkout c = new Checkout();

        JSONObject object = new JSONObject();
        try {
            object.put("name", "OceanmtechDMT");
            object.put("description", "product");
            object.put("image", "" + R.drawable.newlogo);
            object.put("currency", "INR");
            object.put("amount", "69900.00");
            JSONObject prefill = new JSONObject();
            prefill.put("email", new PrefManager(getApplicationContext()).getString(IConstant.BUSINESS_EMAIL));
            prefill.put("contact", new PrefManager(getApplicationContext()).getString(IConstant.MOBILE_NO));
            object.put("prefill", prefill);
            c.open(activity, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    /////////////////


    private void setUpPayment3() {
        final Activity activity = this;
        final Checkout c = new Checkout();

        JSONObject object = new JSONObject();
        try {
            object.put("name", "OceanmtechDMT");
            object.put("description", "product");
            object.put("image", "" + R.drawable.newlogo);
            object.put("currency", "INR");
            object.put("amount", "99900.00");
            JSONObject prefill = new JSONObject();
            prefill.put("email", new PrefManager(getApplicationContext()).getString(IConstant.BUSINESS_EMAIL));
            prefill.put("contact", new PrefManager(getApplicationContext()).getString(IConstant.MOBILE_NO));
            object.put("prefill", prefill);
            c.open(activity, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




    ////////////


    private void setUpPayment4() {
        final Activity activity = this;
        final Checkout c = new Checkout();

        JSONObject object = new JSONObject();
        try {
            object.put("name", "OceanmtechDMT");
            object.put("description", "product");
            object.put("image", "" + R.drawable.newlogo);
            object.put("currency", "INR");
            object.put("amount", "129900.00");
            JSONObject prefill = new JSONObject();
            prefill.put("email", new PrefManager(getApplicationContext()).getString(IConstant.BUSINESS_EMAIL));
            prefill.put("contact", new PrefManager(getApplicationContext()).getString(IConstant.MOBILE_NO));
            object.put("prefill", prefill);
            c.open(activity, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentId) {
        Toast.makeText(this, "Your payment sucessfully" , Toast.LENGTH_SHORT).show();

        SetPaidUser(razorpayPaymentId);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();
    }


    private String Date() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());

        return formattedDate;
    }


    public void SetPaidUser(String razorpayPaymentId) {
        ApiInterfase apiInterface = RetrofitClient.getApiClient().create(ApiInterfase.class);
        apiInterface.setPaidUser("premium", new PrefManager(getApplicationContext()).getString(IConstant.IS_R_ID), Date(), razorpayPaymentId).enqueue(new Callback<Premium_model>() {
            @Override
            public void onResponse(Call<Premium_model> call, Response<Premium_model> response) {
                Gson gson = new Gson();
                String successResponse = gson.toJson(response.body());
                Log.w("SagarSagar",""+successResponse);
                Premium_model premiumModel = response.body();
                if (response.isSuccessful()) {
                    if (premiumModel.getStatus().equals("Success")) {
                        new PrefManager(PaymentGatewayActivity.this).setBoolen(IConstant.IS_PADE, true);
                        //    Toast.makeText(mContext, "Barabar", Toast.LENGTH_SHORT).show();

                    } else {
                        SetPaidUser(razorpayPaymentId);
                    }
                } else {
                    SetPaidUser(razorpayPaymentId);

                }

            }

            @Override
            public void onFailure(Call<Premium_model> call, Throwable t) {
                Log.w("SagarSagar",""+t.getMessage());

                call.cancel();
            }
        });

    }

}