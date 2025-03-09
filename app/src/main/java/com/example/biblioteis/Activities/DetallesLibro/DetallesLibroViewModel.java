package com.example.biblioteis.Activities.DetallesLibro;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biblioteis.API.models.Book;
import com.example.biblioteis.API.models.BookLending;
import com.example.biblioteis.API.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetallesLibroViewModel extends ViewModel {
    public MutableLiveData<Book> libroSelecionado= new MutableLiveData<>();
    public MutableLiveData<List<BookLending>> prestamosLibroSeleccionado=new MutableLiveData<>();
    public MutableLiveData<List<Integer>> usuariosLibroSeleccionado=new MutableLiveData<>();

    public void setLibroSelecionado(Book libro) {
        libroSelecionado.setValue(libro);

        if(libro.getBookLendings() != null){
            List<BookLending> prestamos = libro.getBookLendings();
            prestamosLibroSeleccionado.setValue(prestamos);

            //🟡¿Como funciona el stream?
            /* - prestamos.stream(): Converts the List<BookLending> into a stream (like a pipeline) that allows processing of elements one by one.
             * - .map(BookLending::getUserId): Transforms each BookLending object into its userId.
             * - .collect(Collectors.toList()): Converts the processed elements (user IDs) back into a List<Integer>.
             */
            List<Integer> usuarios = prestamos.stream()
                    .map(BookLending::getUserId)
                    .collect(Collectors.toList());

            usuariosLibroSeleccionado.setValue(usuarios);
        }
    }

    public LiveData<Book> getLibroSeleccionado(){
        return libroSelecionado;
    }

    public LiveData<List<BookLending>> getPrestamosLibroSeleccionado() {
        return prestamosLibroSeleccionado;
    }

    public LiveData<List<Integer>> getUsuariosLibroSeleccionado() {
        return usuariosLibroSeleccionado;
    }

    public boolean userHasBook (int userId){
        if (usuariosLibroSeleccionado.getValue() == null)
            return false;
        return usuariosLibroSeleccionado.getValue().stream().anyMatch(id-> id==userId);
    }
}
