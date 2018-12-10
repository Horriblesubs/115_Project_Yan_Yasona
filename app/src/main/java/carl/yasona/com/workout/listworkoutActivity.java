package carl.yasona.com.workout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class listworkoutActivity extends AppCompatActivity {
    TextView work1, tUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_list);
        tUser = findViewById(R.id.userName);
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        String name = sp.getString("fname", "USER"); // s = key, s1 = defvalue
        String message = name;
        tUser.setText(message);
        work1 = findViewById(R.id.work1);
        Bundle extras = getIntent().getExtras();
        String workLevel = "";
        if (extras != null) {
            workLevel = extras.getString("workLevel");
        }
        createList(workLevel);
    }
    public void createList2(String [] work){
        FileOutputStream fos = null;
        String newLine = System.getProperty("line.separator");
        try {
            fos = openFileOutput("workout1.txt", MODE_PRIVATE);
            for(int i = 0; i <= 5; i++){
                fos.write(work[i].getBytes());
                fos.write(newLine.getBytes());
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error writing to internal file..", Toast.LENGTH_LONG);
        }finally{
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        deleteTemp();
        loadList();
    }
    public void createList(String workLevel) {
        String work[] = new String[6];

        if(workLevel.equals("Beginner")) {
            work = new String[]{"Push-Ups", "Sit-Ups", "Jumping Jacks"};
            createList2(work);
        } else if (workLevel.equals("Intermediate")){
            work = new String[]{"Push-Ups", "Sit-Ups", "Jumping Jacks", "Stretching", "Squats"};
            createList2(work);
        } else if (workLevel.equals("Custom1")){
            File dir = getFilesDir();
            File source = new File(dir, "customworkout1.txt");
            File dest = new File(dir, "workout1.txt");
            try {
                custom1Load(source, dest);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        deleteTemp();
        loadList();

    }

    public void deleteTemp(){
        File dir = getFilesDir();
        File file = new File(dir, "finish.txt");
        boolean deleted = file.delete();
    }
    public void loadList() {
        try {
            FileInputStream fin = openFileInput("workout1.txt");
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
        }
    }

    private static void custom1Load(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    public void beginworkButton(View v){
        Intent i = new Intent(this, workoutLoader.class);
        i.putExtra("tempWork","Start");
        startActivity(i);


    }
}
