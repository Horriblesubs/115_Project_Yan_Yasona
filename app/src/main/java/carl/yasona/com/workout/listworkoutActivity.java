package carl.yasona.com.workout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class listworkoutActivity extends AppCompatActivity {
    TextView work1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_list);
        work1 = findViewById(R.id.work1);
        createList();
        loadList();
    }

    public void createList() {
        FileOutputStream fos = null;
        String work[] = new String[3];
        String newLine = System.getProperty("line.separator");
        work = new String[] {"Jumping Jacks", "Squats", "Sit-Ups", "Push-Ups", "Planking", "Stretching"};
        try {
            fos = openFileOutput("workout1.txt", MODE_PRIVATE);
            for(int i = 0; i <= 2; i++){
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
    }
    public void loadList() {
        try {
            FileInputStream fin = openFileInput("workout1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            String line;
            String entireFile = "";
            while((line = br.readLine()) != null) {
                entireFile += (line + "\n");
            }
            work1.setText(entireFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void beginworkButton(View v){
        Intent i = new Intent(this, jumpingActivity.class);
        startActivity(i);


    }
}
