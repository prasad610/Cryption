package org.prasad.cryption;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class MorseCode extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse_code);

//        try {
//            String s = new versionChecker().execute().get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if (!updateApp("https://play.google.com/store/apps/details","com.rotibank.mumbai.donate")) {
            main();
        }
    }


    private void main() {

    }

    /* Function check if the app version in build config is same as the one in firebase database and */

    @SuppressWarnings("SameParameterValue")
    private boolean updateApp(final String url, final String packagename) {
        DocumentReference docRef = db.collection("Application").document("Details");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        if (!Objects.equals(Objects.requireNonNull(document.getData()).get("Version"), BuildConfig.VERSION_NAME)) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            Uri.Builder uriBuilder = Uri.parse(url)
                                    .buildUpon()
                                    .appendQueryParameter("id", packagename)
                                    .appendQueryParameter("launch", "false");
                            Log.d("Uri","Parsing url Completed");
                            intent.setData(uriBuilder.build());
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
//class versionChecker extends AsyncTask<String, String, String> {
//    String newVersion;
//    private String latestVersion;
//
//    @Override
//    protected String doInBackground(String... params) {
//
//        try {
//
//            Document doc = Jsoup.connect("https://play.google.com/store/apps/details?id=com.rotibank.mumbai.donate&hl=en").get();
//            latestVersion = doc.getElementsByClass("htlgb").get(6).text();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return latestVersion;
//    }
//}