package com.example.biblioteis.Activities.Catalogo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biblioteis.Activities.PerfilUsuario.UserProfileActivity;
import com.example.biblioteis.BookAdapter;
import com.example.biblioteis.R;

import java.util.ArrayList;

public class CatalogoActivity extends AppCompatActivity {
    public static String USERID="userId";
    RecyclerView rvLibrosDisponibles;
    Toolbar tb;
    CatalogoViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        tb=findViewById(R.id.tb_catalogo);
        //Remplazamos la ActionBar que viene por defecto con nuestra Toolbar
        setSupportActionBar(tb);

        addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if(itemId == R.id.item_misLibros)
                    Toast.makeText(CatalogoActivity.this, "Mis libros seleccionado", Toast.LENGTH_SHORT).show();
                if(itemId == R.id.item_datos){
                    Intent intenPerfil= new Intent(CatalogoActivity.this, UserProfileActivity.class);
                    startActivity(intenPerfil);
                }

                if(itemId == R.id.item_cerrarSesion)
                    Toast.makeText(CatalogoActivity.this, "Cerrar sesión seleccionado", Toast.LENGTH_SHORT).show();
                if (itemId == android.R.id.home) {  // This is the Up button
                    finish(); // Close the activity and go back to the previous one

                }
                return false;
            }
        });

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