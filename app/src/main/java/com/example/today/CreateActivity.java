package com.example.today;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static android.app.AlarmManager.RTC_WAKEUP;

public class CreateActivity extends Activity implements TimePicker.OnTimeChangedListener {
    ListAdapter listAdapter;
    Button savebt;
    EditText edmemo;
    TimePicker timePicker;
    private GregorianCalendar mCalender;
    private  AlarmManager mManager;
    private NotificationManager mNotification;
    static int all=0;
    private DbHelper mDbHelper;
    MainActivity activity = (MainActivity)MainActivity.AActivity;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        mCalender = new GregorianCalendar();
        mManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
      mNotification = (NotificationManager)getSystemService(NOTIFICATION_SERVICE) ;
        timePicker = (TimePicker)findViewById(R.id.timepicker);
        timePicker.setCurrentHour(mCalender.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(mCalender.get(Calendar.MINUTE));
        timePicker.setOnTimeChangedListener(this);

        savebt = (Button)findViewById(R.id.savebt);

        edmemo = (EditText)findViewById(R.id.edmemo);
        listAdapter = new ListAdapter();

        savebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.check=0;
                all++;
                mDbHelper = new DbHelper(getApplicationContext());
                mDbHelper.open();
                Cursor cursor= mDbHelper.getAllColumns();
                cursor.moveToLast();
               String dba = String.valueOf(timePicker.getCurrentHour());
               String dbb = String.valueOf(timePicker.getCurrentMinute());

                String c = edmemo.getText().toString();
                mDbHelper.insert(dba,dbb,c);
                mDbHelper.close();
                setalarm(c);
            finish();
            }
        });
    }

    public  void setalarm(String c){
        mCalender.set(Calendar.SECOND,0);
        mManager.set(AlarmManager.RTC_WAKEUP,mCalender.getTimeInMillis(),pendingIntent(c));
        }

        PendingIntent  pendingIntent(String c){

        Intent i = new Intent(getApplicationContext(),PopupActivity.class);
        i.putExtra("memo!",c);
        PendingIntent pi = PendingIntent.getActivity(this,(int) System.currentTimeMillis(),i,0);
        return pi;
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        mCalender.set(2018,5,15,hourOfDay,minute);
    }
}
