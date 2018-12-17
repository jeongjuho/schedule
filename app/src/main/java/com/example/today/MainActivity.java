package com.example.today;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class ListAdapter extends BaseAdapter{

   public static ArrayList<ListView1> items = new ArrayList<ListView1>();
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewActivity viewActivity;
        if(convertView==null)
            viewActivity = new ViewActivity(parent.getContext());

        else
            viewActivity = (ViewActivity)convertView;

        ListView1 listView = items.get(position);
        viewActivity.setHtext(listView.getHclock());
        viewActivity.setMtext(listView.getMclock());
        viewActivity.setMemo(listView.getMemo());
        return viewActivity;
    }
    public void additem(ListView1 item){
        items.add(item);
        notifyDataSetChanged();
    }
    public void delitem(int i){
        items.remove(i);
        notifyDataSetChanged();
    }
}


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(),CreateActivity.class);
        startActivity(intent);
    }

  static int check=1;
  static int check1=0;
    @Override
    protected void onResume() {
        super.onResume();
        mDbHelper = new DbHelper(getApplicationContext());
        mDbHelper.open();
        mCursor = null;
        mCursor = mDbHelper.getAllColumns();

        if(check ==0) {
            if (mCursor.moveToLast()) {
                int a = mCursor.getInt(mCursor.getColumnIndex("hour"));
                int b = mCursor.getInt(mCursor.getColumnIndex("minute"));
                String c = mCursor.getString(mCursor.getColumnIndex("memo"));
                list.additem(new ListView1(a, b, c));
            }
        }
        check=1;
        listView.setAdapter(list);

        mDbHelper.close();
        list.notifyDataSetChanged();
        ox= PopupActivity.b;

        double dox = (double)ox;
        double dall = (double)CreateActivity.all;
        int k = (int)((dox/dall)*100);
        String zs = Integer.toString(k);
        jstx.setText(zs+"점");
    }

    Button create,del;
    ListView listView;
    ListAdapter list;
    TextView jstx;
    int ox;
    private DbHelper mDbHelper;
    private Cursor mCursor;

    public static Activity AActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AActivity = MainActivity.this;
        jstx = (TextView)findViewById(R.id.jumsutx);;

        del = (Button)findViewById(R.id.del);
        listView = (ListView) findViewById(R.id.listvv);
        list = new ListAdapter();

        mDbHelper = new DbHelper(getApplicationContext());
        mDbHelper.open();
        mCursor = null;
        mCursor = mDbHelper.getAllColumns();

        if(check1==0){
            while(mCursor.moveToNext()) {
                int a = mCursor.getInt(mCursor.getColumnIndex("hour"));
                int b = mCursor.getInt(mCursor.getColumnIndex("minute"));
                String c = mCursor.getString(mCursor.getColumnIndex("memo"));
                list.additem(new ListView1(a, b, c));
            }
        }
        listView.setAdapter(list);
        check1 =1;


    create = (Button)findViewById(R.id.createbt);
    create.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),CreateActivity.class);
            startActivity(intent);
        }
    });

    del.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int pos = listView.getCheckedItemPosition();
            if(pos == ListView.INVALID_POSITION)
                return;
            list.delitem(pos);

        }
    });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDbHelper.close();
    }
}

