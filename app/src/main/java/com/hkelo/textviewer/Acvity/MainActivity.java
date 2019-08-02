package com.hkelo.textviewer.Acvity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.hkelo.textviewer.R;
import com.hkelo.textviewer.R.*;
import com.hkelo.textviewer.System.BackPressActionHandler;
import com.hkelo.textviewer.System.Init;

public class MainActivity extends Activity {

    private BackPressActionHandler backPressClaseHandler;

    private int screenWidth;
    private int screenHeight;
    private LinearLayout mainLayout;
    private LinearLayout mainLayoutTop;
    private TextView mainLayoutTopPageView;
    private TextView mainLayoutTopTitleView;
    private Button  mainLayoutTopPreferenceButton;
    private TextView mainLayoutTextView;

    //앱 실행시
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backPressClaseHandler = new BackPressActionHandler(this);

        //요소 정의
        mainLayout = findViewById(R.id.mainLayout);
        mainLayoutTop =findViewById(R.id.mainLayoutTop);
        mainLayoutTopPageView = findViewById(R.id.mainLayoutTopPageView);
        mainLayoutTopTitleView = findViewById(R.id.mainLayoutTopTitleView);
        mainLayoutTopPreferenceButton = findViewById(R.id.mainLayoutTopPreferenceButton);
        mainLayoutTextView = findViewById(R.id.mainLayoutTextView);

        //각 객체에 대한 레이아웃 파라미터 설정
        setLayoutParameter();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View content = getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        screenHeight = content.getHeight();
        screenWidth = content.getWidth();

        setLayoutParameter();
    }

    //
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onStart() {
        super.onStart();
        //환경설정 버튼 액션
        mainLayoutTopPreferenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PreferenceActivity.class);
                startActivity(intent);
                Log.i("ASDF", "OPEN PREFERENCE MENU");
            }
        });

        //타이틀 텍스트뷰 액션
        mainLayoutTopTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //테스트 코드
                Log.i("ASDF", "OPEN FILE SYSTEM");
            }
        });

        //컨텐츠 텍스트뷰 액션
        mainLayoutTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if((mainLayoutTextView.getId() == view.getId()) && motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if((screenHeight/2) > motionEvent.getY()){
                        mainLayoutTextView.setText("UP");
                        Log.i("ASDF", "UP");
                    }
                    else {
                        mainLayoutTextView.setText("DOWN");
                        Log.i("ASDF", "DOWN");
                    }
                }
                return false;
            }
        });

    }

    //뒤로가기 버튼 호출시
    @Override
    public void onBackPressed() {
        backPressClaseHandler.onBackPressed();
    }

    //종료시
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //각 레이아웃에 대한 디자인
    @SuppressLint("ResourceAsColor")
    private void setLayoutParameter() {

        // 각 레이아웃에 대한 설정값
        int titleLayoutWidth = (int) ((screenWidth) * 0.6);;
        int extLayoutWidth = (int) ((screenWidth ) * 0.2);
        int titleLayoutHeight = (int) ((screenHeight) * 0.05);
        int textViewHeight = (int)((screenHeight) * 0.95);

        int titleBarDefault = ContextCompat.getColor(this, color.titleBarDefault);
        int textColorDefault = ContextCompat.getColor(this, color.textColorDefault);
        int textBackgroundColorDefault = ContextCompat.getColor(this, color.textBackgroundColorDefault);

        //컨텐츠 텍스트 뷰
        mainLayoutTextView.setWidth(screenWidth);
        mainLayoutTextView.setHeight(textViewHeight);
        mainLayoutTextView.setPadding(Init.defaultPadding, Init.defaultPadding, Init.defaultPadding, Init.defaultPadding);
        mainLayoutTextView.setText("테스트 텍스트");
        mainLayoutTextView.setTextSize(Init.mediumContentsFontSize);
        mainLayoutTextView.setTextColor(textColorDefault);
        mainLayoutTextView.setBackgroundColor(textBackgroundColorDefault);

        //타이틀 레이아웃
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth, titleLayoutHeight);
        mainLayoutTop.setLayoutParams(params);
        mainLayoutTop.setBackgroundColor(titleBarDefault);

        //페이지 텍스트 뷰
        mainLayoutTopPageView.setText("123" + "/" + "1");       // 코드상 페이지가 변동될때마다 카운트
        mainLayoutTopPageView.setWidth(extLayoutWidth);
        mainLayoutTopPageView.setHeight(titleLayoutHeight);
        mainLayoutTopPageView.setTextColor(textColorDefault);
        mainLayoutTopPageView.setTextSize(Init.mediumFontSize);

        //타이틀 텍스트 뷰
        mainLayoutTopTitleView.setText(screenWidth + ", " + screenHeight);
        mainLayoutTopTitleView.setWidth(titleLayoutWidth);
        mainLayoutTopTitleView.setHeight(titleLayoutHeight);
        mainLayoutTopTitleView.setTextColor(textColorDefault);
        mainLayoutTopTitleView.setTextSize(Init.largeFontSize);

        //환경설정 버튼
        mainLayoutTopPreferenceButton.setText(string.preference);
        mainLayoutTopPreferenceButton.setWidth(extLayoutWidth);
        mainLayoutTopPreferenceButton.setHeight(titleLayoutHeight);
        mainLayoutTopPreferenceButton.setBackgroundColor(titleBarDefault);
        mainLayoutTopPreferenceButton.setTextColor(textColorDefault);
        mainLayoutTopPreferenceButton.setTextSize(Init.smallFontSize);

    }

}
