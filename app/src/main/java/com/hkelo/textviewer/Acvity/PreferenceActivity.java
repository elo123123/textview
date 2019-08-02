package com.hkelo.textviewer.Acvity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.hkelo.textviewer.R;

public class PreferenceActivity extends Activity {

    private Point screenSize;
    private LinearLayout preferenceLayout;
    private ScrollView preferenceLayoutScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        getScreenSize();
        //
        preferenceLayout = findViewById(R.id.preferenceLayout);
        preferenceLayoutScrollView = findViewById(R.id.preferenceLayoutScrollView);

        //각 요소에 대한 레이아웃 디자인 설정
        setLayoutParameter();
    }

    //종료시 설정 유지
    @Override
    protected  void onDestroy() {
        super.onDestroy();
    }

    // 레이아웃 설정
    private void setLayoutParameter(){

    }

    //  환경설정 내용 추가
    private void setPreferenceList() {

    }

    // 스크린 크기 구하기
    private void getScreenSize(){
        screenSize = new Point();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindowManager().getDefaultDisplay().getRealSize(screenSize);
        }
        else {
            //젤리빈 미만 강제종료
            Intent intent = new Intent(PreferenceActivity.this, AlertActivity.class);
            startActivity(intent);
        }
    }
}
