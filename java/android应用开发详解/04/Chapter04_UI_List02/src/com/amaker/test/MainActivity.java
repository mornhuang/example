package com.amaker.test;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cursor c = getContentResolver().query(People.CONTENT_URI, null, null, null, null);
        startManagingCursor(c);
       
        ListAdapter adapter = new SimpleCursorAdapter(this, 
                android.R.layout.simple_list_item_1, 
                c, 
                new String[] {People.NAME} ,
                new int[] {android.R.id.text1}); 
        setListAdapter(adapter);
    }
}