package de.spickra.hellooo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.makeText;


public class MainActivity extends ActionBarActivity {

    private static final String PREFS = "prefs";
    private static final String PREF_NAME = "name";

    private EditText input = null;
    private Button button = null;
    private ListView list = null;
    private List<String> listEntries;
    private ArrayAdapter listAdapter;

    ShareActionProvider mShareActionProvider;
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listEntries = new ArrayList();
        listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listEntries);
        list = (ListView) findViewById(R.id.main_listview);
        list.setAdapter(listAdapter);
        button = (Button) findViewById(R.id.main_button);
        input = (EditText) findViewById(R.id.main_edittext);


        handleClickOnButton();
        handleClickOnListItem();
//
//        displayWelcome();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        // Inflate the menu.
//        // Adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        // Access the Share Item defined in menu XML
//        MenuItem shareItem = menu.findItem(R.id.menu_item_share);
//
//        // Access the object responsible for
//        // putting together the sharing submenu
//        if (shareItem != null) {
//            mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
//        }
//
//        // Create an Intent to share your content
//        setShareIntent();
//
          return true;
    }

    private void handleClickOnListItem() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listEntries.set(position, listEntries.get(position) + " *");
                listAdapter.notifyDataSetChanged();
            }
        });
    }

    private void handleClickOnButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String someText = input.getText().toString() + " is learning Android!";

                // kleiner Hinweis
                makeText(getApplicationContext(), someText, Toast.LENGTH_LONG).show();

                // Liste erweitern
                listEntries.add(someText);
                listAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setShareIntent() {

        if (mShareActionProvider != null) {

            // create an Intent with the contents of the TextView
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Android Development");
            shareIntent.putExtra(Intent.EXTRA_TEXT, input.getText());

            // Make sure the provider knows
            // it should work with that Intent
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    public void displayWelcome() {

        // Access the device's key-value storage
        mSharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);

        // Read the user's name, or an empty string if nothing found
        String name = mSharedPreferences.getString(PREF_NAME, "");

        if (name.length() > 0) {

            // If the name is valid, display a Toast welcoming them
            makeText(this, "Welcome back, " + name + "!", Toast.LENGTH_LONG).show();
        } else {

            // otherwise, show a dialog to ask for their name
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Hello!");
            alert.setMessage("What is your name?");

            // Create EditText for entry
            final EditText input = new EditText(this);
            alert.setView(input);

            // Make an "OK" button to save the name
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {

                    // Grab the EditText's input
                    String inputName = input.getText().toString();

                    // Put it into memory (don't forget to commit!)
                    SharedPreferences.Editor e = mSharedPreferences.edit();
                    e.putString(PREF_NAME, inputName);
                    e.apply();

                    // Welcome the new user
                    makeText(getApplicationContext(), "Welcome, " + inputName + "!", Toast.LENGTH_LONG).show();
                }
            });

            // Make a "Cancel" button
            // that simply dismisses the alert
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {}
            });

            alert.show();
        }
    }
}
