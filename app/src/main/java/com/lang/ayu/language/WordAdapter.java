package com.lang.ayu.language;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private int mcolorResourceId;
    public WordAdapter(@NonNull Context context, ArrayList<Word>words,int colorResourceId) {
        super(context,0,words);
        mcolorResourceId=colorResourceId;
    }

    @Override
    public  View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Word currentWord = getItem(position);
        TextView miwokText = (TextView) listItemView.findViewById(R.id.text1);
        miwokText.setText(currentWord.getMiwokword());
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.text2);
        defaultTextView.setText(currentWord.getEngword());
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image1);
        if(currentWord.hasImage())
        {
            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }
        else
        {
            imageView.setVisibility(View.GONE);
        }
        View textContainer = listItemView.findViewById(R.id.container);
        int color = ContextCompat.getColor(getContext(),mcolorResourceId);
        textContainer.setBackgroundColor(color);
        return listItemView;
    }
}
