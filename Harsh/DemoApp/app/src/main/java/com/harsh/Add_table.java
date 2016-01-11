package com.harsh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by HARSH on 1/10/16.
 */
public class Add_table extends Activity  {


    Button student_add, teacher_add, sub_add;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_add);

        student_add = (Button) findViewById(R.id.studentadd);
        teacher_add = (Button) findViewById(R.id.teacheradd);
        sub_add = (Button) findViewById(R.id.subjectadd);
        student_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Add_table.this, Students_details.class);
                startActivity(i);
            }
        });


        teacher_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(Add_table.this, Teacher_details.class);
                startActivity(it);
            }
        });

        sub_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is= new Intent(Add_table.this, Subject_details.class);
                startActivity(is);
            }
        });

    }


    }

