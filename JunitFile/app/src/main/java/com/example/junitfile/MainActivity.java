package com.example.junitfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Context;

import android.content.pm.PackageManager;

import android.os.Bundle;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.changIO.MyObjectOutputStream;
import com.example.fileoper.Fileoper;
import com.example.vo.Employ;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RadioGroup radiogroup1;
    private Employ emp;
    private static final String[] m={"1","2","3","4","5","6","7","8","9"};
    private EditText name,pro;
    private Button commit,display,cr;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private TextView show;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyStoragePermissions(this);

        spinner = (Spinner) findViewById(R.id.Spinner01);
        name = (EditText) findViewById(R.id.name);
        pro = (EditText) findViewById(R.id.pro);
        commit  = (Button) findViewById(R.id.button1);
        radiogroup1 = (RadioGroup) findViewById(R.id.sex_rad);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setVisibility(View.VISIBLE);
        spinner.setSelection(0);
        display  = (Button) findViewById(R.id.button2);
        cr = (Button)findViewById(R.id.button3);

        show = (TextView) findViewById(R.id.show);

        verifyStoragePermissions(this);

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emp = new Employ();
                RadioButton temp_sex = findViewById(radiogroup1.getCheckedRadioButtonId());
                emp.setName(name.getText().toString());
                emp.setProfess(pro.getText().toString());
                emp.setSex(temp_sex.getText().toString());
                emp.setAge(Integer.parseInt(spinner.getSelectedItem().toString()));
                if(Fileoper.WriteSysFile(getApplicationContext(),emp)) {
                    Toast.makeText(MainActivity.this, "成功加入", Toast.LENGTH_SHORT).show();
                }
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "";
                ArrayList<Employ> temp = new ArrayList<Employ>();
                temp = Fileoper.ReadSysFile(getApplicationContext());
                for (Employ e: temp){
                    result+=e.toString();
                    result+="\n";
                }
                show.setText(result);
            }
        });
        cr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Fileoper.clear(getApplication());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void verifyStoragePermissions(MainActivity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                System.out.println("无权利");
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}