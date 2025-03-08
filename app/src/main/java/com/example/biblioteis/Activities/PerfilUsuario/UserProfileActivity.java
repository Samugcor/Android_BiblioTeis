package com.example.biblioteis.Activities.PerfilUsuario;

import android.os.Bundle;
import android.widget.EditText;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.biblioteis.API.models.User;
import com.example.biblioteis.EncryptedPreferencesHelper;
import com.example.biblioteis.R;

public class UserProfileActivity extends AppCompatActivity {

    Toolbar tb;
    EditText etNombre,etEmail,etRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tb= findViewById(R.id.tb_userProfile);
        setSupportActionBar(tb);


        if (getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNombre = findViewById(R.id.te_up_nombre);
        etEmail = findViewById(R.id.te_up_email);
        etRegistro = findViewById(R.id.te_up_registro);

        User currentUser=EncryptedPreferencesHelper.getUserData(getApplicationContext());
        etNombre.setText(currentUser.getName());
        etEmail.setText(currentUser.getEmail());
        etRegistro.setText(currentUser.getDateJoined());
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {  // Handle the Up button
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}