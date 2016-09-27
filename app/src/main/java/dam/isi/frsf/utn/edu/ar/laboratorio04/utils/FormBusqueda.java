package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import java.io.Serializable;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Ciudad;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;

/**
 * Created by martdominguez on 22/09/2016.
 */
public class FormBusqueda implements Serializable {
    //Note: all fields are nullable.
    //A field being null means that that specific criterion is unspecified for the search (i.e., the
    //user has no particular preference).
    //We cannot use java.util.Optional to express this due to compatibility requirements.
    private Double precioMinimo;
    private Double precioMaximo;
    private Ciudad ciudad;
    private Boolean permiteFumar;
    private Integer huespedes;

    public FormBusqueda(){}

    public FormBusqueda(Double precioMinimo, Double precioMaximo) {
        this(precioMinimo,precioMaximo,null,null,null);
    }

    public FormBusqueda(Ciudad ciudad) {
        this(null,null,ciudad,null,null);
    }

    public FormBusqueda(Double precioMinimo, Double precioMaximo, Ciudad ciudad, Boolean permiteFumar, Integer huespedes) {
        this.precioMinimo = precioMinimo;
        this.precioMaximo = precioMaximo;
        this.ciudad = ciudad;
        this.permiteFumar = permiteFumar;
        this.huespedes = huespedes;
    }

    public Double getPrecioMinimo() {
        return precioMinimo;
    }

    public void setPrecioMinimo(Double precioMinimo) {
        this.precioMinimo = precioMinimo;
    }

    public Double getPrecioMaximo() {
        return precioMaximo;
    }

    public void setPrecioMaximo(Double precioMaximo) {
        this.precioMaximo = precioMaximo;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Boolean getPermiteFumar() {
        return permiteFumar;
    }

    public void setPermiteFumar(Boolean permiteFumar) {
        this.permiteFumar = permiteFumar;
    }

    public Integer getHuespedes() {
        return huespedes;
    }

    public void setHuespedes(Integer huespedes) {
        this.huespedes = huespedes;
    }

    public boolean matches(Departamento apartment) {
        if(apartment == null) throw new NullPointerException("apartment must not be null");

        //Test apartment against all criteria
        if(precioMinimo != null && apartment.getPrecio() < precioMinimo) return false;
        if(precioMaximo != null && apartment.getPrecio() > precioMaximo) return false;
        if(ciudad != null && !apartment.getCiudad().equals(ciudad)) return false;
        if(permiteFumar != null && apartment.getNoFumador() == permiteFumar) return false;
        if(huespedes != null && apartment.getCapacidadMaxima() < huespedes) return false;

        return true;
    }
}
