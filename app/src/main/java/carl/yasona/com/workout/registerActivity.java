
package carl.yasona.com.workout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class registerActivity extends AppCompatActivity {
    TextView fName, lName, userGender, userBirthday, userHeight, userWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        fName = findViewById(R.id.inpfName);
        lName = findViewById(R.id.inplName);
        userGender = findViewById(R.id.inpGender);
        userBirthday = findViewById(R.id.inpBirth);
        userHeight = findViewById(R.id.inpHeight);
        userWeight = findViewById(R.id.inpWeight);
    }

    public void saveInfo(View v) {
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE); //paper
        SharedPreferences.Editor writer = sp.edit(); //pen
        writer.putString("fname", fName.getText().toString());
        writer.putString("lname", lName.getText().toString());
        writer.putString("gender", userGender.getText().toString());
        writer.putString("birth", userBirthday.getText().toString());
        writer.putString("height", userHeight.getText().toString());
        writer.putString("weight", userWeight.getText().toString());
        writer.commit();
        Intent i = new Intent( this, menuActivity.class);
        startActivity(i);
    }
}
