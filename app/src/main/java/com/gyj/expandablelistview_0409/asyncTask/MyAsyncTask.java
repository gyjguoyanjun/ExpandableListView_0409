package com.gyj.expandablelistview_0409.asyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.gyj.expandablelistview_0409.adapters.MyBaseExpandadapter;
import com.gyj.expandablelistview_0409.bean.Info;
import com.gyj.expandablelistview_0409.utils.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

/**
 * data:2017/4/7
 * author:郭彦君(Administrator)
 * function:
 */
public class MyAsyncTask extends AsyncTask<String, Integer, String> {
    private Context context;
    private ExpandableListView expandableListView;
    private String s;
    private ArrayList<Info.Result> parent_list;
    private ArrayList<Info.Result> child_list;


    public MyAsyncTask(Context context, ExpandableListView expandableListView) {
        this.context = context;
        this.expandableListView = expandableListView;
    }

    @Override
    protected String doInBackground(String... params) {
        String param = params[0];
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(param);
        try {
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                InputStream inputStream = response.getEntity().getContent();
                s = StreamUtils.jsonToString(inputStream);
            }
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        Gson gson = new Gson();
        Info info = gson.fromJson(s, Info.class);
        ArrayList<Info.Result> result = info.result;
        parent_list = new ArrayList<Info.Result>();
        child_list = new ArrayList<Info.Result>();
//      抽取分离数据
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).parentid.equals("0")) {
                parent_list.add(result.get(i));
            } else {
                child_list.add(result.get(i));
            }

        }
        MyBaseExpandadapter myBaseExpandadapter = new MyBaseExpandadapter(parent_list, child_list, context);
        expandableListView.setAdapter(myBaseExpandadapter);
    }


}

