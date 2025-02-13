package com.example.biblioteis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.biblioteis.API.models.User;
import com.example.biblioteis.API.repository.BookRepository;
import com.example.biblioteis.API.repository.UserRepository;

import java.util.List;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {


    ImageView img;
    EditText etEmail, etPassw;
    TextView tvEmailPasswError;
    Button btnLogin, btnSignin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        img = findViewById(R.id.img);
        etEmail = findViewById(R.id.et_email);
        etPassw = findViewById(R.id.et_password);
        tvEmailPasswError = findViewById(R.id.tv_errorPassword);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin(v);
            }
        });
    }

    private void doLogin(View v) {

        //Obtner los usuarios
        UserRepository ur = new UserRepository();

        ur.getUsers(new BookRepository.ApiCallback<List<User>>() {
            @Override
            public void onSuccess(List<User> result) {
                for(User u: result){
                    if(Objects.equals(u.getEmail(), etEmail.getText().toString())){
                        tvEmailPasswError.setVisibility(View.INVISIBLE);

                        if(Objects.equals(u.getPasswordHash(), etPassw.getText().toString())) {
                            tvEmailPasswError.setVisibility(View.INVISIBLE);
                            System.out.println("Usuario logeado, pasando a la siguiente view");
                            Intent userProfile=new Intent(v.getContext(), UserMain.class);
                            startActivity(userProfile);

                        }
                        else {
                            tvEmailPasswError.setVisibility(View.VISIBLE);
                        }
                    }
                    else {
                        tvEmailPasswError.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(v.getContext(), "Error de conexión", Toast.LENGTH_LONG).show();
            }
        });
    }

}