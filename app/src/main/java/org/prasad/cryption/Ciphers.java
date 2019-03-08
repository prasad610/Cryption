package org.prasad.cryption;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

class Ciphers
{
    private StringBuilder stringBuilder;

//    public String MorseCode(String plain_text)
//    {
//        plain_text = plain_text.toLowerCase();
//        for(char letter : plain_text.toCharArray())
//        {
//
//        }
//        return stringBuilder.toString();
//    }

    String CasearCipher(Context context, String plain_text, int shift)
    {

        int new_ascii;
        plain_text = plain_text.toUpperCase();
        for(char letter : plain_text.toCharArray())
        {
            stringBuilder = new StringBuilder();
            int ascii=(int)letter;
            //noinspection ConstantConditions
            if( ascii!=20 && (ascii<48 && ascii>90))
            {
                Log.e("Invalid character", "character = '"+String.valueOf(letter)+"'");
                Toast.makeText(context, "Plain text contains invalid character.", Toast.LENGTH_SHORT).show();
            }

            new_ascii = ascii + shift;
            while ( new_ascii > 91 ){
                new_ascii = new_ascii % 90 + 65;
            }
            stringBuilder.append((char) new_ascii);
        }

        return stringBuilder.toString();
    }

    String SubstitutionCipher(String plain_text, String key, boolean mode)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        stringBuilder = new StringBuilder();
        for(char letter : plain_text.toLowerCase().toCharArray())
        {
            if(mode) {
                stringBuilder.append(alphabet.substring(key.indexOf(letter), key.indexOf(letter)+1));
            }
            else {
                stringBuilder.append(key.substring(alphabet.indexOf(letter),alphabet.indexOf(letter)+1));
            }
        }

        return stringBuilder.toString();
    }
}
