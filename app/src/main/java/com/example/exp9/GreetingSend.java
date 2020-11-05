package com.example.exp9;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GreetingSend extends AppCompatActivity {

    Button cn3, cn5, select, search;
    TextView name;
    EditText rphno;
    static String receiver_phno;
    public static final int PICK_CODE = 0;
    static String namedata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting_send);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS,},
                PackageManager.PERMISSION_GRANTED);

        rphno = findViewById(R.id.receive_phno);
        cn3 = findViewById(R.id.continue3);
        cn5 = findViewById(R.id.continue5);
        select = findViewById(R.id.button);
        search = findViewById(R.id.search);
        name = findViewById(R.id.name);

        cn3.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), GreetingMessage.class);
            startActivity(intent);
        });


        search.setOnClickListener(view -> {

            receiver_phno = rphno.getText().toString();
            try{
                String phonenumber = receiver_phno;
                Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,Uri.encode(phonenumber));
                String[] projection = new String[] {ContactsContract.Contacts.DISPLAY_NAME};
                Cursor cursor = getContentResolver().query(uri,projection,null,null,null);
                if(cursor!=null && cursor.moveToFirst()){
                    String contactname = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    name.setText("Receiver's name :" + contactname);
                    namedata = contactname;
                    Toast.makeText(getApplicationContext(),namedata,Toast.LENGTH_LONG).show();
                }
                cursor.close();
            }catch(Exception e){
                Toast.makeText(getApplicationContext(),"failure\n"+e.getMessage(),Toast.LENGTH_LONG).show();
            }

        });

        cn5.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });

        select.setOnClickListener(view -> {

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
            startActivityForResult(intent,PICK_CODE);


        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_CODE && resultCode == RESULT_OK){
            Uri uri = data.getData();
            Cursor cursor = getContentResolver().query(uri,null,null,null,null);
            if(cursor!=null && cursor.moveToFirst()){
                namedata = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number =cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                rphno.setText(number);
                name.setText(namedata);
            }
        }
    }

}