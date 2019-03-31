package com.educons.njuz.feed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.educons.njuz.R;

import com.educons.njuz.countries.CountryDetailFragment;
import com.educons.njuz.countries.CountryListActivity;
import com.educons.njuz.feed.dummy.DummyContent;
import com.educons.njuz.sources.SourceListActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An activity representing a list of NewsPreviews. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link NewsPreviewDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class NewsPreviewListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newspreview_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (findViewById(R.id.newspreview_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        recyclerView = findViewById(R.id.newspreview_list);
        assert recyclerView != null;
        setNews();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<DummyContent.DummyItem> news) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, news, mTwoPane));
    }

    private void setNews() {
        final List<DummyContent.DummyItem> news = new ArrayList<DummyContent.DummyItem>();
        final Map<String, DummyContent.DummyItem> news_map = new HashMap<String, DummyContent.DummyItem>();

        Bundle extras = getIntent().getExtras();
        String url = "https://newsapi.org/v2/everything?q=breaking news&sortBy=popularity&apiKey=b48b7896db1c4975aebdd9cf9125f1a8";
        if (extras != null) {
            String countryID = extras.get(CountryDetailFragment.ARG_ITEM_ID).toString();
            url = "https://newsapi.org/v2/top-headlines?country="+countryID+"&apiKey=b48b7896db1c4975aebdd9cf9125f1a8";
        }

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONArray array = null;
                                try {
                                    array = response.getJSONArray("articles");

                                    for (int i = 0 ; i < array.length(); i++) {
                                        JSONObject obj = array.getJSONObject(i);
                                        DummyContent.DummyItem article = new DummyContent.DummyItem((i+1)+"", obj.get("title").toString(), obj.get("description").toString(), obj.get("content").toString(), obj.get("url").toString(), obj.get("urlToImage").toString(), obj.get("author").toString(), obj.get("publishedAt").toString());
                                        news.add(article);
                                        news_map.put(article.id, article);
                                        DummyContent.addItem(article);
                                    }
                                    DummyContent.ITEMS = news;
                                    DummyContent.ITEM_MAP = news_map;
                                    setupRecyclerView(recyclerView, news);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Log.e("Rest response", response.toString());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("error", error.getMessage());
                            }
                        });
        requestQueue.add(jsonObjectRequest);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(this, NewsPreviewListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_sources) {
            Intent intent = new Intent(this, SourceListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_countries) {
            Intent intent = new Intent(this, CountryListActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final NewsPreviewListActivity mParentActivity;
        private final List<DummyContent.DummyItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(NewsPreviewDetailFragment.ARG_ITEM_ID, item.id);
                    NewsPreviewDetailFragment fragment = new NewsPreviewDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.newspreview_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, NewsPreviewDetailActivity.class);
                    intent.putExtra(NewsPreviewDetailFragment.ARG_ITEM_ID, item.id);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(NewsPreviewListActivity parent,
                                      List<DummyContent.DummyItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.newspreview_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).title);

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
//            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
//                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}
