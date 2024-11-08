/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1progra2;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Omar Romero
 */
public class Movie extends RentItem {

    Calendar Fecha;

    public Movie(int Codigo, String nombreItem, double precio) {
        super(Codigo, nombreItem, precio);
        Fecha = Calendar.getInstance();
    }

    public Calendar getFecha() {
        return Fecha;
    }

    public void setFecha(Calendar Fecha) {
        this.Fecha = Fecha;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar date = getFecha();
        String formattedDate = sdf.format(Fecha.getTime());
        return super.toString() + " Fecha de Estreno: " + formattedDate + " -Movie";
    }

    @Override
    public double pagoRenta(int dias) {
        double pago = getPrecio();
        String estado = getEstado();
        if (estado.equals("ESTRENO") && dias > 2) {
            pago += 50 * (dias - 2);
        } else if (estado.equals("NORMAL") && dias > 5) {
            pago += 30 * (dias - 5);
        }

        return pago;
    }

    public String getEstado() {
        Calendar actual = Calendar.getInstance();
        int mesesDiferencia = (actual.get(Calendar.YEAR) - Fecha.get(Calendar.YEAR)) * 12 + actual.get(Calendar.MONTH) - Fecha.get(Calendar.MONTH);
        return mesesDiferencia <= 3 ? "ESTRENO" : "NORMAL";
    }

}
