package de.spickra.conversations.http;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.List;

import de.spickra.messages.Conversations;
import de.spickra.messages.Message;

public class ConversationHandler extends AsyncHttpResponseHandler {

    private final List<String> entries;
    private final Gson gson = new Gson();

    public ConversationHandler(List<String> entries) {
        this.entries = entries;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        entries.clear();
        final String response = new String(responseBody);
        try {
            final Conversations conversations = gson.fromJson(response, Conversations.class);
            for (Message message : conversations.getConversations()) {
                entries.add(message.toString());
            }

        } catch (JsonSyntaxException e) {
            entries.add("nothing found..");
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        entries.clear();
        entries.add("nothing found..");
    }
}