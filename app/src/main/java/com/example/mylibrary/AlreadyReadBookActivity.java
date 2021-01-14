package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

/**
 * An activity to show all books that the user has marked as read
 * within the library. Each book will be represented by a CardView
 * that will contain the book's title, author and a short description.
 */
public class AlreadyReadBookActivity extends AppCompatActivity {

    private RecyclerView alreadyReadBookRecView;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read_book);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        alreadyReadBookRecView = findViewById(R.id.alreadyReadBookRecView);
        adapter = new BookRecViewAdapter(this, getResources().getString(R.string.already_Read_Books));
        alreadyReadBookRecView.setAdapter(adapter);
        alreadyReadBookRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance(this).getAlreadyReadBooks());
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