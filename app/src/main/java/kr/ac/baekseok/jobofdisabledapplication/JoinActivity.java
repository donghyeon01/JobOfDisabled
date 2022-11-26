package kr.ac.baekseok.jobofdisabledapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;

public class JoinActivity extends Activity {

    EditText joinId,pw1,pw2,joinName,joinBirth,joinPhone,joinHome;
    RadioGroup joinSex;
    Spinner spinType,spinSevere;
    Button btnRegister;
    Calendar c = Calendar.getInstance();
    int mYear=c.get(Calendar.YEAR);
    int mMonth=c.get(Calendar.MONTH);
    int mDay=c.get(Calendar.DAY_OF_MONTH);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);
        setTitle("회원가입 화면");
        joinId=(EditText) findViewById(R.id.joinId);
        pw1=(EditText) findViewById(R.id.pw1);
        pw2=(EditText) findViewById(R.id.pw2);
        joinName=(EditText) findViewById(R.id.joinName);
        joinBirth=(EditText) findViewById(R.id.joinBirth);
        joinSex=(RadioGroup) findViewById(R.id.joinSex);
        joinPhone=(EditText) findViewById(R.id.joinPhone);
        joinHome=(EditText) findViewById(R.id.joinHome);
        spinType=(Spinner) findViewById(R.id.spinType);
        spinSevere=(Spinner) findViewById(R.id.spinSevere);
        btnRegister=(Button)findViewById(R.id.btnRegister);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                joinBirth.setText(year+"-"+(month+1)+"-"+dayOfMonth);
            }
        },mYear,mMonth,mDay);

        joinBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(joinBirth.isClickable()){
                    datePickerDialog.show();
                }
            }
        });
    }
}
