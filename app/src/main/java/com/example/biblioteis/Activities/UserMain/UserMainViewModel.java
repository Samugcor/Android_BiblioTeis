package com.example.biblioteis.Activities.UserMain;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biblioteis.API.models.Book;
import com.example.biblioteis.API.repository.BookRepository;

import java.util.List;

public class UserMainViewModel extends ViewModel {

    private BookRepository bookRepository = new BookRepository();

    public MutableLiveData<List<Book>> listaLibros = new MutableLiveData<List<Book>>();

    public void loadBooks(){
        this.bookRepository.getBooks(new BookRepository.ApiCallback<List<Book>>() {
            @Override
            public void onSuccess(List<Book> result) {
                listaLibros.setValue(result);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


}
