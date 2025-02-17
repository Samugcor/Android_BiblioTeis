package com.example.biblioteis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biblioteis.API.models.Book;
import com.example.biblioteis.API.repository.BookRepository;
import com.example.biblioteis.API.repository.ImageRepository;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;

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

        ImageRepository ir = new ImageRepository();
        ir.getImage(book.getBookPicture(), new BookRepository.ApiCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                if (result != null)
                    bvh.getImgPortada().setImageBitmap(BitmapFactory.decodeStream(result.byteStream()));
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        //
    }

    @Override
    public int getItemCount() {
        return libros.size();
    }

    public BookAdapter(List<Book> libros){
        this.libros=libros;
    }
}
