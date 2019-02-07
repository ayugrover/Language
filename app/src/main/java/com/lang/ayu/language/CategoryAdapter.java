package com.lang.ayu.language;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;

public class CategoryAdapter extends FragmentPagerAdapter{
    private Context mcontext;
    public CategoryAdapter(Context context , FragmentManager fm)
    {
        super(fm);
        mcontext = context;
    }
    @Override
    public Fragment getItem(int position)
    {
        if(position == 0)
            return new numbers_fragment();
        else if(position == 1)
            return new familys();
        else if(position == 2)
            return new colourss();
        else
            return new Phrasess();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return mcontext.getString(R.string.intent1);
        else if(position == 1)
            return mcontext.getString(R.string.intent2);
        else if(position == 2)
            return mcontext.getString(R.string.intent3);
        else
            return mcontext.getString(R.string.intent4);
    }

    @Override
    public int getCount()
    {
        return 4;
    }
}
