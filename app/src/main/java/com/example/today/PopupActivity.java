package com.example.today;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PopupActivity extends Activity {
Button bto,btx;
TextView text;
static int b;
MainActivity activity = (MainActivity)MainActivity.AActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        bto = (Button)findViewById(R.id.bto);
        btx = (Button)findViewById(R.id.btx);
        text = (TextView)findViewById(R.id.text);
        final Intent intent = getIntent();
        String a =intent.getStringExtra("memo!").toString();
        text.setText(a);
        bto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b++;
                finish();
            }
        });
        btx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
