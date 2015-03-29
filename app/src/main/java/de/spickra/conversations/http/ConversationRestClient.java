package de.spickra.conversations.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class ConversationRestClient {

    private static final String CONVERSATIONS_URL = "http://10.0.2.2:9090/f2g/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getConversations(String user, AsyncHttpResponseHandler responseHandler) {
        String productUrl = CONVERSATIONS_URL + user;
        client.get(productUrl, null, responseHandler);
    }
}
