package carl.yasona.com.workout;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
public class situpsActivity extends AppCompatActivity {
    TextView countdownText;
    private static final long START_TIME_IN_MILLIS = 30000;
    private Button mButtonNext;
    private CountDownTimer mCountDownTimer;


    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_situps);
        countdownText = findViewById(R.id.CountdownTime);
        mButtonNext = findViewById(R.id.situpsNext);
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
    public void situpNextAct(View v){
        Intent i = new Intent(this, jumpingActivity.class);
        startActivity(i);
    }
}

