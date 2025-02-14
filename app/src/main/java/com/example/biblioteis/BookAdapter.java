package com.example.biblioteis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biblioteis.API.models.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter {
    List<Book> libros;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_book, parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Book book=libros.get(position);
        BookViewHolder bvh = (BookViewHolder) holder;

        bvh.getTvTitulo().setText(book.getTitle());
        bvh.getTvAutor().setText(book.getAuthor());
        bvh.getImgPortada().setImageResource(book.getBookPicture());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
