package kr.ac.baekseok.jobofdisabledapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Calendar;


public class JoinActivity extends Activity{
    EditText joinId,pw1,pw2,joinName,joinBirth,joinPhone,joinHome;
    RadioGroup sexGroup;
    RadioButton sexButton;
    Spinner spinType,spinSevere;
    Button btnRegister;
    Calendar c = Calendar.getInstance();
    int mYear=c.get(Calendar.YEAR);
    int mMonth=c.get(Calendar.MONTH);
    int mDay=c.get(Calendar.DAY_OF_MONTH);
    private TextView mTextViewResult;
    private static String TAG="phpsignup";
    private static String IP_ADDRESS="ftpdot.dothome.co.kr";
    private static String url="http://ftpdot.dothome.co.kr/insert.php";
    private static  String uid,upw,name,birth,phone,home,disable,severe,sex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);
        setTitle("회원가입 화면");
        joinId=(EditText) findViewById(R.id.joinId);
        pw1=(EditText) findViewById(R.id.pw1);
        pw2=(EditText) findViewById(R.id.pw2);
        joinName=(EditText) findViewById(R.id.joinName);
        joinBirth=(EditText) findViewById(R.id.joinBirth);
        sexGroup=(RadioGroup) findViewById(R.id.joinSex);
        sexButton=(RadioButton)findViewById(sexGroup.getCheckedRadioButtonId());
        joinPhone=(EditText) findViewById(R.id.joinPhone);
        joinHome=(EditText) findViewById(R.id.joinHome);
        spinType=(Spinner) findViewById(R.id.spinType);
        spinSevere=(Spinner) findViewById(R.id.spinSevere);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        mTextViewResult=(TextView)findViewById(R.id.textView_result);

        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());



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

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid = joinId.getText().toString();
                upw = pw1.getText().toString();
                name = joinName.getText().toString();
                birth = joinBirth.getText().toString();
                sex = sexButton.getText().toString();
                phone = joinPhone.getText().toString();
                home = joinHome.getText().toString();
                disable = spinType.getSelectedItem().toString();
                severe = spinSevere.getSelectedItem().toString();


                ContentValues values = new ContentValues();
                values.put("uid",uid);
                values.put("upw",upw);
                values.put("name",name);
                values.put("birth",birth);
                values.put("sex",sex);
                values.put("phone",phone);
                values.put("home",home);
                values.put("disable",disable);
                values.put("severe",severe);

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/Insert.php", uid, upw, name, birth, sex, phone, home, disable, severe);


            }
        });

    }//onCreate
    class InsertData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(JoinActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String uid=(String)params[1];
            String upw=(String)params[2];
            String name=(String)params[3];
            String birth=(String)params[4];
            String sex=(String)params[5];
            String phone=(String)params[6];
            String home=(String)params[7];
            String disable=(String)params[8];
            String severe=(String)params[9];

            String serverURL = (String)params[0];
            String postParameters = "uid=" + uid + "&upw=" + upw +"&name=" + name +"&birth=" + birth +"&sex=" + sex +
                    "&phone=" + phone +"&home=" + home +"&disable=" + disable +"&severe=" + severe;

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }


}