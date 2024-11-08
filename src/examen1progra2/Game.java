/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1progra2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Omar Romero
 */
public class Game extends RentItem implements MenuActions {

    Calendar Fecha;
    ArrayList<String> Especificaciones;

    public Game(int Codigo, String nombreItem, double Precio) {
        super(Codigo, nombreItem, Precio);
        this.precio = 20;
        this.Fecha = Calendar.getInstance();
        Especificaciones = new ArrayList<String>();
    }

    @Override
    public double pagoRenta(int dias) {
        return precio * dias;
    }

    @Override
    public String submenu() {
        return "1. Actualizar Fecha de Publicación\n"
                + "2.    Agregar Especificación\n"
                + "3.    Ver Especificaciones";
    }

    @Override
    public void ejecutarOpcion() {
        String opcionStr = JOptionPane.showInputDialog("Submenú de PS3 Game:\n" + submenu() + "Escoja Opción: ");
        if (opcionStr == null) {
            return;
        }

        int opcion;
        try {
            opcion = Integer.parseInt(opcionStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Opción inválida. Debe ser un número.");
            return;
        }

        switch (opcion) {
            case 1 ->
                actualizarFechaPublicacion();
            case 2 ->
                agregarEspecificacion();
            case 3 ->
                listarEspecificaciones();
            default ->
                JOptionPane.showMessageDialog(null, "Opción inválida.");
        }
    }

    private void actualizarFechaPublicacion() {
        try {
            int year = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año de publicación:"));
            int month = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el mes de publicación:")) - 1;
            int day = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el día de publicación:"));
            setFecha(year, month, day);
            JOptionPane.showMessageDialog(null, "Fecha de publicación actualizada.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Fecha inválida, Por favor ingrese números válidos.");
        }
    }

    private void agregarEspecificacion() {
        String especificacion = JOptionPane.showInputDialog("Ingrese una especificación:");
        if (especificacion != null && !especificacion.isEmpty()) {
            Especificaciones.add(especificacion);
            JOptionPane.showMessageDialog(null, "Especificación agregada");
        } else {
            JOptionPane.showMessageDialog(null, "Especificación no puede estar vacía");
        }
    }

    private void listarEspecificaciones() {
        if (Especificaciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay especifcacione");
        } else {
            JOptionPane.showMessageDialog(null, "Especificaciones\n" + listarEspecificacionesRecursivo(0));
        }
    }

    private String listarEspecificacionesRecursivo(int index) {
        if (index >= Especificaciones.size()) {
            return "";
        }
        return Especificaciones.get(index) + "\n" + listarEspecificacionesRecursivo(index + 1);
    }

    public void setFecha(int year, int month, int day) {
        this.Fecha.set(year, month, day);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar date = getFecha();
        String formattedDate = sdf.format(Fecha.getTime());
        return super.toString() + " Fecha de Estreno " + formattedDate + " -PS3 Game";
    }

    public Calendar getFecha() {
        return Fecha;
    }

}
