package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BuscarDepartamentosTask;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BusquedaFinalizadaListener;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.FormBusqueda;

public class ListaDepartamentosActivity extends AppCompatActivity implements BusquedaFinalizadaListener<Departamento>, View.OnClickListener {

    private TextView tvEstadoBusqueda;
    private ListView listaAlojamientos;
    private Button cancelSearchButton;

    private DepartamentoAdapter departamentosAdapter;
    private List<Departamento> lista;

    private FormBusqueda search;
    private BuscarDepartamentosTask searchTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alojamientos);
        lista= new ArrayList<>();

        listaAlojamientos = (ListView) findViewById(R.id.listaAlojamientos);
        tvEstadoBusqueda = (TextView) findViewById(R.id.estadoBusqueda);
        cancelSearchButton = (Button) findViewById(R.id.cancelSearchButton);

        cancelSearchButton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        boolean esBusqueda = intent.getExtras().getBoolean("esBusqueda");
        if(esBusqueda){
            search = (FormBusqueda) intent.getSerializableExtra("frmBusqueda");
            tvEstadoBusqueda.setText("Buscando....");
            searchTask = new BuscarDepartamentosTask(ListaDepartamentosActivity.this);
            searchTask.execute(search);
        }else{
            tvEstadoBusqueda.setVisibility(View.GONE);
            cancelSearchButton.setVisibility(View.GONE);
            lista=Departamento.getAlojamientosDisponibles();
            departamentosAdapter = new DepartamentoAdapter(ListaDepartamentosActivity.this, lista, null);
            listaAlojamientos.setAdapter(departamentosAdapter);
        }
    }

    @Override
    public void busquedaFinalizada(List<Departamento> listaDepartamento) {
        tvEstadoBusqueda.setVisibility(View.GONE);
        cancelSearchButton.setVisibility(View.GONE);
        System.out.println(listaDepartamento);
        departamentosAdapter = new DepartamentoAdapter(ListaDepartamentosActivity.this, listaDepartamento, search);
        listaAlojamientos.setAdapter(departamentosAdapter);
    }

    @Override
    public void busquedaActualizada(String msg) {
        tvEstadoBusqueda.setText("Buscando..."+msg);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.cancelSearchButton:
                finish(); //Same as pressing back - the button is there to advertise the functionality
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(searchTask != null) searchTask.cancel(true);
    }
}
