package com.example.biblioteis.Activities.DetallesLibro;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biblioteis.API.models.Book;

public class DetallesLibroViewModel extends ViewModel {
    public MutableLiveData<Book> libroSelecionado= new MutableLiveData<>();

    public void setLibroSelecionado(Book libro) {
        libroSelecionado.setValue(libro);
    }
    public LiveData<Book> getLibroSeleccionado(){
        return libroSelecionado;
    }
}
