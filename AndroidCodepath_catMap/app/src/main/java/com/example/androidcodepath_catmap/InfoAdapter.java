package com.example.androidcodepath_catmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class InfoAdapter implements GoogleMap.InfoWindowAdapter {



        private final View mWindow;
        private Context mContext;

        public InfoAdapter(Context context) {
            mContext = context;
            mWindow = LayoutInflater.from(context).inflate(R.layout.info_window_layout, null);
        }

        private void rendowWindowText(Marker marker, View view) {

            String title = marker.getTitle();
            TextView tvTitle = (TextView) view.findViewById(R.id.title);
            ImageView iv = (ImageView) view.findViewById(R.id.markerImage);
            iv.setImageResource(R.drawable.cat);

            if (!title.equals("")) {
                tvTitle.setText(title);
            }

            String snippet = marker.getSnippet();
            TextView tvSnippet = (TextView) view.findViewById(R.id.snippet);

            if (!snippet.equals("")) {
                tvSnippet.setText(snippet);
            }
        }

        @Override
        public View getInfoWindow(Marker marker) {
            rendowWindowText(marker, mWindow);
            return mWindow;
        }

        @Override
        public View getInfoContents(Marker marker) {
            rendowWindowText(marker, mWindow);
            return mWindow;
        }


    }

