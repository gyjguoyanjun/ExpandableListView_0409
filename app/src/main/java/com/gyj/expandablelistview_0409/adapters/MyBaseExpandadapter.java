package com.gyj.expandablelistview_0409.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.gyj.expandablelistview_0409.R;
import com.gyj.expandablelistview_0409.bean.Info;

import java.util.ArrayList;
import java.util.List;

/**
 * data:2017/4/9
 * author:郭彦君(Administrator)
 * function:
 */
public class MyBaseExpandadapter extends BaseExpandableListAdapter {

    private ArrayList<Info.Result> parent_list;
    private ArrayList<Info.Result> child_list;
    private Context context;

    public MyBaseExpandadapter(ArrayList<Info.Result> parent_list, ArrayList<Info.Result> child_list, Context context) {
        this.parent_list = parent_list;
        this.child_list = child_list;
        this.context = context;
    }


    @Override
    public int getGroupCount() {
        return parent_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Info.Result> list_this = new ArrayList<Info.Result>();
        for (int i = 0; i < child_list.size(); i++) {
            if (child_list.get(i).parentid.equals(parent_list.get(groupPosition).cityid)) {
                list_this.add(child_list.get(i));
            }
        }
        return list_this.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parent_list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child_list.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.group_layout, null);
        TextView parent_text = (TextView) convertView.findViewById(R.id.group_text);
        parent_text.setText(parent_list.get(groupPosition).city);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.child_layout, null);
        TextView child_text = (TextView) convertView.findViewById(R.id.child_text);
        //寻找到对应条目的子条目集合
        ArrayList<Info.Result> list_this = new ArrayList<Info.Result>();
        for (int i = 0; i < child_list.size(); i++) {
            if (child_list.get(i).parentid.equals(parent_list.get(groupPosition).cityid)) {
                list_this.add(child_list.get(i));
            }

        }
        child_text.setText(list_this.get(childPosition).city);
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
