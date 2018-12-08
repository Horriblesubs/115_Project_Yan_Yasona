package carl.yasona.com.workout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class regworkActivity extends AppCompatActivity {
    TextView tUser;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regular_workout);
        tUser = findViewById(R.id.userName);
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        String name = sp.getString("fname", "USER"); // s = key, s1 = defvalue
        String message = name;
        tUser.setText(message);
    }
    public void beginButton(View v){
        Intent i = new Intent(this, listworkoutActivity.class);
        i.putExtra("workLevel","Beginner");
        startActivity(i);
    }
    public void interButton(View v){
        Intent i = new Intent( this, listworkoutActivity.class);
        i.putExtra("workLevel","Intermediate");
        startActivity(i);
    }
}
