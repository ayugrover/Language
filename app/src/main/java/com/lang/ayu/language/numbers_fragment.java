package com.lang.ayu.language;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class numbers_fragment extends Fragment {
    private MediaPlayer media;
    private AudioManager mAudioManager;


    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                media.pause();
                media.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                media.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener Completion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words1 = new ArrayList<Word>();
        words1.add(new Word("One", "Lutti", R.drawable.number_one, R.raw.number_one));
        words1.add(new Word("Two", "Otiiko", R.drawable.number_two, R.raw.number_two));
        words1.add(new Word("Three", "Oyyisa", R.drawable.number_three, R.raw.number_four));
        words1.add(new Word("Four", "Tolookosu", R.drawable.number_four, R.raw.number_three));
        words1.add(new Word("Five", "Massokka", R.drawable.number_five, R.raw.number_five));
        words1.add(new Word("Six", "Temmokka", R.drawable.number_six, R.raw.number_six));
        words1.add(new Word("Seven", "Kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words1.add(new Word("Eight", "Kawinta", R.drawable.number_eight, R.raw.number_eight));
        words1.add(new Word("Nine", "Wo,e", R.drawable.number_nine, R.raw.number_nine));
        words1.add(new Word("Ten", "Na,aacha", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(getActivity(), words1, R.color.category_numbers);

        ListView list = (ListView) rootView.findViewById(R.id.list1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word word = words1.get(position);
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    media = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    media.start();
                    media.setOnCompletionListener(Completion);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (media != null) {

            media.release();
            media = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}