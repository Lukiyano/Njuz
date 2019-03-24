package com.educons.njuz.feed;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.educons.njuz.feed.dummy.DummyContent;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A fragment representing a single NewsPreview detail screen.
 * This fragment is either contained in a {@link NewsPreviewListActivity}
 * in two-pane mode (on tablets) or a {@link NewsPreviewDetailActivity}
 * on handsets.
 */
public class NewsPreviewDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    CollapsingToolbarLayout appBarLayout;
    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsPreviewDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.title);
//                appBarLayout.setTitle("RANDOM");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.newspreview_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.newspreview_detail)).setText(mItem.details);
        }

//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        JsonObjectRequest jsonObjectRequest =
//                new JsonObjectRequest(
//                        Request.Method.GET,
//                        "https://newsapi.org/v2/everything?q=breaking news&sortBy=popularity&apiKey=b48b7896db1c4975aebdd9cf9125f1a8",
//                        null,
//                        new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                JSONObject json = null;
//                                try {
//                                    JSONArray jsonArray = response.getJSONArray("articles");
//                                    json = ((JSONArray) jsonArray).getJSONObject(0);
//                                    appBarLayout.setTitle(json.get("title").toString());
//                                    ((TextView) rootView.findViewById(R.id.newspreview_detail)).setText(json.get("description").toString());
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Log.e("error", error.getMessage());
//                            }
//                        });
//        requestQueue.add(jsonObjectRequest);

        return rootView;
    }
}
