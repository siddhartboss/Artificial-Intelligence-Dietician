package com.example.ft.aidt;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class Scheduler extends AppCompatActivity {
    private int o=0;
    private Button b1,b2;
    private EditText e1,e2;
    private ImageView hg;
    TimePicker tpl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        e1=(EditText) findViewById(R.id.editText6);
        e2= (EditText) findViewById(R.id.editText11);
        tpl= (TimePicker) findViewById(R.id.time_picker);
        hg =(ImageView) findViewById(R.id.imageView12);
        hg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Scheduler.this,"If you want to set an alarm you can use this feature. However you can only set one alarm in the current version.",Toast.LENGTH_LONG).show();
            }
        });
        Intent intent = new Intent(Scheduler.this,Scheduler12.class);
        intent.putExtra("Notification ID",o);
        intent.putExtra("Details",e2.getText().toString());
        intent.putExtra("ToDo",e1.getText().toString());
        final PendingIntent p = PendingIntent.getBroadcast(Scheduler.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        final AlarmManager alaram = (AlarmManager) getSystemService(ALARM_SERVICE);
        b1= (Button) findViewById(R.id.button8);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour =tpl.getCurrentHour();
                int min= tpl.getCurrentMinute();
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY,hour);
                cal.set(Calendar.MINUTE,min);
                cal.set(Calendar.SECOND,0);
                long alaramsst = cal.getTimeInMillis();
                alaram.set(AlarmManager.RTC_WAKEUP,alaramsst,p);
            }
        });

   b2 =(Button) findViewById(R.id.button10);
   b2.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           alaram.cancel(p);
           startActivity(new Intent(Scheduler.this,Actual.class));
       }
   });
    }
}
