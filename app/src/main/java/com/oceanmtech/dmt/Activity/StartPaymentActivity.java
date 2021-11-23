package com.oceanmtech.dmt.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.payumoney.core.PayUmoneySdkInitializer;
import com.payumoney.core.entity.TransactionResponse;
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager;
import com.oceanmtech.dmt.NewPremium.ServiceWrapper;
import com.oceanmtech.dmt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartPaymentActivity extends AppCompatActivity {

    PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
    //declare paymentParam object
    PayUmoneySdkInitializer.PaymentParam paymentParam = null;
    String TAG ="mainActivity", txnid ="txt12346", amount ="20", phone ="8866747888",
            prodname ="OceanmtechDMT", firstname ="OceanmtechDMT", email ="youngbreedtechnologies@gmail.com",
            merchantId ="5488798", merchantkey="LfXySGa4";  //   first test key only

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_payment);

        Intent intent = getIntent();
        phone = intent.getExtras().getString("phone");
        amount = intent.getExtras().getString("amount");
        startpay();

    }

    public void startpay(){
        builder.setAmount(amount)                          // Payment amount
                .setTxnId(txnid)                     // Transaction ID
                .setPhone(phone)                   // User Phone number
                .setProductName(prodname)                   // Product Name or description
                .setFirstName(firstname)                              // User First name
                .setEmail(email)              // User Email ID
                .setsUrl("https://www.payumoney.com/mobileapp/payumoney/success.php")     // Success URL (surl)
                .setfUrl("https://www.payumoney.com/mobileapp/payumoney/failure.php")     //Failure URL (furl)
                .setUdf1("")
                .setUdf2("")
                .setUdf3("")
                .setUdf4("")
                .setUdf5("")
                .setUdf6("")
                .setUdf7("")
                .setUdf8("")
                .setUdf9("")
                .setUdf10("")
                .setIsDebug(true)                              // Integration environment - true (Debug)/ false(Production)
                .setKey("HLm81v8TkYTcza")                        // Merchant key
                .setMerchantId(merchantId);
        try {
            paymentParam = builder.build();
            // generateHashFromServer(paymentParam );
            getHashkey();
        } catch (Exception e) {
        }
    }

    public void getHashkey(){
        ServiceWrapper service = new ServiceWrapper(null);
        Call<String> call = service.newHashCall(merchantkey, txnid, amount, prodname, firstname, email);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String merchantHash= response.body();
                if (merchantHash.isEmpty() || merchantHash.equals("")) {
                    Toast.makeText(StartPaymentActivity.this, "Could not generate hash", Toast.LENGTH_SHORT).show();

                } else {
                    paymentParam.setMerchantHash(merchantHash);
                    PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam, StartPaymentActivity.this, R.style.AppTheme_default, false);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

// PayUMoneySdk: Success -- payuResponse{"id":225642,"mode":"CC","status":"success","unmappedstatus":"captured","key":"9yrcMzso","txnid":"223013","transaction_fee":"20.00","amount":"20.00","cardCategory":"domestic","discount":"0.00","addedon":"2018-12-31 09:09:43","productinfo":"a2z shop","firstname":"kamal","email":"kamal.bunkar07@gmail.com","phone":"9144040888","hash":"b22172fcc0ab6dbc0a52925ebbd0297cca6793328a8dd1e61ef510b9545d9c851600fdbdc985960f803412c49e4faa56968b3e70c67fe62eaed7cecacdfdb5b3","field1":"562178","field2":"823386","field3":"2061","field4":"MC","field5":"167227964249","field6":"00","field7":"0","field8":"3DS","field9":" Verification of Secure Hash Failed: E700 -- Approved -- Transaction Successful -- Unable to be determined--E000","payment_source":"payu","PG_TYPE":"AXISPG","bank_ref_no":"562178","ibibo_code":"VISA","error_code":"E000","Error_Message":"No Error","name_on_card":"payu","card_no":"401200XXXXXX1112","is_seamless":1,"surl":"https://www.payumoney.com/sandbox/payment/postBackParam.do","furl":"https://www.payumoney.com/sandbox/payment/postBackParam.do"}
        // Result Code is -1 send from Payumoney activity
        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null) {
            TransactionResponse transactionResponse = data.getParcelableExtra( PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE );
            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {
                if(transactionResponse.getTransactionStatus().equals( TransactionResponse.TransactionStatus.SUCCESSFUL )){
                    //Success Transaction
                } else{
                    //Failure Transaction
                }
                // Response from Payumoney
                String payuResponse = transactionResponse.getPayuResponse();
                // Response from SURl and FURL
                String merchantResponse = transactionResponse.getTransactionDetails();
            } /* else if (resultModel != null && resultModel.getError() != null) {
                Log.d(TAG, "Error response : " + resultModel.getError().getTransactionResponse());
            } else {
                Log.d(TAG, "Both objects are null!");
            }*/
        }
    }
}