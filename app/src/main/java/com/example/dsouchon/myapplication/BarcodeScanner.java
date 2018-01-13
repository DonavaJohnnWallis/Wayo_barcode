package com.example.dsouchon.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;


public class BarcodeScanner extends AppCompatActivity {





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }






    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barcode_resault);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TextView editTagNumber = (TextView) findViewById(R.id.editTagNumber);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            String barcode = (String) bd.get("Barcode");
            editTagNumber.setText(barcode);
        }


        //get the extras that are returned from the intent
        String tagNo = getIntent().getStringExtra( "Barcode");
        editTagNumber.setText(tagNo) ;

        }



    public void scanBar(View view) {

        Intent intent = new Intent(BarcodeScanner.this, MainActivity2.class );
        finish();
        startActivity(intent);

        Button scancode = (Button)findViewById(R.id.scanner2);
        scancode.setVisibility(View.VISIBLE);



    }



    }









