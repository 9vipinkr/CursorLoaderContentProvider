package com.vipinkr.cursorloadercontentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "MainActivity";
    private TaskAdapter taskAdapter;
    ListView listView;
    private static final int TASK_LOADER = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView=(ListView)findViewById(R.id.listView);
    //    String[] projection={TaskContract.Column._ID,TaskContract.Column.TASKS_NAME,TaskContract.Column.TASKS_DESCRIPTION,TaskContract.Column.TASKS_SORTORDER};
        ContentResolver contentResolver=getContentResolver();
        getSupportLoaderManager().initLoader(TASK_LOADER, null, this);

//        Cursor cursor=contentResolver.query(TaskContract.CONTENT_URI,
//                projection,
//                null,
//                null,
//                TaskContract.Column.TASKS_NAME);

        taskAdapter=new TaskAdapter(this,null,0);
        listView.setAdapter(taskAdapter);

//        if(cursor!=null){
//            Log.d(TAG, "onCreate: num of columns: "+cursor.getColumnCount());
//            while(cursor.moveToNext()){
//                for (int i=0;i<cursor.getColumnCount();i++){
//                    Log.d(TAG, "onCreate: "+cursor.getColumnName(i)+": "+cursor.getString(i));
//                }
//            }
//        }
        
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection={TaskContract.Column._ID,TaskContract.Column.TASKS_NAME,TaskContract.Column.TASKS_DESCRIPTION,TaskContract.Column.TASKS_SORTORDER};
        return new CursorLoader(this,
                TaskContract.CONTENT_URI,
                projection,
                null,
                null,
                TaskContract.Column.TASKS_NAME);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
      taskAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
      taskAdapter.swapCursor(null);
    }
}
