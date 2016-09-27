package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Ciudad;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BusquedaFinalizadaListener;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.FormBusqueda;

/**
 * Created by martdominguez on 22/09/2016.
 */
public class BuscarDepartamentosTask extends AsyncTask<FormBusqueda,Integer,List<Departamento>> {

    //Note: nullable field - providing a listener is optional
    final private BusquedaFinalizadaListener<Departamento> listener;

    public BuscarDepartamentosTask(BusquedaFinalizadaListener<Departamento> listener){
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(List<Departamento> departamentos) {
        if(listener != null) listener.busquedaFinalizada(departamentos);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if (listener != null)
            listener.busquedaActualizada("departamento " + values[values.length - 1]);
    }

    @Override
    protected List<Departamento> doInBackground(FormBusqueda... busqueda) {
        if(busqueda.length != 1) throw new IllegalArgumentException("Task expects one and only one argument");

        List<Departamento> todos = Departamento.getAlojamientosDisponibles();
        List<Departamento> resultado = new ArrayList<Departamento>();
        int contador = 0;
        // DONE implementar: buscar todos los departamentos del sistema e ir chequeando las condiciones 1 a 1.
        // si cumplen las condiciones agregarlo a los resultados.
        for(Departamento apt : todos) {

            if (busqueda[0].matches(apt)) resultado.add(apt);
            publishProgress(++contador);

            if(isCancelled()) break; //Explicit check for cancellation

            //Artificial 5 ms wait between iterations
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                break; //If canceled while waiting
            }
        }

        return resultado;
    }
}
