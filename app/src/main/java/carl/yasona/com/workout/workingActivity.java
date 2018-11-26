package carl.yasona.com.workout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Locale;

public class workingActivity extends AppCompatActivity {
    TextView countdownText;
    private static final long START_TIME_IN_MILLIS = 30000;
    private Button mButtonNext;
    private CountDownTimer mCountDownTimer;


    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_pushups);
        try {
            FileInputStream fin = openFileInput("workout1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            String line;

            while((line = br.readLine()) != null) {
                if(line == "Push-Ups"){

                    setContentView(R.layout.workout_pushups);
                    pushupAct();
                } else if(line == "Sit-Ups"){

                    setContentView(R.layout.workout_situps);
                    situpAct();
                } else if(line == "Jumping"){

                    setContentView(R.layout.workout_jumping);
                    jumpingAct();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pushupAct(){
        countdownText = findViewById(R.id.CountdownTime);
        mButtonNext = findViewById(R.id.pushupNext);
        startTimer();
    }

    public void situpAct(){
        countdownText = findViewById(R.id.CountdownTime);
        mButtonNext = findViewById(R.id.situpsNext);
        startTimer();
    }

    public void jumpingAct(){
        countdownText = findViewById(R.id.CountdownTime);
        mButtonNext = findViewById(R.id.jumpNext);
        startTimer();
    }


    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mButtonNext.setVisibility(View.VISIBLE);
                countdownText.setText("");
            }
        }.start();
        mButtonNext.setVisibility(View.INVISIBLE);
    }
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        countdownText.setText(timeLeftFormatted);
    }
}
