package com.example.mylibrary;

/**
 * A model representing a book. A book consists of an unique id,
 * a title, author, number of pages, a short and long description,
 * and a cover image. It also consists of a boolean representing
 * whether to show the long description.
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private int pages;
    private String imgUrl;
    private String shortDesc;
    private String longDesc;
    private boolean isExpanded;

    public Book(int id, String title, String author, int pages, String imgUrl, String shortDesc, String longDesc) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.imgUrl = imgUrl;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.isExpanded = false;
    }

    /**
     * A getter for isExpanded field
     * @return boolean return
     */
    public boolean isExpanded() {
        return isExpanded;
    }

    /**
     * A setter for isExpanded field
     * @param expanded
     */
    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    /**
     * A getter for id field
     * @return boolean
     */
    public int getId() {
        return id;
    }

    /**
     * A setter for id field
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * A getter for title field
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * A setter for title field
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * A getter for author field
     * @return String
     */
    public String getAuthor() {
        return author;
    }

    /**
     * A setter for author field
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * A getter for pages field
     * @return String
     */
    public int getPages() {
        return pages;
    }

    /**
     * A setter for pages field
     * @param pages
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * A getter for imgUrl field
     * @return String
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * A setter for imgUrl field
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * A getter for shortDesc field
     * @return String
     */
    public String getShortDesc() {
        return shortDesc;
    }

    /**
     * A setter for shortDesc field
     * @param shortDesc
     */
    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    /**
     * A getter for longDesc field
     * @return String
     */
    public String getLongDesc() {
        return longDesc;
    }

    /**
     * A setter for longDesc field
     * @param longDesc
     */
    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", imgUrl='" + imgUrl + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", longDesc='" + longDesc + '\'' +
                '}';
    }
}
