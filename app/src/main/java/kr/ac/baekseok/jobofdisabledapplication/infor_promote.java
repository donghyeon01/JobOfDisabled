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

public class infor_promote extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor_promote);

        String title, source;
        int num;
        String web;


        Intent intent = getIntent();
        title = intent.getStringExtra("제목");
        source = intent.getStringExtra("출처");
        num = intent.getIntExtra("번호", 0);
        web = intent.getStringExtra("주소");


        TextView promote_title = (TextView) findViewById(R.id.promote_title);
        promote_title.setText(String.valueOf(title));
        TextView promote_source = (TextView) findViewById(R.id.promote_source);
        promote_source.setText(String.valueOf(source));

        ImageView promote_img = (ImageView) findViewById(R.id.promote_img);

        switch (num) {
            case 1:
                promote_img.setImageResource(R.drawable.promote1);
                break;
            case 2:
                promote_img.setImageResource(R.drawable.promote2);
                break;
            case 3:
                promote_img.setImageResource(R.drawable.promote3);
                break;
            case 4:
                promote_img.setImageResource(R.drawable.promote4);
                break;
            case 5:
                promote_img.setImageResource(R.drawable.promote5);
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
}//infor_promote