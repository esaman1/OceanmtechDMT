package com.oceanmtech.dmt.PayU;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.oceanmtech.dmt.Activity.StartPaymentActivity;
import com.oceanmtech.dmt.R;

public class PayUActivity extends AppCompatActivity {

    EditText phone, amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_u);

        Button btn = (Button) findViewById(R.id.start_transaction);
        phone = (EditText) findViewById(R.id.phone);
        amount = (EditText) findViewById(R.id.amountid);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayUActivity.this, StartPaymentActivity.class);
                intent.putExtra("phone", phone.getText().toString());
                intent.putExtra("amount", amount.getText().toString());
                startActivity(intent);
            }
        });
    }
}