package com.example.towhid.sqlitecrud;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText firstName,lastName;
    TextView textView;
    DbControl control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName=(EditText)findViewById(R.id.first_name_id);
        lastName=(EditText)findViewById(R.id.last_name_id);
        textView=(TextView)findViewById(R.id.text_view);
        control=new DbControl(this,"Test.db",null,1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        control.list_all_student(textView);
    }


    public void btnclick(View view) {
        switch (view.getId()){
            case R.id.button_add:
                try {
                    control.insertStudent(firstName.getText().toString(),lastName.getText().toString());
                }catch (SQLiteException e){
                    Toast.makeText(MainActivity.this, "Already exists", Toast.LENGTH_SHORT).show();
                }
                control.list_all_student(textView);
                firstName.setText("");
                lastName.setText("");
                break;
            case R.id.button_delete:
                control.deleteStudent(firstName.getText().toString());
                control.list_all_student(textView);
                firstName.setText("");
                lastName.setText("");
                break;
            case R.id.button_update:
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Enter New FirstName");
                final EditText new_firstName=new EditText(this);
                dialog.setView(new_firstName);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        control.update_student(firstName.getText().toString(),new_firstName.getText().toString());
                    }
                });
                dialog.show();
                control.list_all_student(textView);
                firstName.setText("");
                lastName.setText("");
                break;
            case R.id.button_list:
                control.list_all_student(textView);
                firstName.setText("");
                lastName.setText("");
                break;
        }
    }
}
