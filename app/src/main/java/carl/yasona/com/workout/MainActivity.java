package carl.yasona.com.workout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Timer().schedule(new TimerTask(){
            public void run() {
                SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
                if(sp.getString("fname", null) == null) {
                    startActivity(new Intent(MainActivity.this, registerActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(MainActivity.this, menuActivity.class));
                    finish();
                }

            }
        }, 2500 );
    }

}
