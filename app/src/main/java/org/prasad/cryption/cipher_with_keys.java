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

public class cipher_with_keys extends AppCompatActivity {
    private EditText message_box, key_box;
    private String message, key, s;
    private Switch switchbutton;
    private boolean mode;
    private Button submit;
    private TextView content, answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cipher_with_keys);
        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle("Substitution");
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
                if(changed()) {
                    callCipher(message, key, mode);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void callCipher(String message, String i, boolean mode) {
        Ciphers ciphers = new Ciphers();
//        if (mode)
//        {
//            changed();
//            s ="\nDecrypted message = "+ciphers.CasearCipher(this,message,-Integer.parseInt(i));
//            answer.setText(s);
//        }
//        else
//        {
//            changed();
//            s ="\nEncrypted message = "+ciphers.CasearCipher(this,message,Integer.parseInt(i));
//            answer.setText(s);
//        }
        s="Message = "+ciphers.SubstitutionCipher(message,key.toLowerCase(),mode);
        answer.setText(s);

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
            Toast.makeText(this, "Key can't be empty", Toast.LENGTH_SHORT).show();
            submit.setClickable(false);
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
