package kr.ac.baekseok.jobofdisabledapplication;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class MenuActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }
}