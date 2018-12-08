package carl.yasona.com.workout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class workoutLoader extends AppCompatActivity{
    String newLine = System.getProperty("line.separator");
    TextView test1;
    TextView test2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_screen);
        test1 = findViewById(R.id.textView17);
        Bundle extras = getIntent().getExtras();
        String work = "";
        if (extras != null) {
           work = extras.getString("tempWork");
        }


        workLoader(work);
    }

    public void workLoader(String work){
        try {
            FileOutputStream fos = openFileOutput("finish.txt", MODE_APPEND);
            fos.write(work.getBytes());
            fos.write(newLine.getBytes());

            FileInputStream fin = openFileInput("finish.txt");
            FileInputStream workList = openFileInput("workout1.txt");
            BufferedReader finish = new BufferedReader(new InputStreamReader(fin));
            BufferedReader workBr = new BufferedReader(new InputStreamReader(workList));

            boolean bool = true;
            ArrayList<String> lists= new ArrayList<String>();
            ArrayList<String> tempLists= new ArrayList<String>();
            while (bool){
                String partOne = workBr.readLine();
                String partTwo = finish.readLine();

                if(partOne != null){
                    lists.add(partOne);
                } else {
                    bool = false;
                }
                if(partTwo != null){
                    tempLists.add(partTwo);
                }
            }
            String entireFile = "";
            lists.add("finish");
            Boolean pushupDone = false;
            Boolean situpDone = false;
            Boolean jumpDone = false;
            Boolean stretchDone = false;
            Boolean squatsDone = false;
            Collections.reverse(lists);
            for(String wlist: lists) {

                test1.setText(wlist);

                if(wlist.equals("Sit-Ups")){
                    for (String result: tempLists){
                        if(result.equals("Sit-Ups")){
                            situpDone = true;
                        }
                    }
                    if(!situpDone){
                        startActivity(new Intent(this, situpsActivity.class));

                    }
                }

                if(wlist.equals("Push-Ups")){
                    for (String result: tempLists){
                        if(result.equals("Push-Ups")){
                            pushupDone = true;
                        }
                    }
                    if(!pushupDone){
                        startActivity(new Intent(this, pushupActivity.class));

                    }
                }

                if(wlist.equals("Jumping Jacks")){
                    for (String result: tempLists){
                        if(result.equals("Jumping Jacks")){
                            jumpDone = true;
                        }
                    }
                    if(!jumpDone){
                        startActivity(new Intent(this, jumpingActivity.class));

                    }
                }
                if(wlist.equals("Stretching")){
                    for (String result: tempLists){
                        if(result.equals("Stretching")){
                            stretchDone = true;
                        }
                    }
                    if(!stretchDone){
                        startActivity(new Intent(this, stretchActivity.class));
                    }
                }
                if(wlist.equals("Squats")){
                    for (String result: tempLists){
                        if(result.equals("Squats")){
                            squatsDone = true;
                        }
                    }
                    if(!squatsDone){
                        startActivity(new Intent(this, squatsActivity.class));
                    }
                }
                if(wlist.equals("finish")){
                    startActivity(new Intent(this, workfinishAct.class));
                }

            }






        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
