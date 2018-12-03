package carl.yasona.com.workout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.TimerTask;

public class menuActivity extends AppCompatActivity {
    TextView tUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        tUser = findViewById(R.id.userName);
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        String name = sp.getString("fname", "USER"); // s = key, s1 = defvalue
        String message = name;
        tUser.setText(message);
    }
    public void regworkButton(View v){
        Intent i = new Intent(this, regworkActivity.class);
        startActivity(i);
    }
    public void custworkButton(View v){
        Intent i = new Intent( this, situpsActivity.class);
        startActivity(i);
    }
}