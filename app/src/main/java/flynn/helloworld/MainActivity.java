package flynn.helloworld;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public View background;
    public TextView text;
    public EditText editText;
    public Random random = new Random();
    public int alpha = 255;
    public int backgroundCol;
    public int textCol;
    public int tSpeed = 7;
    public int textColId = 0;
    public int backColId = 1;
    public int[] colors;

    public void ChangeColor(View v)
    {
        //textCol = Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255));
        textCol = colors[textColId++%colors.length];
    }

    public void ChangeBackground(View v)
    {
        //backgroundCol = Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255));
        backgroundCol = colors[backColId++%colors.length];
    };

    public void ResetView(View v)
    {
        backgroundCol = ContextCompat.getColor(getApplicationContext(), android.R.color.holo_blue_bright);
        textCol = ContextCompat.getColor(getApplicationContext(), android.R.color.holo_green_dark);
    }

    public void UpdateText(View v)
    {
        String input = editText.getText().toString();
        input = input.trim().matches("")?"Hello from Flynn!":input;
        text.setText(input);
        alpha = 0;
        setTextCol();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        background = findViewById(R.id.background);
        text = (TextView) findViewById(R.id.displayText);

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

        ResetView(text);

        Handler mHandler = new Handler();
        mHandler.post(
                new Runnable()
                {
                    public void run()
                    {
                        editText.clearFocus();
                        background.requestFocus();
                    }
                }
        );
        colors = new int[]{ContextCompat.getColor(getApplicationContext(), android.R.color.holo_green_dark), ContextCompat.getColor(getApplicationContext(), android.R.color.holo_blue_bright), ContextCompat.getColor(getApplicationContext(), android.R.color.holo_blue_dark), ContextCompat.getColor(getApplicationContext(), android.R.color.holo_blue_light), ContextCompat.getColor(getApplicationContext(), android.R.color.holo_green_light), ContextCompat.getColor(getApplicationContext(), android.R.color.holo_orange_dark), ContextCompat.getColor(getApplicationContext(), android.R.color.holo_orange_light), ContextCompat.getColor(getApplicationContext(), android.R.color.holo_purple), ContextCompat.getColor(getApplicationContext(), android.R.color.holo_red_dark), ContextCompat.getColor(getApplicationContext(), android.R.color.holo_red_light)};
        final Handler handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                alpha +=50;
                if(alpha>255)
                    alpha = 255;
                setTextCol();

                handler.postDelayed(this,5); // set time here to refresh textView
            }
        });
    }

    public void setTextCol()
    {
        int color = text.getCurrentTextColor();
        text.setTextColor(Color.argb(alpha, Color.red(color) + (Color.red(textCol)-Color.red(color))/tSpeed, Color.green(color) + (Color.green(textCol)-Color.green(color))/tSpeed, Color.blue(color) + (Color.blue(textCol)-Color.blue(color))/tSpeed));
        editText.setTextColor(Color.rgb(Color.red(text.getCurrentTextColor()), Color.green(text.getCurrentTextColor()), Color.blue(text.getCurrentTextColor())));

        color = ((ColorDrawable)background.getBackground()).getColor();
        background.setBackgroundColor(Color.rgb(Color.red(color) + (Color.red(backgroundCol)-Color.red(color))/tSpeed, Color.green(color) + (Color.green(backgroundCol)-Color.green(color))/tSpeed, Color.blue(color) + (Color.blue(backgroundCol)-Color.blue(color))/tSpeed));
    }
}
