package com.vipinkr.cursorloadercontentprovider;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Vipin K R on 25-01-2017.
 */

public class TaskAdapter extends CursorAdapter {
    TextView textView;


    public TaskAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_detail,viewGroup,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        textView=(TextView)view.findViewById(R.id.textView);
        textView.setText(cursor.getString(cursor.getColumnIndex(TaskContract.Column.TASKS_NAME)));
    }
}
