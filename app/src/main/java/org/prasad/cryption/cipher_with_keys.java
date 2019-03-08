package org.prasad.cryption;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class cipher_with_keys extends AppCompatActivity {
    private EditText message_box, key_box;
    private String message, key, s;
    private Switch switchbutton;
    private boolean mode;
    private Button submit;
    private TextView content, answer;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cipher_with_keys);
        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle(getIntent().getStringExtra("cipher"));
            toolbar.setDisplayHomeAsUpEnabled(true);
        }

        message_box = findViewById(R.id.id_cipher_text);
        key_box = findViewById(R.id.id_key);
        switchbutton = findViewById(R.id.mode);
        content = findViewById(R.id.textView);
        answer = findViewById(R.id.textView2);
        submit = findViewById(R.id.id_submit);
        submit.setClickable(false);
        message_box.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                changed();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        key_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                changed();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        switchbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changed();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                message = String.valueOf(message_box.getText());
                key = String.valueOf(key_box.getText());
                mode = switchbutton.isChecked();
                key_box.onEditorAction(EditorInfo.IME_ACTION_DONE);
                message_box.onEditorAction(EditorInfo.IME_ACTION_DONE);
                if(!changed()) {
                    System.exit(0);
                }
                callCipher(message, key, mode,getIntent().getStringExtra("cipher"));
            }
        });


        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
        AdSize adSize = new AdSize(400, 50);

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void callCipher(String message, String i, boolean mode,String cipher) {
        Ciphers ciphers = new Ciphers();
        if(cipher.equals("Casear Cipher")) {
            if (mode)
            {
                changed();
                s ="\nDecrypted message = "+ciphers.CasearCipher(this,message,-Integer.parseInt(i));
                answer.setText(s);
            }
            else
            {
                changed();
                s ="\nEncrypted message = "+ciphers.CasearCipher(this,message,Integer.parseInt(i));
                answer.setText(s);
            }
        }
        else if(cipher.equals("Substitution Cipher")) {
            s="Message = "+ciphers.SubstitutionCipher(message,key.toLowerCase(),mode);
            answer.setText(s);
        }
    }

    private boolean changed() {
        message = String.valueOf(message_box.getText());
        key = String.valueOf(key_box.getText());
        mode = switchbutton.isChecked();
        if(message.isEmpty()){
            Toast.makeText(this, "Message can't be empty", Toast.LENGTH_SHORT).show();
            submit.setClickable(false);
            return false;
        }
        else if(key.isEmpty()){
            submit.setClickable(false);
            Toast.makeText(this, "Key can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            submit.setClickable(true);
            if (mode)
            {
                s = "Message="+message+"\nKey=-"+key+"\nMode=Decrypt";
                content.setText(s);
            }
            else
            {
                s="Message="+message+"\nKey="+key+"\nMode=Encrypt";
                content.setText(s);
            }
            return true;
        }
    }
}
