package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Date;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Usuario;

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

                Date startDate = new Date(startDatePicker.getYear(), startDatePicker.getMonth(), startDatePicker.getDayOfMonth());
                Date endDate = new Date(endDatePicker.getYear(), endDatePicker.getMonth(), endDatePicker.getDayOfMonth());

                Reserva reservation = new Reserva(0, startDate, endDate, selectedApartment);
                reservation.setUsuario(MainActivity.currentUser);
                selectedApartment.getReservas().add(reservation);
                MainActivity.currentUser.getReservas().add(reservation);

                finish();
                break;

        }

    }
}
