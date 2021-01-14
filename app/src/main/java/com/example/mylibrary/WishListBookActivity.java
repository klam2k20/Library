package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class WishListBookActivity extends AppCompatActivity {

    private RecyclerView wishListBookRecView;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list_book);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        wishListBookRecView = findViewById(R.id.wishListBookRecView);
        adapter = new BookRecViewAdapter(this, getResources().getString(R.string.wishList_Books));
        wishListBookRecView.setAdapter(adapter);
        wishListBookRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance(this).getWishListBooks());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}