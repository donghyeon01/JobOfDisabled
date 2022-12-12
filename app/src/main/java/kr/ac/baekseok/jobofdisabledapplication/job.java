package kr.ac.baekseok.jobofdisabledapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class job extends AppCompatActivity {
    int MaxData=957;
    int DataName=17;
    String[][]data=new String[MaxData+1][DataName+1];
    Date mDate;
    long mNow;
    int MaxCnt=0;
    int showCnt=0;
    int NP=1;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
    Button prev,next,nowPage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job);

        ImageButton btnInform = (ImageButton) findViewById(R.id.btnInform);

        btnInform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(job.this,information.class);
                startActivity(intent);
            }//onClick()
        }); //btnJob

        try {
            readCSV();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ListView listView = (ListView) findViewById(R.id.listview);
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> item= new HashMap<String, String>();
        for(int i=showCnt;i<showCnt+5;i++){
            item = new HashMap<String, String> ();
            item.put("item1",data[i][4]);
            item.put("item2",data[i][2]+"\t"+data[i][3]);
            list.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,list, android.R.layout.simple_list_item_2,new String[]{"item1","item2"},new int[]{android.R.id.text1,android.R.id.text2});
        listView.setAdapter(adapter);

        prev=(Button) findViewById(R.id.prev);
        next=(Button) findViewById(R.id.next);
        nowPage=(Button)findViewById(R.id.nowPage);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showCnt==0){
                    Toast.makeText(job.this, "최신 페이지 입니다!", Toast.LENGTH_SHORT).show();
                }else{
                    showCnt-=5;
                    list.clear();
                    HashMap<String,String> item= new HashMap<String, String>();
                    for(int i=showCnt;i<showCnt+5;i++){
                        item = new HashMap<String, String> ();
                        item.put("item1",data[i][4]);
                        item.put("item2",data[i][2]+"\t"+data[i][3]);
                        list.add(item);
                    }
                    listView.setAdapter(adapter);
                    NP-=1;
                    nowPage.setText(String.valueOf(NP));
                }

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showCnt==MaxCnt){
                    Toast.makeText(job.this, "마지막 페이지 입니다!", Toast.LENGTH_SHORT).show();
                }else{
                    showCnt+=5;
                    list.clear();
                    HashMap<String,String> item= new HashMap<String, String>();
                    for(int i=showCnt;i<showCnt+5;i++){
                        item = new HashMap<String, String> ();
                        item.put("item1",data[i][4]);
                        item.put("item2",data[i][2]+"\t"+data[i][3]);
                        list.add(item);
                    }
                    listView.setAdapter(adapter);
                    NP+=1;
                    nowPage.setText(String.valueOf(NP));
                }

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(job.this);
                dlg.setTitle(data[i][3]+"의 구인 공고");
                dlg.setMessage("모집직종 : "+data[i][4]+"\n모집기간 : "+data[i][2]+"\n고용형태 : "+data[i][5]+", "+data[i][6]+" : "+data[i][7]+
                        "\n입사형태 : "+data[i][8]+"\n요구경력 : "+data[i][9]+"\n요구학력 : "+data[i][10]+", 전공계열 : "+data[i][11]+
                        "\n요구 자격증 : "+data[i][12]+"\n사업장 주소 : "+data[i][13]+"\n기업형태 : "+data[i][14]+"\n담당기관 : "+data[i][15]+
                        "\n연락처 : "+data[i][17]);
                dlg.show();
            }
        });
    }


    private Date getTime(){
        mNow=System.currentTimeMillis();
        mDate=new Date(mNow);
        return mDate;
    }
    void readCSV() throws IOException, CsvValidationException, ParseException {
        AssetManager assetManager = this.getAssets();
        InputStream inputStream = assetManager.open("JobList.CSV");
        CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
        String[] nextLine;
        int num=0;
        String split;
        Date arrayDate;
        nextLine = reader.readNext();
        while((nextLine = reader.readNext())!=null){
            split=nextLine[2].substring(nextLine[2].indexOf("~")+1);
            arrayDate=mFormat.parse(split);
            long diff=arrayDate.getTime()-getTime().getTime();
            if(diff>=0){
                for(int j=0;j<18;j++){
                    data[num][j]=nextLine[j];
                }
                num+=1;
            }else{
            }
        }
        MaxCnt=num;
    }

}
