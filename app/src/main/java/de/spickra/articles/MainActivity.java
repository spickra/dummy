package de.spickra.articles;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import de.spickra.articles.tools.MiniAdsToButtonTextHandler;

import static de.spickra.articles.http.ProductRestClient.getProductByArticleNumber;


public class MainActivity extends ActionBarActivity {

    private Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.search_button);

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

                final String articleNumber = String.valueOf(new Random().nextInt(900_000) + 100_000);
                final MiniAdsToButtonTextHandler miniAdsToButtonText = new MiniAdsToButtonTextHandler(articleNumber, button);

                getProductByArticleNumber(articleNumber, miniAdsToButtonText);
            }
        });
    }
}
