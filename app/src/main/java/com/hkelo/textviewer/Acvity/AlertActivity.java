package com.hkelo.textviewer.Acvity;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.hkelo.textviewer.R;

public class AlertActivity extends Activity {

    private Point screenSize;
    private LinearLayout alertLayout;
    private LinearLayout alertLayoutAlert;
    private TextView alertLayoutTextView;
    private Button alertLayoutConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        MainActivity mainActivity = new MainActivity();

        //각 요소 정의
        alertLayout = findViewById(R.id.alertLayout);
        alertLayoutAlert = findViewById(R.id.alertLayoutAlert);
        alertLayoutTextView = findViewById(R.id.alertLayoutTextView);
        alertLayoutConfirm = findViewById(R.id.alertLayoutConfirm);
    }

    @Override
    protected void onStart() {
        super.onStart();

        alertLayoutConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pid = android.os.Process.myPid();
                android.os.Process.killProcess(pid);
            }
        });
    }

}
