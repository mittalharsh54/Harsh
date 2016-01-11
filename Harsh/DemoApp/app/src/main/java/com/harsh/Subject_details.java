package com.harsh;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by HARSH on 1/10/16.
 */
public class Subject_details extends Activity implements OnClickListener
{
	EditText editStudent,editTeacher,editSubject;
	Button btnAdd,btnDelete,btnModify,btnView,btnViewAll,btnShowInfo;
	SQLiteDatabase db;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_details);
		initialize();

        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS subjects(subjectid VARCHAR,name VARCHAR,teachername VARCHAR);");

    }

	private void initialize() {
		editStudent=(EditText)findViewById(R.id.editRoll);
		editTeacher=(EditText)findViewById(R.id.editStudent);
		editSubject=(EditText)findViewById(R.id.editMarks);
		btnAdd=(Button)findViewById(R.id.btnAdd);
		btnDelete=(Button)findViewById(R.id.btnDelete);
		btnModify=(Button)findViewById(R.id.btnModify);
		btnView=(Button)findViewById(R.id.btnView);
		btnViewAll=(Button)findViewById(R.id.btnViewAll);
		btnShowInfo=(Button)findViewById(R.id.btnShowInfo);
		btnAdd.setOnClickListener(this);
		btnDelete.setOnClickListener(this);
		btnModify.setOnClickListener(this);
		btnView.setOnClickListener(this);
		btnViewAll.setOnClickListener(this);
		btnShowInfo.setOnClickListener(this);
	}

	public void onClick(View view)
    {
    	if(view==btnAdd)
    	{
    		if(editStudent.getText().toString().trim().length()==0||
    		   editTeacher.getText().toString().trim().length()==0||
    		   editSubject.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter all values");
    			return;
    		}
    		db.execSQL("INSERT INTO subjects VALUES('"+editStudent.getText()+"','"+editTeacher.getText()+
    				   "','"+editSubject.getText()+"');");
    		showMessage("Success", "Record added");
    		clearText();
    	}
    	if(view==btnDelete)
    	{
    		if(editStudent.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter SubjectId");
    			return;
    		}
    		Cursor c=db.rawQuery("SELECT * FROM subjects WHERE subjectid='"+editStudent.getText()+"'", null);
    		if(c.moveToFirst())
    		{
    			db.execSQL("DELETE FROM subjects WHERE subjectid='"+editStudent.getText()+"'");
    			showMessage("Success", "Record Deleted");
    		}
    		else
    		{
    			showMessage("Error", "Invalid SubjectId");
    		}
    		clearText();
    	}
    	if(view==btnModify)
    	{
    		if(editStudent.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter SubjectId");
    			return;
    		}
    		Cursor c=db.rawQuery("SELECT * FROM subjects WHERE subjectid='"+editStudent.getText()+"'", null);
    		if(c.moveToFirst())
    		{
    			db.execSQL("UPDATE subjects SET name='"+editTeacher.getText()+"',teachername='"+editSubject.getText()+
    					"' WHERE subjectid='"+editStudent.getText()+"'");
    			showMessage("Success", "Record Modified");
    		}
    		else
    		{
    			showMessage("Error", "Invalid SubjectId");
    		}
    		clearText();
    	}
    	if(view==btnView)
    	{
    		if(editStudent.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter SubjectId");
    			return;
    		}
    		Cursor c=db.rawQuery("SELECT * FROM subjects WHERE subjectid='"+editStudent.getText().toString()+"'", null);
    		if(c.moveToFirst())
    		{
    			editTeacher.setText(c.getString(1));
    			editSubject.setText(c.getString(2));
    		}
    		else
    		{
    			showMessage("Error", "Invalid SubjectId");
    			clearText();
    		}
    	}
    	if(view==btnViewAll)
    	{
    		Cursor c=db.rawQuery("SELECT * FROM subjects", null);
    		if(c.getCount()==0)
    		{
    			showMessage("Error", "No records found");
    			return;
    		}
    		StringBuffer buffer=new StringBuffer();
    		while(c.moveToNext())
    		{
    			buffer.append("SubjectId: "+c.getString(0)+"\n");
    			buffer.append("Name: "+c.getString(1)+"\n");
    			buffer.append("TeacherName: "+c.getString(2)+"\n\n");
    		}
    		showMessage("Student Details", buffer.toString());
    	}
    	if(view==btnShowInfo)
    	{
			Intent is= new Intent(this, Add_table.class);
			startActivity(is);
    	}
    }
    public void showMessage(String title,String message)
    {
    	Builder builder=new Builder(this);
    	builder.setCancelable(true);
    	builder.setTitle(title);
    	builder.setMessage(message);
    	builder.show();
	}
    public void clearText()
    {
    	editStudent.setText("");
    	editTeacher.setText("");
    	editSubject.setText("");
    	editStudent.requestFocus();
    }
}