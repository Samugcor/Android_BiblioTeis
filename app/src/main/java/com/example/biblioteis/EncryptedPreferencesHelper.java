package com.example.biblioteis;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.example.biblioteis.API.models.BookLending;
import com.example.biblioteis.API.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/*Esta clase nos sirve para interactuar con las Encrypted Shared Preferences
*
* Los datos que queremos guardar del usuario serían:
*  - id
*  - name
*  - email
* */
public class EncryptedPreferencesHelper {
    /*Este metodo recupera el archivo de las encrypted shared preferences y nos devuelve
    * el objeto Shared Preferences correspondiente. De esta forma podemos usarlo para otros métodos.*/
    private static SharedPreferences getEncryptedSharedPreferences(Context context) {
        try {
            //Primero creamos la MasterKey
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            //El méto-do create de EncryptedSharedPreferences abre una instancia de sharedPreferences, no crea una nueva cada vez.
            SharedPreferences sp= EncryptedSharedPreferences.create(
                    context,
                    "secure_prefs",
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            return sp;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//🔽 GUARDAR Y LEER ALL DATA
    public static void saveAllData(Context context, User user) {
        SharedPreferences prefs = getEncryptedSharedPreferences(context);
        if (prefs != null) {
            prefs.edit().putInt("user_id", user.getId()).apply();
            prefs.edit().putString("user_name", user.getName()).apply();
            prefs.edit().putString("user_email", user.getEmail()).apply();
        }
    }
    public static User getUserData(Context context) {
        SharedPreferences prefs = getEncryptedSharedPreferences(context);
        User currentUser=new User();
        currentUser.setId(prefs.getInt("user_id", -1));
        currentUser.setName(prefs.getString("user_name",""));
        currentUser.setEmail(prefs.getString("user_email",""));
        return currentUser;
    }

//🔽 GUARDAR Y LEER ID
    public static void saveUserId(Context context, int userId) {
        SharedPreferences prefs = getEncryptedSharedPreferences(context);
        if (prefs != null) {
            prefs.edit().putInt("user_id", userId).apply();
        }
    }

    public static int getUserId(Context context) {
        SharedPreferences prefs = getEncryptedSharedPreferences(context);
        return prefs != null ? prefs.getInt("user_id", -1) : -1;
    }

//🔽 GUARDAR Y LEER BOOKLENDINGS
    /*Los datos de un prestamo consisten de:
    * - id del prestamo     - fecha de la devolución esperada
    * - id del libro        - libro
    * - id del usuario      - usuario
    * - fecha del prestamo
    *
    * Esta anidación de datos no es compatible con las sharedPreferences, que solo aguantan datos simples,
    * por lo que lo mejor es transformar estos datos a una String JSON. Para manejar de forma más sencilla
    * la conversión de datos vamos a añadir la dependencia */

    /*public static void saveLendedBooks(Context context, List<BookLending> books) {
        SharedPreferences prefs = getEncryptedSharedPreferences(context);
        if (prefs != null) {
            Gson gson = new Gson();
            String json = gson.toJson(books);
            prefs.edit().putString("lended_books", json).apply();
        }
    }

    public static List<BookLending> getLendedBooks(Context context) {
        SharedPreferences prefs = getEncryptedSharedPreferences(context);
        if (prefs != null) {
            Gson gson = new Gson();
            String json = prefs.getString("lended_books", null);

            if (json != null) {
                Type type = new TypeToken<List<BookLending>>() {}.getType();
                return gson.fromJson(json, type);
                //🟡 Explicación Type y TypeToken
                //In Java, generic types (like List<LendedBook>) lose their type information at runtime due to type erasure.
                //TypeToken<T> is a class provided by Gson that helps retain generic type information.
                //new TypeToken<List<LendedBook>>() {}.getType(); creates a Type object representing List<LendedBook>.

            }
        }
        return null;
    }*/

}
