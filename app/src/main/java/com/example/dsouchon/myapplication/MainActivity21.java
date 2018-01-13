package com.example.dsouchon.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Handler;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity21 extends AppCompatActivity {










    NfcAdapter nfcAdapter;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main21);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        //hides progress bar

        ProgressBar mprogressbar;
        mprogressbar = (ProgressBar) findViewById(R.id.progressbar);
        mprogressbar.setVisibility(View.INVISIBLE);

        Button scanbutton = (Button)findViewById(R.id.buttonScanTag);
        scanbutton.setVisibility(View.INVISIBLE);

        NfcAdapter adapter = NfcAdapter.getDefaultAdapter(this);
        if (adapter == null) {
            Intent intent = new Intent(MainActivity21.this, MainActivity.class );
            finish();
            startActivity(intent);
            Toast.makeText(MainActivity21.this,"Your device does not support this feature", Toast.LENGTH_LONG).show();

        }


        //Make yes no buttons invisible

        Button buttonNo = (Button)findViewById(R.id.buttonNo);

        buttonNo.setVisibility(View.GONE);



        if(Local.isSet(getApplicationContext(), "EventName")) {
            TextView textEvent = (TextView) findViewById(R.id.textEventName);
            textEvent.setText(Local.Get(getApplicationContext(), "EventName"));
        }

        final  AlertDialog ad=new AlertDialog.Builder(this).create();

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        // Get name and email from global/application context
        final String eventImageString  = globalVariable.getEventImage();

        if(eventImageString != null && eventImageString.length() > 0) {
            ImageView imageViewEventImage = (ImageView) findViewById(R.id.imageViewEventImage);
            byte[] decodedString = Base64.decode(eventImageString, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageViewEventImage.setImageBitmap(decodedByte);
        }










    }

    public void ButtonNo_Click(View view)
    {

        TextView labelScanResult = (TextView)findViewById(R.id.labelScanResult);
        TextView nameSurname = (TextView)findViewById(R.id.nameSurname);
        TextView idNumber = (TextView)findViewById(R.id.idNumber);
        TextView ticketClass = (TextView)findViewById(R.id.ticketClass);
        EditText editTagNumber = (EditText)findViewById(R.id.editTagNumber);

        labelScanResult.setText("");
        nameSurname.setText("");
        idNumber.setText("");
        ticketClass.setText("");
        editTagNumber.setText("");

        //Make yes no buttons invisible
        Button buttonYes = (Button)findViewById(R.id.buttonYes);
        Button buttonNo = (Button)findViewById(R.id.buttonNo);
        buttonYes.setVisibility(View.INVISIBLE);
        buttonNo.setVisibility(View.INVISIBLE);

    }


    //Logic business after the web service complete here
//Do the thing that modify the UI in a function like this
    private void onTaskCompleted(Object _response)
    {


    }

    public void openSetupNow(View view) {
        Intent in = new Intent(MainActivity21.this, MainActivity.class);
        startActivity(in);
        finish();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Toast.makeText(MainActivity21.this,"Click Home Button to exit", Toast.LENGTH_SHORT).show();




    }












    @Override
    protected void onResume(){

        super.onResume();

        enableForegroundDispatchSystem();

    }
    @Override
    protected void onPause(){

        super.onPause();
        disableForegroundDispatchSystem();

    }

    private void enableForegroundDispatchSystem(){
        Intent intent = new Intent(this, MainActivity21.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        IntentFilter[] intentFilters = new IntentFilter[] {};
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilters, null);
    }

    private void disableForegroundDispatchSystem(){
        nfcAdapter.disableForegroundDispatch(this);
    }

    private void formatTag(Tag tag, NdefMessage ndefMessage)
    {
        try{
            NdefFormatable ndefFormatable = NdefFormatable.get(tag);

            if(ndefFormatable == null)
            {

                Toast.makeText(this, "Tag is not ndef formattable!", Toast.LENGTH_SHORT).show();


            }
            ndefFormatable.connect();
            ndefFormatable.format(ndefMessage);
            ndefFormatable.close();

            Toast.makeText(this, "Tag written!", Toast.LENGTH_SHORT).show();

        }
        catch(Exception e){
            Log.e("formatTag", e.getMessage());
        }

    }

    private void writeNdefMessage(Tag tag, NdefMessage ndefMessage)
    {
        try{


            if(tag == null)
            {

                Toast.makeText(this, "Tag object cannot be null!", Toast.LENGTH_SHORT).show();

                return;
            }
            Ndef ndef = Ndef.get(tag);
            if (ndef == null)
            {
//format tag with the ndef format and write the message
                formatTag(tag, ndefMessage);


            }
            else
            {
                ndef.connect();
                if(!ndef.isWritable())
                {
                    Toast.makeText(this, "Tag is not writable!", Toast.LENGTH_SHORT).show();
                    ndef.close();
                    return;


                }
                ndef.writeNdefMessage(ndefMessage);
                ndef.close();
                Toast.makeText(this, "Tag written!", Toast.LENGTH_SHORT).show();

            }
        }
        catch(Exception e){
            Log.e("writeNdefMessage", e.getMessage());
        }

    }


    private NdefRecord createTextRecord(String content)
    {
        try{
            byte[] language;
            language = Locale.getDefault().getLanguage().getBytes("QTF-8");
            final byte[] text = content.getBytes("QTF-8");
            final int languageSize = language.length;
            final int textLength = text.length;
            final ByteArrayOutputStream payload = new ByteArrayOutputStream(1 + languageSize + textLength);

            payload.write((byte)(languageSize & 0x1F));
            payload.write(language, 0, languageSize);
            payload.write(text, 0 , textLength);

            return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], payload.toByteArray());





        }
        catch(Exception e){

            Log.e("createTextRecord", e.getMessage());

        }
        return  null;
    }

    private NdefMessage createNdefMessage(String content)
    {
        NdefRecord ndefRecord = createTextRecord(content);

        NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]{ ndefRecord});

        return ndefMessage;
    }

//NFC Stuff End



}

