package com.tencent.weardemo;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;


public class MyActivity extends Activity {

    private Button normalBtn;
    private Button mapBtn;
    private Button extendBtn;
    private Button bigViewBtn;
    private Button hideBtn;
    private Button addBtn;
    private Button groupBtn;
    private Button summaryBtn;
    private Button voiceBtn;
    private NotificationManagerCompat mNotificationManager;
    String GROUP_KEY_SMS = "group_key_sms";
    String GROUP_KEY_SUM = "group_key_sum";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mNotificationManager = NotificationManagerCompat.from(this);
        initUI();
    }

    public void initUI(){

        normalBtn = (Button)findViewById(R.id.button1);
        mapBtn = (Button)findViewById(R.id.button2);
        extendBtn = (Button)findViewById(R.id.button3);
        bigViewBtn = (Button)findViewById(R.id.button4);
        hideBtn = (Button)findViewById(R.id.button5);
        addBtn = (Button)findViewById(R.id.button6);
        groupBtn = (Button)findViewById(R.id.button7);
        summaryBtn = (Button)findViewById(R.id.button8);
        voiceBtn = (Button)findViewById(R.id.button9);

        normalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normalBtnClick();
            }
        });
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapBtnClick();
            }
        });
        extendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extendBtnClick();
            }
        });
        bigViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bigViewBtnClick();
            }
        });
        hideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideBtnClick();
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBtnClick();
            }
        });
        groupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupBtnClick();
            }
        });
        summaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                summaryBtnClick();
            }
        });
        voiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voiceBtnClick();
            }
        });
    }

    public void normalBtnClick(){
        int notificationId = 1;
        // Build intent for notification content
        Intent viewIntent = new Intent(this, ViewEventActivity.class);
        //viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.wear_icon)
                        .setContentTitle("普通Title")
                        .setContentText("普通Content")
                        .setContentIntent(viewPendingIntent);

        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        // Build the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    public void mapBtnClick(){
        int notificationId = 2;

        // Build intent for notification content
        Intent viewIntent = new Intent(this, ViewEventActivity.class);
        //viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

        // Build an intent for an action to view a map
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:39.940409,116.355257?q=西直门");
        mapIntent.setData(geoUri);
        PendingIntent mapPendingIntent =
                PendingIntent.getActivity(this, 0, mapIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.wear_map)
                        .setContentTitle("地图Title")
                        .setContentText("地图Content")
                        .setContentIntent(viewPendingIntent)
                        .addAction(R.drawable.ic_map,
                                getString(R.string.map), mapPendingIntent);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    public void extendBtnClick(){
        int notificationId = 3;
        // Create an intent for the reply action
        Intent actionIntent = new Intent(this, ViewEventActivity.class);
        PendingIntent actionPendingIntent =
                PendingIntent.getActivity(this, 0, actionIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        // Create the action
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_action,
                        getString(R.string.label), actionPendingIntent)
                        .build();

        // Build an intent for an action to view a map
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:39.940409,116.355257?q=西直门");
        mapIntent.setData(geoUri);
        PendingIntent mapPendingIntent =
                PendingIntent.getActivity(this, 0, mapIntent, 0);

        // Build the notification and add the action via WearableExtender
        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_message)
                        .setContentTitle("短信Title")
                        .setContentText("短信Content\n今晚几点回家吃饭？")
                        .setContentIntent(actionPendingIntent)
                        .extend(new NotificationCompat.WearableExtender().addAction(action))
                        .addAction(R.drawable.ic_map, getString(R.string.map), mapPendingIntent)
                        .build();

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notification);
    }

    public void bigViewBtnClick(){
        // Specify the 'big view' content to display the long
        // event description that may not fit the normal content text.
        NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        bigStyle.bigText("今晚几点回家吃饭？加班吗？等等等等");

        int notificationId = 4;
        // Create an intent for the reply action
        Intent actionIntent = new Intent(this, ViewEventActivity.class);
        PendingIntent actionPendingIntent =
                PendingIntent.getActivity(this, 0, actionIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        // Create the action
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_action,
                        getString(R.string.label), actionPendingIntent)
                        .build();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_message);

        // Build the notification and add the action via WearableExtender
        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_message)
                        .setLargeIcon(bitmap)
                        .setContentTitle("短信Title")
                        .setContentText("短信Content")
                        .setContentIntent(actionPendingIntent)
                        .extend(new NotificationCompat.WearableExtender().addAction(action))
                        .setStyle(bigStyle)
                        .build();

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notification);
    }

    public void hideBtnClick(){
        // Create a WearableExtender to add functionality for wearables
        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true);
        // Specify the 'big view' content to display the long
        // event description that may not fit the normal content text.
        NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        bigStyle.bigText("今晚几点回家吃饭？加班吗？等等等等");

        int notificationId = 5;
        // Create an intent for the reply action
        Intent actionIntent = new Intent(this, ViewEventActivity.class);
        PendingIntent actionPendingIntent =
                PendingIntent.getActivity(this, 0, actionIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        // Create the action
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_action,
                        getString(R.string.label), actionPendingIntent)
                        .build();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_message);

        // Build the notification and add the action via WearableExtender
        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_message)
                        .setLargeIcon(bitmap)
                        .setContentTitle("短信Title")
                        .setContentText("短信Content")
                        .setContentIntent(actionPendingIntent)
                        .extend(wearableExtender)
                        .setStyle(bigStyle)
                        .build();

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notification);
    }

    public void addBtnClick(){
        int notificationId = 6;
        // Build intent for notification content
        Intent viewIntent = new Intent(this, ViewEventActivity.class);
        //viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

        // Create builder for the main notification
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_message)
                        .setContentTitle("Page 1")
                        .setContentText("Short message")
                        .setContentIntent(viewPendingIntent);

        // Create a big text style for the second page
        NotificationCompat.BigTextStyle secondPageStyle = new NotificationCompat.BigTextStyle();
        secondPageStyle.setBigContentTitle("Page 2")
                .bigText("A lot of text...");

        // Create second page notification
        Notification secondPageNotification =
                new NotificationCompat.Builder(this)
                        .setStyle(secondPageStyle)
                        .build();

        // Add second page with wearable extender and extend the main notification
        Notification twoPageNotification =
                new NotificationCompat.WearableExtender()
                        .addPage(secondPageNotification)
                        .extend(notificationBuilder)
                        .build();

        // Issue the notification
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, twoPageNotification);
    }

    public void groupBtnClick(){
        int notificationId1 = 7;
        int notificationId2 = 8;

        // Build the notification, setting the group appropriately
        Notification notif1 = new NotificationCompat.Builder(this)
                .setContentTitle("New message from xx")
                .setContentText("今天天气好好啊！")
                .setSmallIcon(R.drawable.ic_message)
                .setGroup(GROUP_KEY_SMS)
                .build();

        Notification notif2 = new NotificationCompat.Builder(this)
                .setContentTitle("New mail from yy")
                .setContentText("今天我要去加班～～～")
                .setSmallIcon(R.drawable.ic_message)
                .setGroup(GROUP_KEY_SMS)
                .build();

        // Issue the notification
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId1, notif1);
        notificationManager.notify(notificationId2, notif2);
    }

    public void summaryBtnClick(){
        int notificationId3 = 9;

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_message);

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setBackground(BitmapFactory.decodeResource(getResources(),R.drawable.ic_background));

        // Create an InboxStyle notification
        Notification summaryNotification = new NotificationCompat.Builder(this)
                .setContentTitle("2 new messages")
                .setSmallIcon(R.drawable.ic_message)
                .setLargeIcon(largeIcon)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("10086   您的电话已欠费")
                        .addLine("95588   您的信用卡账单明天到期")
                        .setBigContentTitle("2 new messages")
                        .setSummaryText("短信－收件箱"))
                .setGroup(GROUP_KEY_SUM)
                .setGroupSummary(true)
                .extend(wearableExtender)
                .build();

        // Issue the notification
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId3, summaryNotification);
    }

    public void voiceBtnClick(){
        int notificationId = 10;
        // Key for the string that's delivered in the action's intent
        String EXTRA_VOICE_REPLY = "extra_voice_reply";
        String[] replyChoices = getResources().getStringArray(R.array.reply_choices);
        RemoteInput remoteInput = new RemoteInput.Builder(EXTRA_VOICE_REPLY)
                .setLabel("语音回复")
                .setChoices(replyChoices)
                .build();
        // Create an intent for the reply action
        Intent replyIntent = new Intent(this, ViewEventActivity.class);
        PendingIntent replyPendingIntent =
                PendingIntent.getActivity(this, 0, replyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        // Create the reply action and add the remote input
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_reply,
                        getString(R.string.label),replyPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();

        // Build the notification and add the action via WearableExtender
        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_message)
                        .setContentTitle("标题")
                        .setContentText("待回复内容")
                        .extend(new NotificationCompat.WearableExtender().addAction(action))
                        .build();

        // Issue the notification
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notification);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
