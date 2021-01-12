package kr.or.womanup.nambu.hjy.pendingintenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SubActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
        Intent intent = getIntent();
        int num1 = intent.getIntExtra("num1",0);
        int num2 = intent.getIntExtra("num2",0);
        Log.d("Pending",num1+"");
        Log.d("Pending",num2+"");

    }
}