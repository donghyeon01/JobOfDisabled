package kr.ac.baekseok.jobofdisabledapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtId,edtPw;
    Button btnLogin, btnFind, btnJoin;

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

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),JoinActivity.class);
                startActivity(intent);
            }
        });
    }
}