package com.example.biblioteis;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.biblioteis.API.models.User;
import com.example.biblioteis.API.repository.BookRepository;
import com.example.biblioteis.API.repository.UserRepository;

import java.util.List;

public class LoginActivity extends AppCompatActivity {


    ImageView iv;
    TextView tvError, tvEmail, tvPassw;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        iv = findViewById(R.id.iv1);
        tvError = findViewById(R.id.textViewError);
        tvEmail = findViewById(R.id.textView2);
        tvPassw = findViewById(R.id.textView3);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
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
                    if(u.getEmail() == tvEmail.getText()){

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