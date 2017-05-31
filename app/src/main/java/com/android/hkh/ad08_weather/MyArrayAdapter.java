package com.android.hkh.ad08_weather;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hieu.truongvan on 5/25/2017.
 */

public class MyArrayAdapter extends ArrayAdapter<Weather> {
    private Context mycontext;
    public MyArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Weather> objects) {
        super(context, resource, objects);
        mycontext= context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Weather weather = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_wather, parent, false);
        }

        TextView tvNameCity = (TextView) convertView.findViewById(R.id.tv_namecity);
        TextView tvWeather = (TextView) convertView.findViewById(R.id.tv_weather);
        TextView tvTamperature = (TextView) convertView.findViewById(R.id.tv_temperature);
        ImageView img_icon = (ImageView) convertView.findViewById(R.id.img_icon);

        tvNameCity.setText(weather.getCityName());
        tvWeather.setText(weather.getWeather());
        tvTamperature.setText(weather.getTemperature());
        String icon = weather.getIdImage();
        //int imgResource=getContext().getResources().getIdentifier(weather.getIdImage(), "drawable", getContext().getPackageName());
        //img_icon.setImageResource(imgResource);
        Picasso.with(mycontext).load("http://openweathermap.org/img/w/"+icon).into(img_icon);
        return convertView;
    }
}
