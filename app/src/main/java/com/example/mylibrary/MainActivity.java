package com.example.mylibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnAllBooks, btnCurrentlyReading, btnAlreadyRead, btnWishList, btnFavorites, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Utils.getInstance(this);
    }

    private void initViews() {
        btnAllBooks = findViewById(R.id.btnAllBooks);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnWishList = findViewById(R.id.btnWishList);
        btnFavorites = findViewById(R.id.btnFavorites);
        btnAbout = findViewById(R.id.btnAbout);
        btnAllBooks.setOnClickListener(this);
        btnCurrentlyReading.setOnClickListener(this);
        btnAlreadyRead.setOnClickListener(this);
        btnWishList.setOnClickListener(this);
        btnFavorites.setOnClickListener(this);
        btnAbout.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnAllBooks:
                intent = new Intent(MainActivity.this, AllBooksActivity.class);
                startActivity(intent);
                break;
            case R.id.btnAlreadyRead:
                intent = new Intent(MainActivity.this, AlreadyReadBookActivity.class);
                startActivity(intent);
                break;
            case R.id.btnCurrentlyReading:
                intent = new Intent(MainActivity.this, CurrentlyReadingBookActivity.class);
                startActivity(intent);
                break;
            case R.id.btnWishList:
                intent = new Intent(MainActivity.this, WishListBookActivity.class);
                startActivity(intent);
                break;
            case R.id.btnFavorites:
                intent = new Intent(MainActivity.this, FavoriteBookActivity.class);
                startActivity(intent);
                break;
            case R.id.btnAbout:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Developed and Designed by Kenny. Much Love to Meisam for" +
                        " the awesome Android Development Tutorial. Check him out at his website" +
                        " meiCode.org");
                builder.setNegativeButton("Dismiss", new  DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
                        intent.putExtra(getResources().getString(R.string.URL_ID), "https://www.meiCode.org");
                        startActivity(intent);
                    }
                });
                builder.create().show();
                break;
            default:
                break;
        }

    }
}