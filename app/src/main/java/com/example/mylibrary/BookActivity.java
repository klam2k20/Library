package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * An activity to showcase the following details of a book:
 * title, author, number of pages, and long description.
 */
public class BookActivity extends AppCompatActivity {
    private ImageView imgBookActLogo;
    private Button btnAddToCurrentlyReading, btnAddToWishList, btnAddToAlreadyRead, btnAddToFavorites;
    private TextView txtBookActTitle, txtBookActAuthor, txtBookActPages, txtBookActDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

        Intent intent = getIntent();
        if (intent != null) {
            int bookId = intent.getIntExtra(getResources().getString(R.string.BOOK_ID_KEY), -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if (incomingBook != null) {
                    setData(incomingBook);
                    handleAlreadyReadBook(incomingBook);
                    handleCurrentlyReadingBook(incomingBook);
                    handleWishListBook(incomingBook);
                    handleFavoriteBook(incomingBook);

                }
            }
        }

    }

    /**
     * Checks whether there exists a list containing the user's
     * favorite books. If there is not such a list with be created
     * and the selected book will be added to said list. If there
     * is already a list the selected book will be added to said list
     * if it hasn't already.
     * @param incomingBook
     */
    private void handleFavoriteBook(Book incomingBook) {
        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavoriteBooks();
        boolean existInFavoriteBooks = false;
        for (Book b: favoriteBooks) {
            if (b.getId() == incomingBook.getId()) {
                existInFavoriteBooks = true;
                break;
            }
        }
        if (existInFavoriteBooks) {
            btnAddToFavorites.setEnabled(false);
        }
        else {
            btnAddToFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToFavorite(incomingBook)) {
                        Toast.makeText(BookActivity.this, incomingBook.getTitle() + " Added!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavoriteBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Error! Try  Again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Checks whether there exists a list containing the books
     * the user wishes to read. If there is not such a list
     * with be created and the selected book will be added to
     * said list. If there is already a list the selected book
     * will be added to said list if it hasn't already.
     * @param incomingBook
     */
    private void handleWishListBook(Book incomingBook) {
        ArrayList<Book> wishListBooks = Utils.getInstance(this).getWishListBooks();
        boolean existInWishListBooks = false;
        for (Book b: wishListBooks) {
            if (b.getId() == incomingBook.getId()) {
                existInWishListBooks = true;
                break;
            }
        }
        if (existInWishListBooks) {
            btnAddToWishList.setEnabled(false);
        }
        else {
            btnAddToWishList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToWishList(incomingBook)) {
                        Toast.makeText(BookActivity.this, incomingBook.getTitle() + " Added!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WishListBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Error! Try  Again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Checks whether there exists a list containing the books
     * the user is currently reading. If there is not such a
     * list with be created and the selected book will be added
     * to said list. If there is already a list the selected book
     * will be added to said list if it hasn't already.
     * @param incomingBook
     */
    private void handleCurrentlyReadingBook(Book incomingBook) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();
        boolean existInCurrentlyReadingBooks = false;
        for (Book b: currentlyReadingBooks) {
            if (b.getId() == incomingBook.getId()) {
                existInCurrentlyReadingBooks = true;
                break;
            }
        }
        if (existInCurrentlyReadingBooks) {
            btnAddToCurrentlyReading.setEnabled(false);
        }
        else {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToCurrentlyRead(incomingBook)) {
                        Toast.makeText(BookActivity.this, incomingBook.getTitle() + " Added!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Error! Try  Again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Checks whether there exists a list containing the books
     * the user has already read. If there is not such a
     * list with be created and the selected book will be added
     * to said list. If there is already a list the selected book
     * will be added to said list if it hasn't already.
     * @param incomingBook
     */
    private void handleAlreadyReadBook(Book incomingBook) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();
        boolean existInAlreadyReadBooks = false;
        for (Book b: alreadyReadBooks) {
            if (b.getId() == incomingBook.getId()) {
                existInAlreadyReadBooks = true;
                break;
            }
        }
        if (existInAlreadyReadBooks) {
            btnAddToAlreadyRead.setEnabled(false);
        }
        else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToAlreadyRead(incomingBook)) {
                        Toast.makeText(BookActivity.this, incomingBook.getTitle() + " Added!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Error! Try  Again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Transfers book's fields to UI elements to be displayed
     * to the user
     * @param b
     */
    private void setData(Book b) {
        txtBookActTitle.setText(b.getTitle());
        txtBookActAuthor.setText(b.getAuthor());
        txtBookActPages.setText(String.valueOf(b.getPages()));
        txtBookActDesc.setText(b.getLongDesc());
        Glide.with(this)
                .asBitmap()
                .load(b.getImgUrl())
                .into(imgBookActLogo);
    }

    /**
     * Initializes all UI elements:
     * - ImageView: containing an image of the book's cover
     * - Buttons: buttons to add current book to a specific list
     * - TextViews: title, author, number of pages, and description labels
     *  as well as, textViews to contain the actually title, author,
     *  number of pages and book summary.
     */
    private void initViews() {
        imgBookActLogo = findViewById(R.id.imgBookActLogo);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToWishList = findViewById(R.id.btnAddtoWishList);
        btnAddToFavorites = findViewById(R.id.btnAddToFavorites);
        txtBookActTitle = findViewById(R.id.txtBookActTitle);
        txtBookActAuthor = findViewById(R.id.txtBookActAuthor);
        txtBookActPages = findViewById(R.id.txtBookActPages);
        txtBookActDesc = findViewById(R.id.txtBookActDesc);
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