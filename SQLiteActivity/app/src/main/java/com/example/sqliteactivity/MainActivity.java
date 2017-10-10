package com.example.sqliteactivity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button createBtn;
    private Button insertBtn;
    private Button updateBtn;
    private Button modifyBtn;
    private Button queryBtn;
    private Button deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createView();
        setListener();
    }

    private void createView() {
        createBtn = (Button) findViewById(R.id.createDatabase);
        updateBtn = (Button) findViewById(R.id.updateDatabase);
        insertBtn = (Button) findViewById(R.id.insert);
        modifyBtn = (Button) findViewById(R.id.modify);
        queryBtn = (Button) findViewById(R.id.query);
        deleteBtn = (Button) findViewById(R.id.delete);
    }

    private void setListener() {
        createBtn.setOnClickListener(new CreateListener());
        updateBtn.setOnClickListener(new UpdateListener());
        insertBtn.setOnClickListener(new InsertListener());
        modifyBtn.setOnClickListener(new ModifyListener());
        queryBtn.setOnClickListener(new QueryListener());
        deleteBtn.setOnClickListener(new DeleteListener());
    }

    private class CreateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            StuDBHelper dbHelper = new StuDBHelper(MainActivity.this,"stu_db",null,1);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
        }
    }

    private class UpdateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            StuDBHelper dbHelper = new StuDBHelper(MainActivity.this,"stu_db",null,2);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
        }
    }

    private class InsertListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            StuDBHelper dbHelper = new StuDBHelper(MainActivity.this,"stu_db",null,1);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("id",1);
            cv.put("sname","xiaoming");
            cv.put("sage","21");
            cv.put("ssex","man");
            db.insert("stu_table",null,cv);
            db.close();
        }
    }

    private class ModifyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            StuDBHelper dbHelper = new StuDBHelper(MainActivity.this,"stu_db",null,1);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("sage","23");
            String whereClause = "id=?";
            String[] whereArgs = {String.valueOf(1)};
            db.update("stu_table",cv,whereClause,whereArgs);
        }
    }

    private class QueryListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            StuDBHelper dbHelper = new StuDBHelper(MainActivity.this,"stu_db",null,1);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("stu_table",new String[]{"id","sname","sage","ssex"},"id=?",new String[]{"1"},null,null,null);
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex("sname"));
                String age = cursor.getString(cursor.getColumnIndex("sage"));
                String sex = cursor.getString(cursor.getColumnIndex("ssex"));
                System.out.println("query--->"+"姓名"+name+"年龄"+age+"性别"+sex);
            }
            db.close();
        }
    }

    private class DeleteListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            StuDBHelper dbHelper = new StuDBHelper(MainActivity.this,"stu_db",null,1);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String whereClauses = "id=?";
            String[] whereArgs = {String.valueOf(2)};
            db.delete("stu_table",whereClauses,whereArgs);
         }
    }
}
