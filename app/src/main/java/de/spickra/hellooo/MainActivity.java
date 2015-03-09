package de.spickra.hellooo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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

    private EditText input = null;
    private Button button = null;
    private ListView list = null;
    private List<String> listEntries;
    private ArrayAdapter listAdapter;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
}
