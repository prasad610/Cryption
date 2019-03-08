package org.prasad.cryption;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView typeOfEncryption;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }
        typeOfEncryption = findViewById(R.id.type_of_encryption);
        typeOfEncryption.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
//                        Caesar Shift
                        startActivity(new Intent(MainActivity.this,cipher_with_keys.class).putExtra("cipher","Casear Cipher"));
                        break;
                    case 1:
//                        ROT-13
                        startActivity(new Intent(MainActivity.this,cipher_with_keys.class).putExtra("cipher","Substitution Cipher"));
                        break;
                }
            }
        });
    }

}
