package com.example.dsouchon.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;


public class HomeActivity extends AppCompatActivity {

    public String mPurpose;
    ViewFlipper viewFlipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
    }


    //links child button to parent flipper layout and contains animations
    public void PackPallet(View view) {
        viewFlipper.setDisplayedChild(1);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.homescreen1)));

        mPurpose = "PackPallet";
        //pop animation for layout
        LinearLayout Layoutpop1 = (LinearLayout)this.findViewById(R.id.homescreen1);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop1.startAnimation(expandIn);
    }



    public void Delivery(View view) {
        viewFlipper.setDisplayedChild(2);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.homescreen2)));
        mPurpose = "Delivery";
        //pop animation for layout
        LinearLayout Layoutpop2 = (LinearLayout)this.findViewById(R.id.homescreen2);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop2.startAnimation(expandIn);
    }


    public void PreInstallation(View view) {
        viewFlipper.setDisplayedChild(3);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.homescreen3)));
        mPurpose = "PreInstallation";
        //pop animation for layout
        LinearLayout Layoutpop2 = (LinearLayout)this.findViewById(R.id.homescreen3);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop2.startAnimation(expandIn);
    }


    public void Collection(View view) {
        viewFlipper.setDisplayedChild(4);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.homescreen4)));
        mPurpose = "Collection";
        //pop animation for layout
        LinearLayout Layoutpop2 = (LinearLayout)this.findViewById(R.id.homescreen4);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop2.startAnimation(expandIn);
    }



    //go to barcode scanner

    public void GoToScanbarcode(View view) {

        Intent intent = new Intent(HomeActivity.this, OpenScanner.class);
        intent.putExtra("Purpose", mPurpose);
        Local.Set(getApplicationContext(), "Purpose",  mPurpose);
        finish();

        startActivity(intent);
    }
}
