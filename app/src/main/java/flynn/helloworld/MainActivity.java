package flynn.helloworld;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Choreographer;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static android.R.color.holo_green_dark;

public class MainActivity extends AppCompatActivity {

    public View background;
    public TextView text;
    public EditText editText;
    public Button textColorButton;
    public Random random = new Random();
    public int alpha = 255;
    public boolean colorMode;

    public void ChangeColor(View v)
    {
        text.setTextColor(Color.argb(alpha,random.nextInt(255),random.nextInt(255),random.nextInt(255)));
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
        input = input.trim().matches("")?"Hello from Flynn!":input;
        text.setText(input);
        alpha = 0;
        setAlpha();
    }

    public void toggleMode(View v)
    {
        colorMode = !colorMode;

        if(colorMode)
        {
            textColorButton.setVisibility(View.INVISIBLE);
        }
        else
        {
            textColorButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        background = findViewById(R.id.background);
        text = (TextView) findViewById(R.id.displayText);
        textColorButton = (Button) findViewById(R.id.buttonChangeTextColor);

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

        final Handler handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                alpha +=50;
                if(alpha>255)
                    alpha = 255;
                setAlpha();

                handler.postDelayed(this,50); // set time here to refresh textView
            }
        });
    }

    public void setAlpha() {
        int color = text.getCurrentTextColor();
        text.setTextColor(Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color)));
    }
}
