package de.spickra.articles.tools;

import android.util.Log;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.DOTALL;

public class MiniAdsToButtonTextHandler extends AsyncHttpResponseHandler {

    public static final Pattern ARTICLE_NAME_PATTERN = Pattern.compile("<span.*?js_variationName.*?>(.*?)</span>", DOTALL);

    private final String articleNumber;
    private final Button button;

    public MiniAdsToButtonTextHandler(String articleNumber, Button button) {
        this.articleNumber = articleNumber;
        this.button = button;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

        String miniAds = new String(responseBody);

        Log.d(this.getClass().getSimpleName(),
                String.format("articleNumber=%s response=%s", articleNumber, miniAds));

        final Matcher articleNameMatcher = ARTICLE_NAME_PATTERN.matcher(miniAds);
        if (articleNameMatcher.find()) {
            found(articleNameMatcher.group(1));
        } else {
            notFound();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Log.e(this.getClass().getSimpleName(),
              String.format("articleNumber=%s status=%d response=%s", articleNumber, statusCode, new String(responseBody)),
              error);
        notFound();
    }

    private void found(final String articleName) {
        button.setText(String.format("%s (%s)", articleName, articleNumber));
    }

    private void notFound() {
        button.setText(String.format("Nothing found for %s, try again..", articleNumber));
    }
}
