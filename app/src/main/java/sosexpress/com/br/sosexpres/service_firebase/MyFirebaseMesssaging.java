package sosexpress.com.br.sosexpres.service_firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import sosexpress.com.br.sosexpres.R;
import sosexpress.com.br.sosexpres.activitys.ActPrincipal;

public class MyFirebaseMesssaging extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        showNotification( remoteMessage.getNotification());
    }

    private void showNotification(RemoteMessage.Notification notification) {
        Intent intent = new Intent(getApplicationContext(), ActPrincipal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"channel_id")
                .setSmallIcon(R.drawable.icon_notification)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setAutoCancel( true )
                .setContentIntent( pendingIntent );

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }
}
