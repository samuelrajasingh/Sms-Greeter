package com.example.exp9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GreetingConfirm extends AppCompatActivity {

    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting_confirm);

        ok = findViewById(R.id.ok_button);

        ok.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), GreetingSend.class);
            startActivity(intent);
            GreetingSend.namedata = "";
        });

    }
}