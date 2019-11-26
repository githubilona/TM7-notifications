package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_NOTIFICATION_ID = 1;
    private static Context context;


    private Intent mNotificationIntent;
    private PendingIntent mContentIntent;

    private Uri soundURI = Uri.parse("android.resource://com.example.lab7/" + R.raw.woof);
    private Uri notificationImageUri = Uri.parse("android.resource://com.example.lab7/" + R.drawable.dog);
    private long[] vibrationPattern = {0, 200, 200, 300};

    //Tworzenie nowego widoku do powiadomienia
    RemoteViews mContentView = new RemoteViews("com.example.lab7", R.layout.custom_notification);


    public void showCustomNotificationOnClick(View view) {
        int mNotificationCount = 0;
        String contentText = "Content text";
        // String mNotificationCount="";
        mContentView.setTextViewText(R.id.customNotificationText, contentText + "(" + ++mNotificationCount + ")");
      //  mContentView.setImageViewUri(R.id.notificationImageView, notificationImageUri);

        mContentView.setImageViewResource(R.id.notificationImageView,R.drawable.toast);
        //Tworzymy powiadomienie
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext());
        String ticketText = "Lab7: New notification";
        notificationBuilder.setTicker(ticketText);//ticker tekst, powyzej android 5.0 nie działa
        notificationBuilder.setSmallIcon(android.R.drawable.stat_sys_warning);//mala ikona na pasku powiadomien
        notificationBuilder.setAutoCancel(true);//automatyczne usunięcie powiadomienia jak użytkownik wyciągnie szuflade
        notificationBuilder.setContentIntent(mContentIntent);//akcja gdy klikniemyw szufladzie powiadomienie
        notificationBuilder.setSound(soundURI);//dzwięk powiadomienia
        notificationBuilder.setVibrate(vibrationPattern);//patern wibracji
        notificationBuilder.setContent(mContentView);//wygląd


        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(mNotificationCount, notificationBuilder.build());

    }

    public void showNotificationOnClick(View view) {
        String contextTitle = "Notification from Lab7 App";
        String contentText = "I am notification";
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setContentTitle(contextTitle)
                        .setWhen(System.currentTimeMillis()).setShowWhen(true)
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
        toast.setGravity(Gravity.AXIS_PULL_BEFORE, 10, 100);

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

        //zachowanie uruchamiające to co chcemy
        mNotificationIntent = new Intent(getApplicationContext(), IntentActivity.class);
        mNotificationIntent.putExtra("from" , "Notification");
        //PendngIntent umożliwia nam wywołanie wcześniej zadeklarowanego zachowania z wszystkimi uprawnieniami jakie by miało gdyby zostało wywołane przez
        // naszą aplikacje
        mContentIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                mNotificationIntent, 0);


    }

    public static Context getAppContext() {
        return MainActivity.context;
    }
}
