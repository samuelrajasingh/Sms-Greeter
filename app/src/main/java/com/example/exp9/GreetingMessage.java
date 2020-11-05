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

public class GreetingMessage extends AppCompatActivity {

    Button cn4;
    EditText msg;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting_message);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);

        cn4 = findViewById(R.id.continue4);
        msg = findViewById(R.id.msg);

        cn4.setOnClickListener(view -> {
            message = msg.getText().toString();
            if (message != null){

                Intent intent = new Intent(getApplicationContext(), GreetingConfirm.class);
                PendingIntent pendingIntent= PendingIntent.getActivity(getApplicationContext(),1,intent,0);

                SmsManager smsManager = SmsManager.getDefault();

                Log.d("TAG", "destination Addr : "+GreetingSend.receiver_phno );

                smsManager.sendTextMessage(GreetingSend.receiver_phno,null,"Greetings "+GreetingSend.namedata+"!\n " + message+"\n\n This message was brought you by WISHES.",pendingIntent,null);

            }
            else {
                Toast.makeText(getApplicationContext(),"Please enter a message.",Toast.LENGTH_LONG).show();
            }

        });

    }
}