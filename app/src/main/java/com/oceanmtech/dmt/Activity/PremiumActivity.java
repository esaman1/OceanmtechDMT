package com.oceanmtech.dmt.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.oceanmtech.dmt.QuoteCreater.Constans;
import com.oceanmtech.dmt.IConstant;
import com.oceanmtech.dmt.Model.Premium_model;
import com.oceanmtech.dmt.PrefManager;
import com.oceanmtech.dmt.R;
import com.oceanmtech.dmt.api.ApiInterfase;
import com.oceanmtech.dmt.api.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PremiumActivity extends AppCompatActivity implements PurchasesUpdatedListener {

    private ImageView iv_clos;
    private CardView cv_button;
    private Context mContext = this;
    PrefManager prefrenceHandler;
    BillingClient billingClient;
    public static final String PREF_FILE = "MyPref";
    public static final String PURCHASE_KEY = "purchase";
    public static final String PRODUCT_ID = "premium_upgrade";
    String formattedDate;
    RelativeLayout rl_all;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium);

        prefrenceHandler = new PrefManager(PremiumActivity.this);


        BillingClient.Builder enablePendingPurchases = BillingClient.newBuilder(this).setListener(PremiumActivity.this).enablePendingPurchases();
        enablePendingPurchases.build();
        SetUpBillingClient();

        BindView();
        Click();
        Date();
    }

    private void BindView() {
        iv_clos = (ImageView) findViewById(R.id.iv_close);
        cv_button = (CardView) findViewById(R.id.cv_button);
        rl_all = (RelativeLayout) findViewById(R.id.rl_all);

    }

    private void Click() {
        iv_clos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        rl_all.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (prefrenceHandler.get_is_purchased(IConstant.IS_PADE)) {
                    Toast.makeText(mContext, "You have already Premium ", Toast.LENGTH_SHORT).show();
                } else {
                    if (billingClient.isReady()) {
                        SkuDetailsParams params = SkuDetailsParams.newBuilder()
                                .setSkusList(Arrays.asList(Constans.INAPPID))
                                .setType(BillingClient.SkuType.INAPP)
                                .build();

                        billingClient.querySkuDetailsAsync(params, new SkuDetailsResponseListener() {
                            @Override
                            public void onSkuDetailsResponse(@NonNull BillingResult billingResult, @Nullable List<SkuDetails> list) {
                                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {

                                    BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                                            .setSkuDetails(list.get(0))
                                            .build();
                                    billingClient.launchBillingFlow(PremiumActivity.this, billingFlowParams);
                                } else {
                                    Toast.makeText(mContext, "cannot Query products", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(mContext, "Billing client not ready", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void SetUpBillingClient() {
        billingClient = BillingClient.newBuilder(this).setListener(this).enablePendingPurchases().build();
        billingClient.startConnection(new BillingClientStateListener() {


            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    //   Toast.makeText(mContext, "Sucessfully connect Billing", Toast.LENGTH_SHORT).show();
                } else {
                    //    Toast.makeText(mContext, "" + billingResult.getResponseCode(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                //   Toast.makeText(mContext, "Disconnect from Billing", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK
                && list != null) {

            for (Purchase purchase : list) {
                handlePurchase(purchase);
                SetPaidUser(purchase);


            }

        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            // Handle an error caused by a user cancelling the purchase flow.
        } else {
            // Handle any other error codes.
        }

    }

    void handlePurchase(Purchase purchase) {
        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
            //   premium = true; //In casse purchase was acknowledge before

            prefrenceHandler.setBoolen(IConstant.IS_PADE, true);
            if (!purchase.isAcknowledged()) {
                AcknowledgePurchaseParams acknowledgePurchaseParams =
                        AcknowledgePurchaseParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();

                AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener = new AcknowledgePurchaseResponseListener() {
                    @Override
                    public void onAcknowledgePurchaseResponse(BillingResult billingResult) {

                        prefrenceHandler.setBoolen(IConstant.IS_PADE, true);

                    }
                };
                billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
            }
        }
    }


    private String Date() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());

        return formattedDate;
    }

    public void SetPaidUser(Purchase purchase) {
        ApiInterfase apiInterface = RetrofitClient.getApiClient().create(ApiInterfase.class);
        apiInterface.setPaidUser("premium", new PrefManager(getApplicationContext()).getString(IConstant.IS_R_ID), Date(), purchase.getPurchaseToken()).enqueue(new Callback<Premium_model>() {
            @Override
            public void onResponse(Call<Premium_model> call, Response<Premium_model> response) {

                Premium_model premiumModel = response.body();
                if (response.isSuccessful()) {
                    if (premiumModel.getStatus().equals("Success")) {
                        prefrenceHandler.setBoolen(IConstant.IS_PADE, true);
                        //    Toast.makeText(mContext, "Barabar", Toast.LENGTH_SHORT).show();

                    } else {
                        SetPaidUser(purchase);
                    }
                } else {
                    SetPaidUser(purchase);

                }

            }

            @Override
            public void onFailure(Call<Premium_model> call, Throwable t) {
                call.cancel();
            }
        });

    }

}