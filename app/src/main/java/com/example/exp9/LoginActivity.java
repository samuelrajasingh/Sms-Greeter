package com.example.exp9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    EditText loginphno;
    Button cn1;
    static String OTP;
    static int otp;
    Random r = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);


        loginphno = findViewById(R.id.login_phno);
        cn1 = findViewById(R.id.continue1);

        cn1.setOnClickListener(view -> {

            String phno = loginphno.getText().toString();

            if (phno != null && phno.length()==10){

                Intent intent = new Intent(getApplicationContext(), OtpActivity.class);
                PendingIntent pendingIntent= PendingIntent.getActivity(getApplicationContext(),1,intent,0);

                SmsManager smsManager = SmsManager.getDefault();

                //OTP
                int number = r.nextInt(9999);
                OTP = String.format("%04d",number);
                otp = number;

                Log.e("TAG", "otp: "+OTP );

                smsManager.sendTextMessage(phno,null,"Your OTP for login into wishes is "+OTP+".",pendingIntent,null);
                Toast.makeText(getApplicationContext(),"OTP sent.",Toast.LENGTH_LONG).show();

            }
            else {
                Toast.makeText(getApplicationContext(),"Please enter a proper phone number.",Toast.LENGTH_LONG).show();
            }

        });


    }
}