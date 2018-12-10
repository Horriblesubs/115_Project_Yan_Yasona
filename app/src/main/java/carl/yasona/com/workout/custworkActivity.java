package carl.yasona.com.workout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class custworkActivity extends AppCompatActivity {
    TextView tButton1;
    protected void onCreate(Bundle savedInstanceState) {
        TextView tUser;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_workout);
        tUser = findViewById(R.id.userName);
        tButton1 = findViewById(R.id.workout1);
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences sp2 = getSharedPreferences("data2", MODE_PRIVATE);
        String name = sp.getString("fname", "USER"); // s = key, s1 = defvalue
        String exerName = sp2.getString("work1", "NO WORKOUT");
        String message = name;
        tUser.setText(message);
        tButton1.setText(exerName);
    }
    public void workAdd(View v){

        Intent i = new Intent(this, workoutEdit.class);
        startActivity(i);
    }

    public void customwork1(View v){
        try {
            FileInputStream fin = openFileInput("customworkout1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            if(br.readLine() == null){
                Toast.makeText(this, "No Workout Created",
                        Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(this, listworkoutActivity.class);
                i.putExtra("workLevel","Custom1");
                startActivity(i);
            }
        } catch (Exception e) {
            Toast.makeText(this, "No Workout Created",
                    Toast.LENGTH_SHORT).show();
        }

    }
}
