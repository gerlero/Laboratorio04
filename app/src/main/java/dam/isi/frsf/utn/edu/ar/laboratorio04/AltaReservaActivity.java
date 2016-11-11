package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Date;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.AlarmReceiver;

public class AltaReservaActivity extends AppCompatActivity implements View.OnClickListener {

    private Departamento selectedApartment;

    private DatePicker startDatePicker, endDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_reserva);

        selectedApartment = (Departamento) getIntent().getExtras().get("selectedApartment");

        startDatePicker = (DatePicker) findViewById(R.id.startDatePicker);
        endDatePicker = (DatePicker) findViewById(R.id.endDatePicker);
        Button makeReservationButton = (Button) findViewById(R.id.makeReservationButton);
        makeReservationButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            case R.id.makeReservationButton:

                //Make the reservation
                Date startDate = new Date(startDatePicker.getYear(), startDatePicker.getMonth(), startDatePicker.getDayOfMonth());
                Date endDate = new Date(endDatePicker.getYear(), endDatePicker.getMonth(), endDatePicker.getDayOfMonth());

                int reservationId = MainActivity.currentUser.getReservas().size();

                Reserva reservation = new Reserva(reservationId, startDate, endDate, selectedApartment);
                reservation.setUsuario(MainActivity.currentUser);
                selectedApartment.getReservas().add(reservation);
                MainActivity.currentUser.getReservas().add(reservation);

                //Set the required alarm
                Intent intent = new Intent(this, AlarmReceiver.class);
                intent.putExtra("reservationId", reservationId);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, reservationId, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, 15000, pendingIntent);

                //Tell the user what's happening
                Toast.makeText(this, "¡Gracias! Tu reserva está pendiente. Te avisaremos cuando sea confirmada", Toast.LENGTH_SHORT);

                finish();
                break;

        }

    }
}
