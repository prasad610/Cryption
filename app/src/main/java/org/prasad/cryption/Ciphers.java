package org.prasad.cryption;

import android.util.Log;

public class Ciphers
{
    private String cipher="";

    public String MorseCode(String plain_text)
    {
        plain_text = plain_text.toLowerCase();
//        for(char letter : plain_text.toCharArray())
//        {
//
//        }
        return cipher;
    }

    String CasearCipher(String plain_text, int shift, String mode)
    {
        int new_ascii = 0;
        plain_text = plain_text.toUpperCase();
        for(char letter : plain_text.toCharArray())
        {
            int ascii=(int)letter;
            if( ascii < 65 )
            {
                Log.e("Invalid character", "character = '"+String.valueOf(letter)+"'");
                return "Plain text contains invalid character.";
            }

            switch (mode){
                case "encrypt":
                    new_ascii = ascii + shift;
                    break;
                case "decrypt":
                    new_ascii = ascii - shift;
                    break;
                default:
                    Log.e("Switch case","Line 41 mode neither encrypt nor decrypt");
            }
            while ( new_ascii > 91 ){
                new_ascii = new_ascii % 90 + 65;
            }
            cipher = cipher + (char) new_ascii;
        }

        return cipher;
    }

    public String SubstitutionCipher(String plain_text, String key)
    {

        return cipher;
    }
}
