package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_NOTIFICATION_ID = 1;
    private static Context context;


    public void showCustomNotificationOnClick(View view) {
    }

    public void showNotificationOnClick(View view) {
        String contextTitle="Notification from Lab7 App";
        String contentText="I am notification";
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setContentTitle(contextTitle)
                        .setContentText(contentText);

        NotificationManager mNotificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(MY_NOTIFICATION_ID, mBuilder.build());

    }

    public void showDialogOnClick(View view) {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "dialog");
    }

    public void showToastOnClick(View view) {
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.AXIS_PULL_BEFORE,10,100);

        View toastView = getLayoutInflater().inflate(R.layout.toast_view, null);
        TextView text = toastView.findViewById(R.id.toastTextView);
        ImageView imageView = toastView.findViewById(R.id.imageView);
        text.setText("wow much customization!");
        imageView.setImageResource(R.drawable.toast);
        toast.setView(toastView);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.context = getApplicationContext();
    }
    public static Context getAppContext() {
        return MainActivity.context;
    }
}
