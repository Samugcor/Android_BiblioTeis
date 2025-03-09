package com.example.biblioteis.Activities.DetallesLibro;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.biblioteis.API.models.Book;
import com.example.biblioteis.API.repository.BookRepository;
import com.example.biblioteis.API.repository.ImageRepository;
import com.example.biblioteis.BookAdapter;
import com.example.biblioteis.EncryptedPreferencesHelper;
import com.example.biblioteis.R;

import okhttp3.ResponseBody;

public class DetallesLibroActivity extends AppCompatActivity {
    //Variables Vistas
    Button btnVolver,btnPrestar,btnDevolver;
    ImageView imgPortada;
    TextView tvTitulo,tvPublicacion,tvAutor,tvSipnopsis,tvIsbn;

    //Variables de servicio
    BookRepository br;
    DetallesLibroViewModel detallesViewModel;

    //Otras variables
    int bookid;
    static String LOREMIPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed maximus, elit vitae pharetra tempus, nunc purus mollis nibh, vitae mattis urna metus a purus. Pellentesque tincidunt dui eget eleifend volutpat. Proin sapien eros, placerat sed sapien id, luctus commodo leo. Sed dignissim purus odio, ac accumsan nisl tincidunt sit amet.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_libro);

        //Asignamos las views
        btnVolver=findViewById(R.id.btn_detalles_volver);
        btnPrestar=findViewById(R.id.btn_detalles_prestar);
        btnDevolver=findViewById(R.id.btn_detalles_devolver);
        imgPortada=findViewById(R.id.img_detalles_portada);
        tvTitulo=findViewById(R.id.tv_detalles_titulo);
        tvPublicacion=findViewById(R.id.tv_detalles_publicacion);
        tvAutor=findViewById(R.id.tv_detalles_autor);
        tvSipnopsis=findViewById(R.id.tv_detalles_sipnopsis);
        tvIsbn=findViewById(R.id.tv_detalles_isbn);

        //asignamos las capas de servicio
        br = new BookRepository();
        detallesViewModel = new ViewModelProvider(this).get(DetallesLibroViewModel.class);

        //Observamos si hay cambios en el libro del viewModel
        //🟡¿Por qué observamos en el on create?¿Es el lugar más adecuado?
        /* Sí, y existen varios motivos para ello, algunos de los más importantes:
        * - LiveData is lifecycle-aware, meaning it automatically stops observing when the Activity is destroyed (e.g., during configuration changes).
        * - onCreate() is the ideal place because it ensures that we start observing as soon as the Activity is created, and Android automatically cleans up the observer when the Activity is destroyed.
        * - If you observe LiveData outside of onCreate(), say in a global/static context, the observer could persist even after the Activity is destroyed, causing memory leaks.
        * - Si se destrulle la actividad al rotar el dispositivo la creación de una nueva actividad tambíen observará los cambios
        * */
        detallesViewModel.libroSelecionado.observe(this,  libro -> {
            if(libro != null){
                //Si hay cambios y el libro no es nullo coloca los datos
                tvTitulo.setText(libro.getTitle());
                tvPublicacion.setText(libro.getPublishedDate());
                tvAutor.setText(libro.getAuthor());
                tvSipnopsis.setText(LOREMIPSUM);
                tvIsbn.setText(libro.getIsbn());

                //coloca la imagen
                ImageRepository ir = new ImageRepository();
                ir.getImage(libro.getBookPicture(), new BookRepository.ApiCallback<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody result) {
                        if (result != null)
                            imgPortada.setImageBitmap(BitmapFactory.decodeStream(result.byteStream()));
                    }
                    @Override
                    public void onFailure(Throwable t) {
                        imgPortada.setImageResource(R.drawable.image_not_available);
                    }
                });

                //Activamos y desactivamos los botones
                //Si no está disponible
                if (!libro.isAvailable()) {
                    btnPrestar.setEnabled(false);
                    btnPrestar.setBackgroundColor(ContextCompat.getColor(this, R.color.moradoAndroidDisabled));
                    //Si no tenemos el libro
                    if(!detallesViewModel.userHasBook(EncryptedPreferencesHelper.getUserId(getApplicationContext()))){
                        btnDevolver.setEnabled(false);
                        btnDevolver.setBackgroundColor(ContextCompat.getColor(this, R.color.moradoAndroidDisabled));
                    }
                    else {
                        btnDevolver.setEnabled(true);
                        btnDevolver.setBackgroundColor(ContextCompat.getColor(this, R.color.moradoAndroid));
                    }
                }else {
                    btnPrestar.setEnabled(true);
                    btnPrestar.setBackgroundColor(ContextCompat.getColor(this, R.color.moradoAndroid));
                }
            }

        });

        //Recuperamos los datos pasados por el intent
        Intent i =getIntent();
        bookid = i.getIntExtra(BookAdapter.BOOK_ID,-1);

        //Usamos el book repository para conseguir los datos del libro con el id que hemos conseguido del intent
        if (bookid != -1){
            br.getBookById(bookid, new BookRepository.ApiCallback<Book>() {
                @Override
                public void onSuccess(Book result) {
                    //Y pasarselos al view model
                    detallesViewModel.setLibroSelecionado(result);
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(DetallesLibroActivity.this, "No se han podido recuperar los datos del libro", Toast.LENGTH_SHORT).show();

                }
            });
        }

        //Escuchamos por interacción con el botón de volver
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnPrestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetallesLibroActivity.this, "Has pulsado prestar", Toast.LENGTH_SHORT).show();

            }
        });



    }
}