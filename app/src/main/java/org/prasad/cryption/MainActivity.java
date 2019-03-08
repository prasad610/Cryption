package org.prasad.cryption;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView typeOfEncryption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        typeOfEncryption = findViewById(R.id.type_of_encryption);
        typeOfEncryption.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
//                        Caesar Shift
                        startActivity(new Intent(MainActivity.this,cipher_with_keys.class));
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "Item  at position = "+String.valueOf(position)+" is Clicked!!!", Toast.LENGTH_SHORT).show();
//                        ROT-13
                        break;
                }
            }
        });
    }

}
