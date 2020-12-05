package com.example.group07.classes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.group07.R;
import com.example.group07.activites.ViewActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * This class is an attempt to hide the implementation details needed with firebase
 * so that classes that use it will remain clean.
 *
 * This particular implementation of DatabaseFacade will create a reference to the realtime database
 * of FirebaseTest. The data of the user will live in the entries "folder" inside the database.
 */
public class DatabaseFacade {
    protected static class EntryHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;
        View mView;

        public EntryHolder(View v) {
            super(v);
            mView = v;
            title = itemView.findViewById(R.id.item_title);
            date = itemView.findViewById(R.id.item_date);
        }
    }

    private final String TAG = "DatabaseFacade";
    private String referenceName;
    private DatabaseReference rootDatabaseReference;
    private DatabaseReference dataReference;

    public DatabaseFacade(String name) {
        this.referenceName = name;
        rootDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Log.d(TAG, "Reference to the database: " + rootDatabaseReference.toString());
        dataReference = rootDatabaseReference.child(referenceName).child("entries");
        Log.d(TAG, "Reference to the entries: " + rootDatabaseReference.toString());

    }

    /**
     * @param data to add to the database
     */
    public void addValue(Object data) {
        dataReference.push().setValue(data);
    }

    public FirebaseRecyclerAdapter getFirebaseRecyclerAdapter(Context context) {
        SnapshotParser<Entry> parser = new SnapshotParser<Entry>() {
            @Override
            public Entry parseSnapshot(DataSnapshot dataSnapshot) {
                Entry entry = dataSnapshot.getValue(Entry.class);
                if (entry != null) {
                    entry.setId(dataSnapshot.getKey());
                }
                return entry;
            }
        };

        FirebaseRecyclerOptions<Entry> options =
                new FirebaseRecyclerOptions.Builder<Entry>()
                        // Query: reference to where the data is in the database, parser for how to pull in data
                        .setQuery(dataReference, parser)
                        .build();

        return new FirebaseRecyclerAdapter<Entry, DatabaseFacade.EntryHolder>(options) {
            @NonNull
            @Override
            public DatabaseFacade.EntryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                Log.d(TAG, "adapter onCreatViewHolder");
                // need inflator to allow the holder to grow with new entries
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                // inflate with the layout for each entry
                return new DatabaseFacade.EntryHolder(inflater.inflate(R.layout.browse_activity_item, viewGroup, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull DatabaseFacade.EntryHolder viewHolder,
                                            int position,
                                            @NonNull Entry entry) {
                Log.d(TAG, "adapter onBindViewHolder");
                viewHolder.title.setText(entry.getTitle());
                viewHolder.date.setText(entry.getBody());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "The " + entry.getTitle() + " item was clicked!");
                        Intent intent = new Intent(context, ViewActivity.class);
                        intent.putExtra("title", entry.getTitle());
                        intent.putExtra("body", entry.getBody());
                        intent.putExtra("date", entry.getDate());
                        context.startActivity(intent);
                    }
                });
            }
        };
    }
}
