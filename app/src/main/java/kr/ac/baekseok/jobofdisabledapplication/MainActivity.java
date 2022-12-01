package kr.ac.baekseok.jobofdisabledapplication;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText edtId,edtPw;
    Button btnLogin, btnFind, btnJoin;
    private String mJsonString;
    private static String uid,upw;
    private static final String TAG_JSON = "user";
    ArrayList<HashMap<String,String>> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("로그인 화면");

        edtId=(EditText) findViewById(R.id.edtId);
        edtPw=(EditText) findViewById(R.id.edtPw);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnFind=(Button) findViewById(R.id.btnFind);
        btnJoin=(Button) findViewById(R.id.btnJoin);
        uid=edtId.getText().toString();
        upw=edtPw.getText().toString();


        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),JoinActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mArrayList.clear();

                GetData task = new GetData();
                task.execute(edtId.getText().toString(),edtPw.getText().toString());
            }
        });
    }

    private class GetData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this,"잠시만 기다려주세요.",null,true,true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            Log.d(TAG,"response - "+result);

            if(result==null){
                Toast.makeText(MainActivity.this, errorString, Toast.LENGTH_SHORT).show();
            }else{
                mJsonString = result;
                showResult();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String uid=(String) params[0];
            String upw=(String) params[1];

            String serverURL="http://ftpdothome.co.kr/login.php";
            String postParameters = "uid="+uid+"&upw="+upw;

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
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i =0; i<jsonArray.length();i++){
                JSONObject item = jsonArray.getJSONObject(i);

                String uid= item.getString("uid");
                String name = item.getString("name");
                String sex = item.getString("sex");
                String disableName = item.getString("disableName");

                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put("uid",uid);
                hashMap.put("name",name);
                hashMap.put("sex",sex);
                hashMap.put("disableName",disableName);

                mArrayList.add(hashMap);

                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                intent.putExtra("loginID",edtId.getText().toString());
                intent.putExtra("name",name);
                intent.putExtra("disableName",disableName);
                startActivity(intent);
                Toast.makeText(this, "로그인하였습니다", Toast.LENGTH_SHORT).show();
                return;
            }

        }catch (JSONException e){
            Log.d(TAG,"showResult : " + e);
        }
    }
}