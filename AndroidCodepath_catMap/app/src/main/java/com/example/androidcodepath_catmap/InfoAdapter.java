package com.example.androidcodepath_catmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import fragments.Map;

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

            String image = marker.getId();
            String description = ":";

            ImageView iv = (ImageView) view.findViewById(R.id.markerImage);
            if (!image.equals("")) {


                switch (title) {

                    case "SE2":
                        iv.setImageResource(R.drawable.se_b);
                        break;
                    case "KOLLIG":
                        iv.setImageResource(R.drawable.b_kl);
                        break;
                    case "SSB":
                        iv.setImageResource(R.drawable.campus_front);
                        break;
                    case "SRE":
                        iv.setImageResource(R.drawable.campus_front);
                        break;
                    case "SSM":
                        iv.setImageResource(R.drawable.campus_front);
                        break;
                    case "BSP":
                        iv.setImageResource(R.drawable.campus_front);
                        break;
                    case "SCIENG":
                        iv.setImageResource(R.drawable.b_eng1);
                        break;
                    case "GLCR":
                        iv.setImageResource(R.drawable.campus_front);
                        break;
                    case "GRAN":
                        iv.setImageResource(R.drawable.campus_front);
                        break;
                    case "ACS":
                        iv.setImageResource(R.drawable.acs_b);
                        break;
                    case "ADMIN":
                        iv.setImageResource(R.drawable.b_admin);
                        break;
                    case "CLSSRM":
                        iv.setImageResource(R.drawable.clssrm_b);
                        break;
                    case "COB2":
                        iv.setImageResource(R.drawable.cob2);
                        break;
                    default:
                        iv.setImageResource(R.drawable.campus_main_view);
                }
            }

            if (!title.equals("")) {
                tvTitle.setText(title );
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

