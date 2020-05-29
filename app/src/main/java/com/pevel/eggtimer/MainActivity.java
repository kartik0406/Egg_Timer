package com.pevel.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textview;
    SeekBar seekbar;
    boolean active=false;
    Button gobutton;
    CountDownTimer countdown;
    public void buttonclicked(View view) {
        if (active) {
            countdown.cancel();
            textview.setText("0:30");
            seekbar.setProgress(30);
            gobutton.setText("GO!");
            active = false;
            seekbar.setEnabled(true);

        } else {
            gobutton.setText("Stop");
            active = true;
            seekbar.setEnabled(false);
            countdown = new CountDownTimer(seekbar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updatetimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();

                }
            }.start();
        }
        }
        public void updatetimer ( int i)
        {
            int min = i / 60;
            int sec = i - min * 60;
            String secstring = Integer.toString(sec);
            if (sec <= 9) {
                secstring = "0" + secstring;
            }
            textview.setText(Integer.toString(min) + ":" + secstring);
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekbar=findViewById(R.id.seekBar);
        textview=findViewById(R.id.textView);
        seekbar.setMax(600);
        gobutton=findViewById(R.id.button);
        seekbar.setProgress(30);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
               updatetimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
