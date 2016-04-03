package com.example.calvinkwan.glassdoor_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    DatabaseAdapter adapter;
    private EditText edt;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        openDB();
        populateList();



    }



    public void showChangeLangDialog()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialogbox, null);
        dialogBuilder.setView(dialogView);

        edt = (EditText) dialogView.findViewById(R.id.editTextDialogUserInput);

        dialogBuilder.setTitle("Custom dialog");
        dialogBuilder.setMessage("Enter text below");
        dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                //do something with edt.getText().toString();
                String temp = edt.getText().toString();
                if (temp.length() != 0)
                {
                    adapter.insertRow(temp);
                }
                populateList();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                //String data = dbAdapter.getAllData();
                //Message.message(MainActivity.this, data);
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void AddNewText(View view)               //opens dialog box to input message
    {
        showChangeLangDialog();
    }

    private void openDB()
    {
        adapter = new DatabaseAdapter(this);
        adapter.open();         //instantiates a new instance of a database
    }
    private void populateList()
    {
        Cursor cursor = adapter.getAllRows();

        String[] messageText = new String[] {adapter.KEY_ROWID, adapter.KEY_MESSAGE};       //takes message in cursor object to extra into a string array
        int[] messageID = new int[] {R.id.messageID, R.id.messageString};
        SimpleCursorAdapter cursorAdapter;
        cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.customrow, cursor, messageText, messageID, 0);
        ListView myList = (ListView) findViewById(R.id.listView);
        myList.setAdapter(cursorAdapter);
    }

}
