package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static android.content.Context.NOTIFICATION_SERVICE;

public class ReservationConfirmationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //Confirm the reservation
        int reservationId = intent.getExtras().getInt("reservationId");
        MainActivity.currentUser.getReservas().get(reservationId).setConfirmada(true);

        Log.d("RConfirmationReceiver", "reservation with id " + reservationId + " confirmed");

        //Send a notification
        Intent reservationListIntent = new Intent(context, ReservationListActivity.class);
        PendingIntent reservationListPendingIntent = PendingIntent.getActivity(context, 0, reservationListIntent, 0);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Uri ringtone = Uri.parse(prefs.getString("ringtone", ""));

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context);
        notificationBuilder.setSmallIcon(R.drawable.ic_notifications_black_24dp);
        notificationBuilder.setContentTitle("Reserva confirmada");
        notificationBuilder.setContentText("¡Buenas noticias! Tu reserva pendiente ha sido confirmada");
        notificationBuilder.setContentIntent(reservationListPendingIntent);
        notificationBuilder.setSound(ringtone);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }
}
