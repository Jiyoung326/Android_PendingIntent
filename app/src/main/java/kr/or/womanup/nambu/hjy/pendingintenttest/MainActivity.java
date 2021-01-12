package kr.or.womanup.nambu.hjy.pendingintenttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button1, button2, button3;
    NotificationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override  //버튼 누르면 SubActivity1 열림
            public void onClick(View v) {
                NotificationCompat.Builder builder = getNotificationBuilder("ch01","총무부");
                builder.setSmallIcon(android.R.drawable.btn_star);
                builder.setContentTitle("이것은 팬딩인텐트 테스트입니다");
                builder.setContentText("이것을 터치하면 SubActivity1이 열립니다.");

                Intent intent = new Intent(getApplicationContext(),SubActivity1.class);
                PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(),
                        101,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pIntent);

                Notification noti = builder.build();
                manager.notify(10,noti);

            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼 누르면 SubActivity2 열림
                NotificationCompat.Builder builder = getNotificationBuilder("ch02","하하하");
                builder.setSmallIcon(android.R.drawable.btn_plus);
                builder.setContentTitle("from 뚱땅");
                builder.setContentText("뚱땅이가 다가옵니다. 눌러주세요");

                Intent intent = new Intent(getApplicationContext(),SubActivity2.class);
                //데이터 넘길 경우 Intent에 담기
                intent.putExtra("num1",10);
                intent.putExtra("num2",20);
                PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(),
                        201,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pIntent);

                Notification noti = builder.build();
                manager.notify(20,noti);
            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = getNotificationBuilder("ch03","영업부");
                builder.setSmallIcon(android.R.drawable.btn_star);
                builder.setContentTitle("이것은 팬딩인텐트 테스트입니다");
                builder.setContentText("이것을 터치하면 SubActivity1이 열립니다.");
                //builder.setAutoCancel(false); //알림 확인해도 떠있음

                ///기존 PendingIntent 그대로
                Intent intent = new Intent(getApplicationContext(),SubActivity1.class);
                PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(),
                        101,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pIntent);
                
                //SubActivity2가 열리는 액션 추가하기, 데이터도 담기 가능
                //intent ->pendingintent ->action->build.add
                Intent actIntent = new Intent(getApplicationContext(),SubActivity2.class);
                PendingIntent actPIntent = PendingIntent.getActivity(getApplicationContext(),
                        101,actIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Action.Builder actionBuilder
                        = new NotificationCompat.Action.Builder(android.R.drawable.btn_star,"액션입니다",actPIntent);
                NotificationCompat.Action action = actionBuilder.build();
                builder.addAction(action);

                Intent actIntent1 = new Intent(getApplicationContext(),SubActivity2.class);
                PendingIntent actPIntent1 = PendingIntent.getActivity(getApplicationContext(),
                        101,actIntent1,PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Action.Builder actionBuilder1
                        = new NotificationCompat.Action.Builder(android.R.drawable.btn_star,"액션2입니다",actPIntent1);
                NotificationCompat.Action action1 = actionBuilder1.build();
                builder.addAction(action1);

                Notification noti = builder.build();
                manager.notify(60,noti);
            }
        });
    }

    public NotificationCompat.Builder getNotificationBuilder(String chId, String chName){
        NotificationCompat.Builder builder;
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
            NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(chId,chName,NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.enableVibration(true);
            manager.createNotificationChannel(channel);
            builder = new NotificationCompat.Builder(this,chId);
        }else{
            builder = new NotificationCompat.Builder(this);
        }
        return builder;
    }
}