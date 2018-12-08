package carl.yasona.com.workout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class workoutEdit extends AppCompatActivity {
    Toast currentToast;
    TextView work1;
    TextView tUser;
    TextView tWorkoutName;
    TextView exerName;
    TextView tButton1;
    FileOutputStream fos = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_workout_add);
        work1 = findViewById(R.id.workList);
        tUser = findViewById(R.id.userName);
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        String name = sp.getString("fname", "USER"); // s = key, s1 = defvalue
        String message = name;
        tUser.setText(message);
        loadList();
    }

    public void addJumpJacks(View v){
        boolean limit = workoutLimit();
        if (limit) {
            try {
                String act = "Jumping Jacks";
                String newLine = System.getProperty("line.separator");
                fos = openFileOutput("customworkout1.txt", MODE_APPEND);
                fos.write(act.getBytes());
                fos.write(newLine.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (currentToast != null) {
                currentToast.cancel();
            }
            currentToast = Toast.makeText(this, "Added Jumping Jacks!",
                    Toast.LENGTH_SHORT);

            currentToast.show();
            loadList();
        } else {
            error();
        }

    }
    public void addStretching(View v){
        boolean limit = workoutLimit();
        if(limit) {
            try {
                String act = "Stretching";
                String newLine = System.getProperty("line.separator");
                fos = openFileOutput("customworkout1.txt", MODE_APPEND);
                fos.write(act.getBytes());
                fos.write(newLine.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (currentToast != null) {
                currentToast.cancel();
            }
            currentToast = Toast.makeText(this, "Added Stretching!",
                    Toast.LENGTH_SHORT);

            currentToast.show();
            loadList();
        }else{
            error();
        }

    }
    public void addSquats(View v) {
        boolean limit = workoutLimit();

        if (limit) {
            try {
                String act = "Squats";
                String newLine = System.getProperty("line.separator");
                fos = openFileOutput("customworkout1.txt", MODE_APPEND);
                fos.write(act.getBytes());
                fos.write(newLine.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (currentToast != null) {
                currentToast.cancel();
            }
            currentToast = Toast.makeText(this, "Added Squats!",
                    Toast.LENGTH_SHORT);

            currentToast.show();
            loadList();
        } else {
            error();
        }
    }
    public void addPushups(View v){
        boolean limit = workoutLimit();
        if(limit) {
            try {
                String act = "Push-Ups";
                String newLine = System.getProperty("line.separator");
                fos = openFileOutput("customworkout1.txt", MODE_APPEND);
                fos.write(act.getBytes());
                fos.write(newLine.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (currentToast != null) {
                currentToast.cancel();
            }
            currentToast = Toast.makeText(this, "Added Push-Ups!",
                    Toast.LENGTH_SHORT);

            currentToast.show();
            loadList();
        }else{
            error();
        }

    }
    public void addSitups(View v){
        boolean limit = workoutLimit();
        if(limit) {
            try {
                String act = "Sit-Ups";
                String newLine = System.getProperty("line.separator");
                fos = openFileOutput("customworkout1.txt", MODE_APPEND);
                fos.write(act.getBytes());
                fos.write(newLine.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (currentToast != null) {
                currentToast.cancel();
            }
            currentToast = Toast.makeText(this, "Added Sit-Ups!",
                    Toast.LENGTH_SHORT);

            currentToast.show();
            loadList();
        } else {
            error();
        }
    }

    public void loadList() {
        try {
            FileInputStream fin = openFileInput("customworkout1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            String line;
            String entireFile = "";
            ArrayList<String> lists= new ArrayList<String>();
            while((line = br.readLine()) != null) {
                lists.add(line);
            }
            for(String work: lists){
                entireFile += (work + "\n");
            }
            work1.setText(entireFile);

        } catch (Exception e) {
            e.printStackTrace();
            work1.setText("");
        }
    }

    public boolean workoutLimit(){
        FileInputStream fin = null;
        int count = 0;
        boolean chk = true;
        try {
            fin = openFileInput("customworkout1.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fin));
        while(br.readLine() != null){
            count++;
        }
        if(count >= 5){
            chk = false;
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return chk;
    }
    public void error(){
        if (currentToast != null) {
            currentToast.cancel();
        }
        currentToast = Toast.makeText(this, "Only 5 exercises is allowed!",
                Toast.LENGTH_SHORT);

        currentToast.show();
    }

    public void saveFile(View v){
        SharedPreferences sp = getSharedPreferences("data2", MODE_PRIVATE); //paper
        SharedPreferences.Editor writer = sp.edit(); //pen
        tWorkoutName = findViewById(R.id.workoutName);
        String name = tWorkoutName.getText().toString();
        if(name.equals("")){
            name = "Workout1";
        }
        writer.putString("work1", name);
        writer.commit();

        if (currentToast != null) {
            currentToast.cancel();
        }
        currentToast = Toast.makeText(this, "Saved Successfully",
                Toast.LENGTH_SHORT);

        currentToast.show();
        Intent i = new Intent( this, custworkActivity.class);
        startActivity(i);
    }

    public void deleteFile(View v){
        File dir = getFilesDir();
        File file = new File(dir, "customworkout1.txt");
        boolean deleted = file.delete();
        loadList();
    }

}
