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

public class infor_contest extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor_contest);

        String title, source;
        int num;
        String web;


        Intent intent = getIntent();
        title = intent.getStringExtra("제목");
        source = intent.getStringExtra("출처");
        num = intent.getIntExtra("번호", 0);
        web = intent.getStringExtra("주소");


        TextView contest_title = (TextView) findViewById(R.id.contest_title);
        contest_title.setText(String.valueOf(title));
        TextView contest_source = (TextView) findViewById(R.id.contest_source);
        contest_source.setText(String.valueOf(source));

        ImageView contest_img = (ImageView) findViewById(R.id.contest_img);

        switch (num) {
            case 1:
                contest_img.setImageResource(R.drawable.contest1);
                break;
            case 2:
                contest_img.setImageResource(R.drawable.contest2);
                break;
            case 3:
                contest_img.setImageResource(R.drawable.contest3);
                break;
            case 4:
                contest_img.setImageResource(R.drawable.contest4);
                break;
            case 5:
                contest_img.setImageResource(R.drawable.contest5);
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
}//infor_contest