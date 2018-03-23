package com.example.dsouchon.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class BarcodeScanner extends AppCompatActivity {
    public String mPurpose;
    public String mThisBarcode;
public String mContentatedbarcodes;



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
        TextView txtPurpose = (TextView) findViewById(R.id.txtPurpose);
        Intent intent = getIntent();

        Bundle bd = intent.getExtras();
        mPurpose  = Local.Get(getApplicationContext(), "Purpose");

        txtPurpose.setText(mPurpose);

        if(bd != null)
        {
            String barcode = (String) bd.get("Barcode");
            editTagNumber.setText(barcode);
        }


        //get the extras that are returned from the intent
        String tagNo = getIntent().getStringExtra( "Barcode");
        editTagNumber.setText(tagNo) ;

        mThisBarcode = editTagNumber.getText().toString();

        mContentatedbarcodes = Local.Get(getApplicationContext(), "BarcodeList");

        mContentatedbarcodes = String.format("%s;%s", mContentatedbarcodes, mThisBarcode);

        Local.Set(getApplicationContext(), "BarcodeList", mContentatedbarcodes);

        Toast.makeText(BarcodeScanner.this,String.format("Barcodes scanned so far: %s", mContentatedbarcodes), Toast.LENGTH_LONG).show();

        }



    public void scanBar(View view) {

        Intent intent = new Intent(BarcodeScanner.this, OpenScanner.class );
        finish();
        startActivity(intent);

        Button scancode = (Button)findViewById(R.id.scanner2);
        scancode.setVisibility(View.VISIBLE);



    }


    public void scanningComplete(View view) {

        Intent intent = new Intent(BarcodeScanner.this, UploadActivity.class );
        finish();
        startActivity(intent);

        Button scancode = (Button)findViewById(R.id.scanner2);
        scancode.setVisibility(View.VISIBLE);



    }
    public void errorDelete(View view) {

        //remove latest barcode from the csv list
        mContentatedbarcodes = mContentatedbarcodes.replace(mThisBarcode + ";", "");
        //overwrite the saved csvList
        Local.Set(getApplicationContext(), "BarcodeList", mContentatedbarcodes);

        //Proceed with new scan

        Intent intent = new Intent(BarcodeScanner.this, OpenScanner.class );
        finish();
        startActivity(intent);

        Button scancode = (Button)findViewById(R.id.scanner2);
        scancode.setVisibility(View.VISIBLE);



    }

    public void Reset(View view) {

        //remove latest barcode from the csv list
        mContentatedbarcodes = "";
        //overwrite the saved csvList
        Local.Set(getApplicationContext(), "BarcodeList", mContentatedbarcodes);

        //Proceed with new scan

        Intent intent = new Intent(BarcodeScanner.this, OpenScanner.class );
        finish();
        startActivity(intent);

        Button scancode = (Button)findViewById(R.id.scanner2);
        scancode.setVisibility(View.VISIBLE);



    }


    }









