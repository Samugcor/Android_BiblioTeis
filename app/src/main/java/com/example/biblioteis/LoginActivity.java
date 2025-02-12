package com.example.biblioteis;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.biblioteis.API.models.User;
import com.example.biblioteis.API.repository.BookRepository;
import com.example.biblioteis.API.repository.UserRepository;

import java.util.List;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {


    ImageView img;
    EditText etEmail, etPassw;
    TextView tvEmailError, tvPasswError;
    Button btnLogin, btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        img = findViewById(R.id.img);
        etEmail = findViewById(R.id.et_email);
        etPassw = findViewById(R.id.et_password);
        tvEmailError = findViewById(R.id.tv_errorEmail);
        tvPasswError = findViewById(R.id.tv_errorPassword);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    private void doLogin() {

        //Obtner los usuarios
        UserRepository ur = new UserRepository();

        ur.getUsers(new BookRepository.ApiCallback<List<User>>() {
            @Override
            public void onSuccess(List<User> result) {
                for(User u: result){
                    if(Objects.equals(u.getEmail(), etEmail.getText().toString())){
                        tvPasswError.setVisibility(View.INVISIBLE);

                        if(Objects.equals(u.getPasswordHash(), etPassw.getText().toString())) {
                            tvPasswError.setVisibility(View.INVISIBLE);


                        }
                        else {
                            tvPasswError.setVisibility(View.VISIBLE);
                        }
                    }
                    else {
                        tvEmailError.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                //notifica con un toast
            }
        });
    }
}