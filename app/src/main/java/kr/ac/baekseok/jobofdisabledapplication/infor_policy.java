package kr.ac.baekseok.jobofdisabledapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class infor_policy extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor_policy);

        String title, source;
        int num;
        String web;


        Intent intent = getIntent();
        title = intent.getStringExtra("제목");
        source = intent.getStringExtra("출처");
        num = intent.getIntExtra("번호", 0);
        web = intent.getStringExtra("주소");


        TextView policy_title = (TextView) findViewById(R.id.policy_title);
        policy_title.setText(String.valueOf(title));
        TextView policy_source = (TextView) findViewById(R.id.policy_source);
        policy_source.setText(String.valueOf(source));

        ImageView policy_img = (ImageView) findViewById(R.id.policy_img);

        switch (num) {
            case 1:
                policy_img.setImageResource(R.drawable.policy1);
                break;
            case 2:
                policy_img.setImageResource(R.drawable.policy2);
                break;
            case 3:
                policy_img.setImageResource(R.drawable.policy3);
                break;
            case 4:
                policy_img.setImageResource(R.drawable.policy4);
                break;
            case 5:
                policy_img.setImageResource(R.drawable.policy5);
                break;
        }

        Button btn_web = (Button)findViewById(R.id.btn_web);
        btn_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(web);
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });




    }//onCreate
}//infor_policy