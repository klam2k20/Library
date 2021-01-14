package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A class that utilizes the SharedPreferences interface
 * in order to create lists representing the user's
 * favorite books, wishlist books, currently reading books,
 * and already read books. Utilizing the mentioned interface
 * will allow the arraylists to be persistant.
 */
public class Utils {
    private static  final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ__BOOKS_KEY = "already_read_books";
    private static final String CURRENTLY_READING_BOOKS_KEY = "currently_reading_books";
    private static final String WISHLIST_BOOKS_KEY = "wishlist_books";
    private static final String FAVORITE_BOOKS_KEY = "favorite_books";

    private static Utils instance;
    private SharedPreferences sharedPreferences;

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> wishListBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> favoriteBooks;

    private Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);
        if (null == getAllBooks()) {
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getCurrentlyReadingBooks()) {
            editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getAlreadyReadBooks()) {
            editor.putString(ALREADY_READ__BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getWishListBooks()) {
            editor.putString(WISHLIST_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getFavoriteBooks()) {
            editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    /**
     * Creates a SharedPreferences object that contains all the books
     * within the library.
     */
    private void initData() {
        ArrayList<Book> books = new ArrayList<>();
        Book b1 = new Book(1, "1Q84", "Haruki Murakami", 1350, "https://jasonlefkowitz.net/wp-content/uploads/2015/06/1q84-660x1024.jpg",
                "A work of madden brilliance", "Long Description");
        Book b2 = new Book(2, "The Myth of Sisyphus", "Albert Camus", 250, "https://miro.medium.com/max/500/1*DDsOx6D3oe8ZxcA-OTfIDA.jpeg",
                "One of the most influential works of this century", "Long Description");
        books.add(b1);
        books.add(b2);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Converts Object to String
        Gson gson = new Gson();
        // PutString(Key, string)
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        // Blocks main thread until action done
        editor.commit();
    }

    /**
     * Creates a new Util object if there isn't one,
     * else returns the Util object
     * @param context
     * @return Utils
     */
    public static Utils getInstance(Context context) {
        if (null == instance) {
            instance = new Utils(context);
        }
        return instance;
    }

    /**
     * Returns the arraylist representing all books within the
     * library
     * @return ArrayList
     */
    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        // Converts String back into object using the key. Returns null if string key not found
        ArrayList<Book>  books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    /**
     * Returns the arraylist representing all books the user
     * has marked as read
     * @return ArrayList
     */
    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        // Converts String back into object using the key. Returns null if string key not found
        ArrayList<Book>  books = gson.fromJson(sharedPreferences.getString(ALREADY_READ__BOOKS_KEY, null), type);
        return books;
    }

    /**
     * Returns the arraylist representing all books the user
     * has marked as wants to read
     * @return ArrayList
     */
    public ArrayList<Book> getWishListBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        // Converts String back into object using the key. Returns null if string key not found
        ArrayList<Book>  books = gson.fromJson(sharedPreferences.getString(WISHLIST_BOOKS_KEY, null), type);
        return books;
    }

    /**
     * Returns the arraylist representing all books the user
     * has marked as currently reading
     * @return ArrayList
     */
    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        // Converts String back into object using the key. Returns null if string key not found
        ArrayList<Book>  books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS_KEY, null), type);
        return books;
    }

    /**
     * Returns the arraylist representing all books the user
     * has marked as favorite
     * @return ArrayList
     */
    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        // Converts String back into object using the key. Returns null if string key not found
        ArrayList<Book>  books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS_KEY, null), type);
        return books;
    }

    /**
     * Returns the book based on its unique id
     * @return Book
     */
    public Book getBookById(int bookId) {
        ArrayList<Book> books = getAllBooks();
        if (books != null) {
            for (Book b: books) {
                if (b.getId() == bookId) {
                    return b;
                }
            }
        }
        return null;
    }

    /**
     * Adds the book to an arraylist representing the books
     * the user has marked as currently reading. Converts the
     * list into a JSON to be added to SharePreferences.
     * @param book
     * @return boolean
     */
    public boolean addToCurrentlyRead(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS_KEY);
                editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the book to an arraylist representing the books
     * the user has marked as read. Converts the
     * list into a JSON to be added to SharePreferences.
     * @param book
     * @return boolean
     */
    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ__BOOKS_KEY);
                editor.putString(ALREADY_READ__BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the book to an arraylist representing the books
     * the user has marked as wanting to read. Converts the
     * list into a JSON to be added to SharePreferences.
     * @param book
     * @return boolean
     */
    public boolean addToWishList(Book book) {
        ArrayList<Book> books = getWishListBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WISHLIST_BOOKS_KEY);
                editor.putString(WISHLIST_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the book to an arraylist representing the books
     * the user has marked as favorite. Converts the
     * list into a JSON to be added to SharePreferences.
     * @param book
     * @return boolean
     */
    public boolean addToFavorite(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS_KEY);
                editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the book from an arraylist representing the books
     * the user has marked as currently reading. Converts the
     * list into a JSON to be added to SharePreferences.
     * @param book
     * @return boolean
     */
    public boolean removeFromCurrentlyRead(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (books != null) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS_KEY);
                        editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Removes the book from an arraylist representing the books
     * the user has marked as already read. Converts the
     * list into a JSON to be added to SharePreferences.
     * @param book
     * @return boolean
     */
    public boolean removeFromAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (books != null) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ__BOOKS_KEY);
                        editor.putString(ALREADY_READ__BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Removes the book from an arraylist representing the books
     * the user has marked as wanting to read. Converts the
     * list into a JSON to be added to SharePreferences.
     * @param book
     * @return boolean
     */
    public boolean removeFromWishList(Book book) {
        ArrayList<Book> books = getWishListBooks();
        if (books != null) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WISHLIST_BOOKS_KEY);
                        editor.putString(WISHLIST_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Removes the book from an arraylist representing the books
     * the user has marked as favorites. Converts the
     * list into a JSON to be added to SharePreferences.
     * @param book
     * @return boolean
     */
    public boolean removeFromFavorite(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (books != null) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS_KEY);
                        editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
