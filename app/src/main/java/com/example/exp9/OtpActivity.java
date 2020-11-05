package com.example.exp9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OtpActivity extends AppCompatActivity {

    EditText otp1,otp2,otp3,otp4;
    Button cn2;
    int otp_check;
    int otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        cn2 = findViewById(R.id.continue2);
        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);

        Intent recv = getIntent();
        Bundle data = recv.getExtras();
        otp_check = data.getInt("otp", 0);


        cn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                otp = Integer.parseInt(otp1.getText().toString() + otp2.getText().toString() + otp3.getText().toString() + otp4.getText().toString());

                if (otp == LoginActivity.otp){
                    Intent intent = new Intent(getApplicationContext(), GreetingSend.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Wrong OTP. Enter again."+otp_check,Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}