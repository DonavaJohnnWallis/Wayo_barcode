package com.example.dsouchon.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {




    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Toast.makeText(MainActivity.this,"Please Logout before exiting!", Toast.LENGTH_SHORT).show();

    }




    public void ScanCode(View view) {


        Intent intent = new Intent(MainActivity.this, OpenScanner.class );
        finish();
        startActivity(intent);

    }

}
