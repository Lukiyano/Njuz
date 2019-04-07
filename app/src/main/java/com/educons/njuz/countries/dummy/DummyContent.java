package com.educons.njuz.countries.dummy;

import android.graphics.Bitmap;

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
    public static final List<Country> ITEMS = new ArrayList<Country>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Country> ITEM_MAP = new HashMap<String, Country>();

    private static final int COUNT = 6;

    static {
        // Add some sample items.
        addItem(new Country("us", "United States", null));
        addItem(new Country("gb", "Great Britain", null));
        addItem(new Country("rs", "Serbia", null));
        addItem(new Country("au", "Australia", null));
        addItem(new Country("ru", "Russia", null));
        addItem(new Country("ch", "Switzerland", null));
        addItem(new Country("fr", "France", null));
        addItem(new Country("de", "Germany", null));
    }

    private static void addItem(Country item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Country {
        public final String id;
        public final String name;
        public final Bitmap flag;

        public Country(String id, String name, Bitmap flag) {
            this.id = id;
            this.name = name;
            this.flag = flag;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
