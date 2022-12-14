package kr.ac.baekseok.jobofdisabledapplication;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
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
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class JoinActivity extends Activity{
    EditText joinId,pw1,pw2,joinName,joinBirth,joinPhone,joinHome;
    RadioGroup sexGroup;
    RadioButton sexButton;
    Spinner spinType,spinSevere;
    Button btnRegister,btnCheckId;
    Calendar c = Calendar.getInstance();
    int mYear=c.get(Calendar.YEAR);
    int mMonth=c.get(Calendar.MONTH);
    int mDay=c.get(Calendar.DAY_OF_MONTH);
    private TextView mTextViewResult;
    private static String TAG="phpsignup";
    private static String IP_ADDRESS="ftpdot.dothome.co.kr";
    private static  String uid,upw,name,birth,phone,home,disable,severe,sex;
    private boolean validate=false;
    private String mJsonString;
    ArrayList<HashMap<String,String>> mArrayList = new ArrayList<>();
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);
        setTitle("???????????? ??????");
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
        btnCheckId=(Button)findViewById(R.id.btnCheckId);

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

        btnCheckId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid = joinId.getText().toString();
                Response.Listener<String> responseListener = null;
                if (TextUtils.isEmpty(uid)) {
                    Toast.makeText(JoinActivity.this, "ID??? ??????????????? ??????????????????", Toast.LENGTH_SHORT).show();
                } else {
                    GetData vali = new GetData();
                    vali.execute(uid);
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
                String checkpw = pw2.getText().toString();

                if (TextUtils.isEmpty(uid)||TextUtils.isEmpty(upw)||TextUtils.isEmpty(name)||TextUtils.isEmpty(birth)||TextUtils.isEmpty(sex)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(home)){
                    Toast.makeText(JoinActivity.this, "????????? ????????????! ????????? ???????????????.", Toast.LENGTH_SHORT).show();
                }else if(!validate){
                    Toast.makeText(JoinActivity.this, "????????? ??????????????? ????????????!", Toast.LENGTH_SHORT).show();
                }else if(!checkpw.equals(upw)){
                    Toast.makeText(JoinActivity.this, "??????????????? ???????????? ????????????!", Toast.LENGTH_SHORT).show();
                }else if(phone.length()!=11){
                    Toast.makeText(JoinActivity.this, "???????????? ????????? 11????????? ????????????!", Toast.LENGTH_SHORT).show();
                }else {

                    ContentValues values = new ContentValues();
                    values.put("uid", uid);
                    values.put("upw", upw);
                    values.put("name", name);
                    values.put("birth", birth);
                    values.put("sex", sex);
                    values.put("phone", phone);
                    values.put("home", home);
                    values.put("disable", disable);
                    values.put("severe", severe);

                    InsertData task = new InsertData();
                    task.execute("http://" + IP_ADDRESS + "/Insert.php", uid, upw, name, birth, sex, phone, home, disable, severe);
                }//else
            }
        });

    }//onCreate

    private class GetData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(JoinActivity.this,"????????? ??????????????????.",null,true,true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            Log.d(TAG,"response - "+result);

            if(result==null){
                Toast.makeText(JoinActivity.this, errorString, Toast.LENGTH_SHORT).show();
            }else{
                mJsonString = result;
                showResult();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String uid=(String) params[0];

            String serverURL="http://ftpdot.dothome.co.kr/checkID.php";
            String postParameters = "uid="+uid;

            try {
                URL url=new URL(serverURL);
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
                Log.d(TAG,"POST response code - "+responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode==HttpURLConnection.HTTP_OK){
                    inputStream=httpURLConnection.getInputStream();
                }else{
                    inputStream=httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine())!=null){
                    sb.append(line);
                }

                bufferedReader.close();
                return sb.toString();
            }catch (Exception e){
                Log.d(TAG,"InsertData : Error ",e);
                return new String("Error: "+e.getMessage());
            }
        }
    }
    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);

            boolean success = jsonObject.getBoolean("success");
            if(success==false){
                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                dialog = builder.setMessage("?????? ?????? ?????? ??????????????????.").setNegativeButton("OK", null).create();
                dialog.show();
                validate = false;//????????????
                return;
            }else if(success==true){
                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                dialog = builder.setMessage("????????? ????????? ???????????????.").setPositiveButton("OK", null).create();
                dialog.show();
                joinId.setEnabled(false);//??????????????? ?????? ??? ????????? ???
                validate = true;//????????????
                return;
            }


            return;

        }catch (JSONException e){
            Log.d(TAG,"showResult : " + e);
        }
    }


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

