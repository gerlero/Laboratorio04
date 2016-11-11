package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import dam.isi.frsf.utn.edu.ar.laboratorio04.ReservationConfirmationReceiver;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        int reservationId = (int) intent.getExtras().getInt("reservationId");

        Log.d("AlarmReceiver", "alarm rang for reservation with id " + reservationId);

        if(System.currentTimeMillis() % 3 == 0) {

            //Cancel the recurring alarm
            PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, reservationId, intent, 0);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(alarmPendingIntent);

            Log.d("AlarmReceiver", "alarm canceled for reservation with id " + reservationId);

            //Emit the broadcast
            Intent broadcastIntent = new Intent(context, ReservationConfirmationReceiver.class);
            broadcastIntent.putExtra("reservationId", reservationId);
            context.sendBroadcast(broadcastIntent);

        }
    }
}
