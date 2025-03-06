package com.example.biblioteis.Activities.Catalogo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biblioteis.BookAdapter;
import com.example.biblioteis.R;

import java.util.ArrayList;

public class CatalogoActivity extends AppCompatActivity {

    RecyclerView rvLibrosDisponibles;
    //MutableLiveData<List<Book>> listaLibros = new MutableLiveData<List<Book>>();
    CatalogoViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);


        rvLibrosDisponibles =findViewById(R.id.rv_libros_disponibles);
        rvLibrosDisponibles.setLayoutManager(new LinearLayoutManager(this));

        vm  = new ViewModelProvider(this).get(CatalogoViewModel.class);


        vm.listaLibros.observe(this,  bs -> {
            if(bs != null)
                rvLibrosDisponibles.setAdapter(new BookAdapter(bs));
        });
        vm.loadBooks();

        rvLibrosDisponibles.setAdapter(new BookAdapter(new ArrayList<>()));
    }
}