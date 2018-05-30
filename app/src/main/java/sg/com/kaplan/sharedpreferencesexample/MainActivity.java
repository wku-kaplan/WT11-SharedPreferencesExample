package sg.com.kaplan.sharedpreferencesexample;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name_input, score_input;
    Switch s;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getPreferences(Context.MODE_PRIVATE);

        s = (Switch) findViewById(R.id.switch1);
        name_input = (EditText) findViewById(R.id.input_name);
        score_input = (EditText) findViewById(R.id.input_score);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("name", name_input.getText().toString());
                editor.putInt("high_score", Integer.parseInt(score_input.getText().toString()));
                editor.putBoolean("music", s.isChecked());
                editor.commit();
                Toast.makeText(MainActivity.this, "Settings saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String name = sharedPref.getString("name", "kaplan");
        name_input.setText(name);
        int score = sharedPref.getInt("high_score", 0);
        score_input.setText(Integer.toString(score));
        Boolean music = sharedPref.getBoolean("music", false);
        s.setChecked(music);
    }
}
