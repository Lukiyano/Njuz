package com.educons.njuz.sources.dummy;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;

import com.educons.njuz.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<Source> ITEMS = new ArrayList<Source>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, Source> ITEM_MAP = new HashMap<String, Source>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        addItem(new Source("1", "BBC", "BBC description","http://www.bbc.com/news","en","us"));
        addItem(new Source("2", "CNN", "dummy description","http://www.cnn.com","en","us"));
        addItem(new Source("3", "Al Jazeera","dummy description","http://www.aljazeera.com","en","us"));
        addItem(new Source("4", "FOX", "BBC description dummy description","http://www.foxnews.com","en","us"));
        addItem(new Source("5", "Yahoo", "dummy descriptiondummy descriptiondummy descriptiondummy description","http://www.news.yahoo.com","en","us"));

    }

    public static void addItem(Source item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Source createDummyItem(int position) {
        return new Source(String.valueOf(position), "Source " + position,"description",null,"en","us");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Source {
        public final String id;
        public final String name;
        public final String description;
        public final String url;
        public final String language;
        public final String country;

        public Source(String id, String name, String description, String url, String language, String country) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.url = url;
            this.language = language;
            this.country = country;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
