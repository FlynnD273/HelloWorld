package flynn.helloworld;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

import java.util.Random;

import static android.R.color.holo_green_dark;

public class MainActivity extends AppCompatActivity {


    public TextView text;
    public View background;
    public EditText editText;
    public Random random = new Random();

    public void ChangeColor(View v)
    {
        text.setTextColor(Color.argb(255,random.nextInt(255),random.nextInt(255),random.nextInt(255)));
    }
    public void ChangeBackground(View v)
    {
        background.setBackgroundColor(Color.argb(255,random.nextInt(255),random.nextInt(255),random.nextInt(255)));
    };

    public void ResetView(View v)
    {
        background.setBackgroundResource(android.R.color.holo_blue_bright);
        text.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_green_dark));
    }

    public void UpdateText(View v)
    {
        String input = editText.getText().toString();
        text.setText(input.trim().matches("")?"Hello from Flynn!":input);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.displayText);
        background = (View) findViewById(R.id.background);

        editText = (EditText) findViewById(R.id.editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                UpdateText(editText);
                return true;
            }
        });
    }
}
