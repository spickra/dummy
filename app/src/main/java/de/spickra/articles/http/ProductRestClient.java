package de.spickra.articles.http;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class ProductRestClient {

    private static final String PRODUCT_URL = "https://www.otto.de/product/products/views/mini/?articleNumber=";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getProductByArticleNumber(String articleNumber, AsyncHttpResponseHandler responseHandler) {
        String productUrl = PRODUCT_URL + articleNumber;

        Log.d("ProductRestClient", "Reading from " + productUrl);
        client.get(productUrl, null, responseHandler);
    }
}
