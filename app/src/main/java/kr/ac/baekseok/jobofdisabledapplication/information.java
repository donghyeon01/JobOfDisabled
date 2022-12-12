package kr.ac.baekseok.jobofdisabledapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class information extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);


        ImageButton btnJob = (ImageButton)findViewById(R.id.btnJob);

        LinearLayout btnCon1 = (LinearLayout)findViewById(R.id.btnCon1);
        LinearLayout btnCon2 = (LinearLayout)findViewById(R.id.btnCon2);
        LinearLayout btnCon3 = (LinearLayout)findViewById(R.id.btnCon3);
        LinearLayout btnCon4 = (LinearLayout)findViewById(R.id.btnCon4);
        LinearLayout btnCon5 = (LinearLayout)findViewById(R.id.btnCon5);

        LinearLayout btnPol1 = (LinearLayout)findViewById(R.id.btnPol1);
        LinearLayout btnPol2 = (LinearLayout)findViewById(R.id.btnPol2);
        LinearLayout btnPol3 = (LinearLayout)findViewById(R.id.btnPol3);
        LinearLayout btnPol4 = (LinearLayout)findViewById(R.id.btnPol4);
        LinearLayout btnPol5 = (LinearLayout)findViewById(R.id.btnPol5);

        LinearLayout btnPro1 = (LinearLayout)findViewById(R.id.btnPro1);
        LinearLayout btnPro2 = (LinearLayout)findViewById(R.id.btnPro2);
        LinearLayout btnPro3 = (LinearLayout)findViewById(R.id.btnPro3);
        LinearLayout btnPro4 = (LinearLayout)findViewById(R.id.btnPro4);
        LinearLayout btnPro5 = (LinearLayout)findViewById(R.id.btnPro5);


        btnJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(information.this,job.class);
                startActivity(intent);
            }//onClick()
        }); //btnJob

        btnCon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(information.this,infor_contest.class);

                String title = "2022 공모전";
                String source = "한국장애인고용공단";
                int num = 1;
                String web = "https://www.kead.or.kr/view/info_center/info_center02_01_01.jsp?year=2022";

                intent.putExtra("제목", title);
                intent.putExtra("출처", source);
                intent.putExtra("번호", num);
                intent.putExtra("주소", web);

                startActivity(intent);
            }//onClick()
        }); //btnCon1
        btnCon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(information.this,infor_contest.class);

                String title = "2021 공모전";
                String source = "한국장애인고용공단";
                int num = 2;
                String web = "https://www.kead.or.kr/view/info_center/info_center02_01_01.jsp?year=2021";

                intent.putExtra("제목", title);
                intent.putExtra("출처", source);
                intent.putExtra("번호", num);
                intent.putExtra("주소", web);

                startActivity(intent);
            }//onClick()
        }); //btnCon2
        btnCon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(information.this,infor_contest.class);

                String title = "2020 공모전";
                String source = "한국장애인고용공단";
                int num = 3;
                String web = "https://www.kead.or.kr/view/info_center/info_center02_01_01.jsp?year=2020";

                intent.putExtra("제목", title);
                intent.putExtra("출처", source);
                intent.putExtra("번호", num);
                intent.putExtra("주소", web);

                startActivity(intent);
            }//onClick()
        }); //btnCon3
        btnCon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(information.this,infor_contest.class);

                String title = "2019 공모전";
                String source = "한국장애인고용공단";
                int num = 4;
                String web = "https://www.kead.or.kr/view/info_center/info_center02_01_01.jsp?year=2019";

                intent.putExtra("제목", title);
                intent.putExtra("출처", source);
                intent.putExtra("번호", num);
                intent.putExtra("주소", web);

                startActivity(intent);
            }//onClick()
        }); //btnCon4
        btnCon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(information.this,infor_contest.class);

                String title = "2018 공모전";
                String source = "한국장애인고용공단";
                int num = 5;
                String web = "https://www.kead.or.kr/view/info_center/info_center02_01_01.jsp?year=2018";

                intent.putExtra("제목", title);
                intent.putExtra("출처", source);
                intent.putExtra("번호", num);
                intent.putExtra("주소", web);

                startActivity(intent);
            }//onClick()
        }); //btnCon5


        btnPol1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(information.this,infor_policy.class);

                String title = "정책의 이해";
                String source = "보건복지부";
                int num = 1;
                String web = "http://www.mohw.go.kr/react/policy/index.jsp?PAR_MENU_ID=06&MENU_ID=06370101&PAGE=1&topTitle=%EC%A0%95%EC%B1%85%EC%9D%98%20%EC%9D%B4%ED%95%B4";

                intent.putExtra("제목", title);
                intent.putExtra("출처", source);
                intent.putExtra("번호", num);
                intent.putExtra("주소", web);

                startActivity(intent);
            }//onClick()
        }); //btnPol1
        btnPol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(information.this,infor_policy.class);

                String title = "의료 지원";
                String source = "보건복지부";
                int num = 2;
                String web = "http://www.mohw.go.kr/react/policy/index.jsp?PAR_MENU_ID=06&MENU_ID=06370104&PAGE=4&topTitle=%EC%9D%98%EB%A3%8C%EC%A7%80%EC%9B%90";

                intent.putExtra("제목", title);
                intent.putExtra("출처", source);
                intent.putExtra("번호", num);
                intent.putExtra("주소", web);

                startActivity(intent);
            }//onClick()
        }); //btnPol2
        btnPol3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(information.this,infor_policy.class);

                String title = "공공요금 감면";
                String source = "보건복지부";
                int num = 3;
                String web = "http://www.mohw.go.kr/react/policy/index.jsp?PAR_MENU_ID=06&MENU_ID=06370107&PAGE=7&topTitle=%EA%B3%B5%EA%B3%B5%EC%9A%94%EA%B8%88%20%EA%B0%90%EB%A9%B4";

                intent.putExtra("제목", title);
                intent.putExtra("출처", source);
                intent.putExtra("번호", num);
                intent.putExtra("주소", web);

                startActivity(intent);
            }//onClick()
        }); //btnPol3
        btnPol4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(information.this,infor_policy.class);

                String title = "지역사회복지사업 (재활시설) 및 기타";
                String source = "보건복지부";
                int num = 4;
                String web = "http://www.mohw.go.kr/react/policy/index.jsp?PAR_MENU_ID=06&MENU_ID=06370109&PAGE=9&topTitle=%EC%A7%80%EC%97%AD%EC%82%AC%ED%9A%8C%EB%B3%B5%EC%A7%80%EC%82%AC%EC%97%85%20(%EC%9E%AC%ED%99%9C%EC%8B%9C%EC%84%A4)%20%EB%B0%8F%20%EA%B8%B0%ED%83%80";

                intent.putExtra("제목", title);
                intent.putExtra("출처", source);
                intent.putExtra("번호", num);
                intent.putExtra("주소", web);

                startActivity(intent);
            }//onClick()
        }); //btnPol4
        btnPol5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(information.this,infor_policy.class);

                String title = "장애인등록/장애정도 심사제도";
                String source = "보건복지부";
                int num = 5;
                String web = "http://www.mohw.go.kr/react/policy/index.jsp?PAR_MENU_ID=06&MENU_ID=06370111&PAGE=11&topTitle=%EC%9E%A5%EC%95%A0%EC%9D%B8%EB%93%B1%EB%A1%9D/%EC%9E%A5%EC%95%A0%EC%A0%95%EB%8F%84%20%EC%8B%AC%EC%82%AC%EC%A0%9C%EB%8F%84";

                intent.putExtra("제목", title);
                intent.putExtra("출처", source);
                intent.putExtra("번호", num);
                intent.putExtra("주소", web);

                startActivity(intent);
            }//onClick()
        }); //btnPol5


        btnPro1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(information.this,infor_promote.class);

                String title = "발달장애인 공공후견 지원사업";
                String source = "보건복지부";
                int num = 1;
                String web = "http://www.mohw.go.kr/react/policy/index.jsp?PAR_MENU_ID=06&MENU_ID=06370401&PAGE=1&topTitle=%EB%B0%9C%EB%8B%AC%EC%9E%A5%EC%95%A0%EC%9D%B8%20%EA%B3%B5%EA%B3%B5%ED%9B%84%EA%B2%AC%20%EC%A7%80%EC%9B%90%EC%82%AC%EC%97%85";

                intent.putExtra("제목", title);
                intent.putExtra("출처", source);
                intent.putExtra("번호", num);
                intent.putExtra("주소", web);

                startActivity(intent);
            }//onClick()
        }); //btnPro1
        btnPro2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(information.this,infor_promote.class);

                String title = "언어발달지원 개요";
                String source = "보건복지부";
                int num = 2;
                String web = "http://www.mohw.go.kr/react/policy/index.jsp?PAR_MENU_ID=06&MENU_ID=06370403&PAGE=3&topTitle=%EC%96%B8%EC%96%B4%EB%B0%9C%EB%8B%AC%EC%A7%80%EC%9B%90%20%EA%B0%9C%EC%9A%94";

                intent.putExtra("제목", title);
                intent.putExtra("출처", source);
                intent.putExtra("번호", num);
                intent.putExtra("주소", web);

                startActivity(intent);
            }//onClick()
        }); //btnPro2
        btnPro3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(information.this,infor_promote.class);

                String title = "발달장애인 부모상담지원";
                String source = "보건복지부";
                int num = 3;
                String web = "http://www.mohw.go.kr/react/policy/index.jsp?PAR_MENU_ID=06&MENU_ID=06370405&PAGE=5&topTitle=%EB%B0%9C%EB%8B%AC%EC%9E%A5%EC%95%A0%EC%9D%B8%20%EB%B6%80%EB%AA%A8%EC%83%81%EB%8B%B4%EC%A7%80%EC%9B%90";

                intent.putExtra("제목", title);
                intent.putExtra("출처", source);
                intent.putExtra("번호", num);
                intent.putExtra("주소", web);

                startActivity(intent);
            }//onClick()
        }); //btnPro3
        btnPro4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(information.this,infor_promote.class);

                String title = "장애인활동지원사업";
                String source = "보건복지부";
                int num = 4;
                String web = "http://www.mohw.go.kr/react/policy/index.jsp?PAR_MENU_ID=06&MENU_ID=06370406&PAGE=6&topTitle=%EC%9E%A5%EC%95%A0%EC%9D%B8%ED%99%9C%EB%8F%99%EC%A7%80%EC%9B%90%EC%82%AC%EC%97%85";

                intent.putExtra("제목", title);
                intent.putExtra("출처", source);
                intent.putExtra("번호", num);
                intent.putExtra("주소", web);

                startActivity(intent);
            }//onClick()
        }); //btnPro4
        btnPro5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(information.this,infor_promote.class);

                String title = "서비스이용";
                String source = "보건복지부";
                int num = 5;
                String web = "http://www.mohw.go.kr/react/policy/index.jsp?PAR_MENU_ID=06&MENU_ID=06370408&PAGE=8&topTitle=%EC%84%9C%EB%B9%84%EC%8A%A4%EC%9D%B4%EC%9A%A9";

                intent.putExtra("제목", title);
                intent.putExtra("출처", source);
                intent.putExtra("번호", num);
                intent.putExtra("주소", web);

                startActivity(intent);
            }//onClick()
        }); //btnPro5





    }//onCreate
}//information
