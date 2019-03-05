package org.prasad.cryption;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class MorseCode extends AppCompatActivity {
    TextView tv;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse_code);
        tv = findViewById(R.id.textvieew);
        if (!updateApp("https://play.google.com/store/apps/details","com.rotibank.mumbai.donate")) {
            main();
        }

    }

    private void main() {
        // Access a Cloud Firestore instance from your Activity
        Ciphers cypher = new Ciphers();
        Toast.makeText(this, cypher.CasearCipher("QSBTBE",1,"decrypt"), Toast.LENGTH_LONG).show();

    }

    /* Function check if the app version in build config is same as the one in firebase database and */
    private boolean updateApp(final String url, final String packagename) {
        DocumentReference docRef = db.collection("Application").document("Details");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        Toast.makeText(MorseCode.this, ""+document.getData().get("Version").equals(BuildConfig.VERSION_NAME), Toast.LENGTH_SHORT).show();
                        if (!Objects.requireNonNull(document.getData()).get("Version").equals(BuildConfig.VERSION_NAME)) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            Uri.Builder uriBuilder = Uri.parse(url)
                                    .buildUpon()
                                    .appendQueryParameter("id", packagename)
                                    .appendQueryParameter("launch", "false");
                            Log.d("Uri","Parsing url Completed");
                            intent.setData(uriBuilder.build());
                            intent.setPackage("com.android.vending");
                            startActivity(intent);
                        } else {
                            main();
                        }
                    }
                } else {
                    Log.d("TAG", "No such document");
                }

            }
        });
        return false;
    }
}
