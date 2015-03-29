package de.spickra.messages;

import java.text.DateFormat;
import java.util.Date;

import static java.text.DateFormat.MEDIUM;
import static java.util.Locale.GERMANY;

public class Message {
    private static final DateFormat DATE_FORMAT = DateFormat.getTimeInstance(MEDIUM, GERMANY);

    private final User from;
    private final User to;
    private final String text;
    private final Date date;

    public Message(User from, User to, String text) {
        this.from = from;
        this.to = to;
        this.text = text;
        this.date = new Date();
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public boolean contains(final User user) {
        return user.equals(from) || user.equals(to);
    }

    @Override
    public String toString() {
        return String.format("%s - from %s to %s: %s", DATE_FORMAT.format(date), from, to, text);
    }
}
