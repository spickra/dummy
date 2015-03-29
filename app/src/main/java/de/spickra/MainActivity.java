package de.spickra;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.spickra.articles.R;
import de.spickra.conversations.http.ConversationHandler;

import static de.spickra.conversations.http.ConversationRestClient.getConversations;


public class MainActivity extends ActionBarActivity {

    private Button button = null;
    private ListView listView = null;
    private List<String> listEntries = new ArrayList<>();
    private ArrayAdapter<String> listViewAdapter= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.search_button);
        listViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listEntries);;
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(listViewAdapter);

        handleClickOnButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
          return true;
    }

    private void handleClickOnButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setText("Searching..");

                final String user = "john";
                final ConversationHandler conversationHandler = new ConversationHandler(listEntries);

                getConversations(user, conversationHandler);

                listViewAdapter.notifyDataSetChanged();

                button.setText("Messages for John");
            }
        });
    }
}
