package kr.ac.baekseok.jobofdisabledapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
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
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
    private static String url="http://ftpdot.dothome.co.kr/insert.php";
    private static  String uid,upw,name,birth,phone,home,disable,severe,sex;
    private boolean validate=false;
    private AlertDialog dialog;

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
                if(TextUtils.isEmpty(uid)){
                    Toast.makeText(JoinActivity.this, "ID가 빈칸입니다 입력해주세요", Toast.LENGTH_SHORT).show();
                }else {
                    if(validate){

                    }
                }
                Response.Listener<String>responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            Toast.makeText(JoinActivity.this,response, Toast.LENGTH_SHORT).show();

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder=new AlertDialog.Builder(JoinActivity.this);
                                dialog=builder.setMessage("you can use ID").setPositiveButton("OK",null).create();
                                dialog.show();
                                joinId.setEnabled(false);
                                validate=true;

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                                dialog=builder.setMessage("alreay used ID").setNegativeButton("OK",null).create();
                                dialog.show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest=new ValidateRequest(uid,responseListener);
                RequestQueue queue = Volley.newRequestQueue(JoinActivity.this);
                queue.add(validateRequest);

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
                    Toast.makeText(JoinActivity.this, "빈칸이 있습니다! 빈칸을 채워주세요.", Toast.LENGTH_SHORT).show();
                }else if(!validate){
                    Toast.makeText(JoinActivity.this, "아이디 확인을 해주세요!", Toast.LENGTH_SHORT).show();
                }else if(!checkpw.equals(upw)){
                    Toast.makeText(JoinActivity.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                }else if(phone.length()!=11){
                    Toast.makeText(JoinActivity.this, "핸드폰의 길이가 11글자가 아닙니다!", Toast.LENGTH_SHORT).show();
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

