package com.educons.njuz.feed.dummy;

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
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item Title", "Item " + position, makeDetails(position), "http://www.google.rs", "http://www.google.rs", "Author's Name", "today");
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
    public static class DummyItem {
        public final String id;
        public final String title;
        public final String content;
        public final String details;
        public final String url;
        public final String urlToImg;
        public final String author;
        public final String publishedAt;

        public DummyItem(String id, String title, String content, String details, String url, String urlToImg, String author, String publishedAt) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.details = details;
            this.url = url;
            this.urlToImg = urlToImg;
            this.author = author;
            this.publishedAt = publishedAt;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
