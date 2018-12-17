package com.example.today;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewActivity extends LinearLayout {
    TextView htext,mtext,memo;

    public ViewActivity(Context context) {
        super(context);
        init(context);
    }

    public ViewActivity(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_view,this, true);
        htext = (TextView)findViewById(R.id.htext);
        mtext = (TextView)findViewById(R.id.mtext);
        memo = (TextView)findViewById(R.id.memo);
    }
    public void setHtext(int h){
        this.htext.setText(String.valueOf(h));
    }
    public void setMtext(int m){
        this.mtext.setText(String.valueOf(m));
    }
    public void setMemo(String memo){
        this.memo.setText(memo);
    }
}
