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
        Book b1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 218, "https://d28hgpri8am2if.cloudfront.net/book_images/onix/cvr9781982146702/the-great-gatsby-9781982146702_hr.jpg",
                "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, the novel depicts narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
                "Written by F. Scott Fitzgerald and published in 1925, The Great Gatsby is set during the Roaring Twenties, in 1922 and tells the story of one man's pursuit of the American Dream. The narrator, Nick Carraway, is an upper class American man who moves from the West to New York to try his luck as a bond trader. He meets an eccentric, exceptionally wealthy neighbor named Jay Gatsby, and becomes embroiled in Gatsby's plan to rekindle a lost love with a woman named Daisy Buchanan, who happens to be Nick's cousin.\n" +
                "\n" +
                "The protagonist, Jay Gatsby, is involved in illegal activities, including bootlegging, or selling liquor during prohibition, when liquor sales are illegal in the United States. He throws lavish parties and eventually meets and begins an affair with his beloved Daisy. Daisy's husband, Tom, is carrying on an affair with a garage owner's wife; a woman named Myrtle Wilson. Driving home from New York, Daisy strikes and kills Myrtle while driving Gatsby's car. She is unaware that she has killed her husband's mistress and leaves the scene of the crime. Myrtle's husband is despondent and tries to find his wife's killer. Daisy's husband, Tom, directs him to Gatsby's house, where he shoots and kills Gatsby, and then himself, thus resolving Daisy's affair.");
        Book b2 = new Book("Jane Eye", " Charlotte Brontë", 480, "https://images-na.ssl-images-amazon.com/images/I/41hQyLVXYPL._SX320_BO1,204,203,200_.jpg",
                "Jane Eyre is a novel written by Charlotte Brontë in 1847. The novel follows the story of Jane, a seemingly plain and simple girl as she battles through life's struggles. ",
                "Jane Eyre is a novel written by Charlotte Brontë in 1847. The novel follows the story of Jane, a seemingly plain and simple girl as she battles through life's struggles. Jane has many obstacles in her life - her cruel and abusive Aunt Reed, the grim conditions at Lowood school, her love for Rochester and Rochester's marriage to Bertha. However, Jane overcomes these obstacles through her determination, sharp wit and courage. The novel ends with Jane married to Rochester with children of their own.\n" +
                        "\n" +
                        "There are elements of Jane Eyre that echo Charlotte Brontë's own life. She and her sisters went to a school run by a headmaster as severe as Mr Brocklehurst. Two of Charlotte's sisters died there from tuberculosis (just like Jane's only friend, Helen Burns). Charlotte Brontë was also a governess for some years before turning to writing.");
        Book b3 = new Book("To Kill a MockingBird", "Harper Lee", 281, "https://upload.wikimedia.org/wikipedia/commons/4/4f/To_Kill_a_Mockingbird_%28first_edition_cover%29.jpg",
                "When Tom Robinson, one of the town's Black residents, is falsely accused of raping Mayella Ewell, a white woman, Atticus agrees to defend him despite threats from the community. ",
                "When Tom Robinson, one of the town's Black residents, is falsely accused of raping Mayella Ewell, a white woman, Atticus agrees to defend him despite threats from the community. At one point he faces a mob intent on lynching his client but refuses to abandon him. Scout unwittingly diffuses the situation.");
        books.add(b1);
        books.add(b2);
        books.add(b3);


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
