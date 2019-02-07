package com.lang.ayu.language;

public class Word {
    private String mEngword;
    private String mMiwokword;
    private int mAudioResource;
    private int mimageId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED=-1;

    public Word(String Eng,String Miwok,int imgId,int audioresource)
    {
        mEngword = Eng;
        mMiwokword = Miwok;
        mimageId = imgId;
        mAudioResource = audioresource;
    }
    public Word(String Eng, String Miwok,int audioresource)
    {
        mEngword = Eng;
        mMiwokword = Miwok;
        mAudioResource = audioresource;
    }
    public String getEngword()
    {
        return mEngword;
    }
    public String getMiwokword()
    {
        return mMiwokword;
    }
    public int getImageResourceId(){return mimageId;}
    public boolean hasImage(){
        return mimageId !=NO_IMAGE_PROVIDED;
    }
    public int getAudioResourceId() {
        return mAudioResource;
    }
}
