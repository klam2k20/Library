package com.example.mylibrary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;


import java.util.ArrayList;


public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder>{
    private static final String TAG = "BookRecViewAdapter";
    private ArrayList<Book> books = new ArrayList<>();
    private Context myContext;
    private String parentActivity;

    public BookRecViewAdapter(Context myContext, String parentActivity) {
        this.myContext = myContext;
        this.parentActivity = parentActivity;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater.from(where you're getting the context).inflate(layout of item, where you're putting it)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book incomingBook = books.get(position);
        Log.d(TAG, "onBindViewHolder: Called");
        holder.txtTitle.setText(incomingBook.getTitle());
        holder.txtAuthor.setText(incomingBook.getAuthor());
        holder.txtShortDesc.setText(incomingBook.getShortDesc());
        Glide.with(myContext)
                .asBitmap()
                .load(incomingBook.getImgUrl())
                .into(holder.imgBook);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, BookActivity.class);
                intent.putExtra(myContext.getResources().getString(R.string.BOOK_ID_KEY), incomingBook.getId());
                myContext.startActivity(intent);
            }
        });

        if (incomingBook.isExpanded()) {
            TransitionManager.beginDelayedTransition((holder.parent));
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.btnDownArrow.setVisibility(View.GONE);
            if (parentActivity.equals(myContext.getResources().getString(R.string.all_Books))) {
                holder.btnDelete.setVisibility(View.GONE);
            }
            else if (parentActivity.equals(myContext.getResources().getString(R.string.currently_Reading_Books))) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
                        builder.setMessage("Are  you sure you want to delete " + incomingBook.getTitle() + " ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(myContext).removeFromCurrentlyRead(incomingBook)) {
                                    Toast.makeText(myContext, incomingBook.getTitle() + " Removed!", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                                else {
                                    Toast.makeText(myContext, "Error! Try Again!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });


            }
            else if (parentActivity.equals(myContext.getResources().getString(R.string.already_Read_Books))) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
                        builder.setMessage("Are  you sure you want to delete " + incomingBook.getTitle() + " ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(myContext).removeFromAlreadyRead(incomingBook)) {
                                    Toast.makeText(myContext, incomingBook.getTitle() + " Removed!", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                                else {
                                    Toast.makeText(myContext, "Error! Try Again!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });
            }
            else if (parentActivity.equals(myContext.getResources().getString(R.string.wishList_Books))) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
                        builder.setMessage("Are  you sure you want to delete " + incomingBook.getTitle() + " ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(myContext).removeFromWishList(incomingBook)) {
                                    Toast.makeText(myContext, incomingBook.getTitle() + " Removed!", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                                else {
                                    Toast.makeText(myContext, "Error! Try Again!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });
            }
            else if (parentActivity.equals(myContext.getResources().getString(R.string.favorite_Books))) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
                        builder.setMessage("Are  you sure you want to delete " + incomingBook.getTitle() + " ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(myContext).removeFromFavorite(incomingBook)) {
                                    Toast.makeText(myContext, incomingBook.getTitle() + " Removed!", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                                else {
                                    Toast.makeText(myContext, "Error! Try Again!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });

            }

        }
        else {
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.btnDownArrow.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView .ViewHolder {
        private CardView parent;
        private RelativeLayout expandedRelLayout;
        private ImageView imgBook, btnUpArrow, btnDownArrow;
        private TextView txtTitle, txtAuthor, txtShortDesc, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.parent = itemView.findViewById(R.id.cardView);
            this.expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);
            this.imgBook = itemView.findViewById(R.id.imgBookActLogo);
            this.btnUpArrow = itemView.findViewById(R.id.btnUpArrow);;
            this.btnDownArrow = itemView.findViewById(R.id.btnDownArrow);
            this.txtTitle = itemView.findViewById(R.id.txtBookActTitle);
            this.txtAuthor = itemView.findViewById(R.id.txtBookActAuthor);
            this.txtShortDesc = itemView.findViewById(R.id.txtShortDesc);
            this.btnDelete = itemView.findViewById(R.id.btnDelete);

            btnDownArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            btnUpArrow.setOnClickListener(new View .OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });


        }
    }
}
