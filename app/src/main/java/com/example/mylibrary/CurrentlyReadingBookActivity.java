package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

/**
 * An activity to show all books that the user is currently reading
 * within the library. Each book will be represented by a CardView
 * that will contain the book's title, author and a short description.
 */
public class CurrentlyReadingBookActivity extends AppCompatActivity {

    private RecyclerView currentlyReadingBookRecView;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading_book);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currentlyReadingBookRecView = findViewById(R.id.currentlyReadingBookRecView);
        adapter = new BookRecViewAdapter(this, getResources().getString(R.string.currently_Reading_Books));
        currentlyReadingBookRecView.setAdapter(adapter);
        currentlyReadingBookRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance(this).getCurrentlyReadingBooks());
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