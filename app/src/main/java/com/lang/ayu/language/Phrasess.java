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

public class Phrasess extends Fragment {

    private MediaPlayer media;
    private AudioManager audio;

    public Phrasess() {
        // Required empty public constructor
    }

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

        private MediaPlayer.OnCompletionListener completion = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.word_list, container, false);
            audio = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

            final ArrayList<Word> words = new ArrayList<Word>();
            words.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
            words.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
            words.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
            words.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
            words.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
            words.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
            words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
            words.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
            words.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
            words.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

            WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
            ListView listView = (ListView) rootView.findViewById(R.id.list1);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    releaseMediaPlayer();
                    Word word = words.get(i);
                    int result = audio.requestAudioFocus(mOnAudioFocusChangeListener,
                            AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                    if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        media = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                        media.start();
                        media.setOnCompletionListener(completion);
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
                audio.abandonAudioFocus(mOnAudioFocusChangeListener);
            }
        }
}
