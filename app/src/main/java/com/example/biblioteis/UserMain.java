package com.example.biblioteis;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biblioteis.API.models.Book;
import com.example.biblioteis.API.repository.BookRepository;
import com.example.biblioteis.viewModels.UserMainViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserMain extends AppCompatActivity {

    RecyclerView rvLibrosDisponibles;
    //MutableLiveData<List<Book>> listaLibros = new MutableLiveData<List<Book>>();
    UserMainViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);


        rvLibrosDisponibles =findViewById(R.id.rv_libros_disponibles);
        rvLibrosDisponibles.setLayoutManager(new LinearLayoutManager(this));

        vm  = new ViewModelProvider(this).get(UserMainViewModel.class);


        vm.listaLibros.observe(this,  bs -> {
            if(bs != null)
                rvLibrosDisponibles.setAdapter(new BookAdapter(bs));
        });
        vm.loadBooks();

        rvLibrosDisponibles.setAdapter(new BookAdapter(new ArrayList<>()));
    }
}