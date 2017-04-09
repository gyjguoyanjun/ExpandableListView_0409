package com.gyj.expandablelistview_0409.activitis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.gyj.expandablelistview_0409.R;
import com.gyj.expandablelistview_0409.asyncTask.MyAsyncTask;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData() {
        MyAsyncTask myAsyncTask = new MyAsyncTask(MainActivity.this, expandableListView);
        myAsyncTask.execute("http://api.jisuapi.com/weather/city?appkey=b4d06fdd59ed379f");
    }

    private void initView() {
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

    }

}
