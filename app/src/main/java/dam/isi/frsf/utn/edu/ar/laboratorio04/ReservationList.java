package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

public class ReservationList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);

        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayList<Reserva> allReservations = new ArrayList<>();
        for(Departamento apt : Departamento.getAlojamientosDisponibles()) {
            allReservations.addAll(apt.getReservas());
        }

        listView.setAdapter(new ArrayAdapter<Reserva>(this, android.R.layout.simple_list_item_1, allReservations));
    }
}
