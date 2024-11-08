/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1progra2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Omar Romero
 */
public class MainFrame extends JFrame {

    ArrayList<RentItem> items;

    public MainFrame(ArrayList<RentItem> items) {
        this.items = items;
        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        panel.setLayout(new BorderLayout(20, 20));

        JLabel label = new JLabel();
        ImageIcon image = new ImageIcon(getClass().getResource("/images/aaa.png"));
        label.setIcon(image);
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label, BorderLayout.NORTH);

        JPanel bpanel = new JPanel();
        bpanel.setLayout(new GridLayout(2, 2, 10, 10));
        bpanel.setBackground(Color.BLACK);

        JButton addItem = new JButton("Agregar Item");
        JButton search = new JButton("Buscar Item");
        JButton sub = new JButton("Submenu");
        JButton print = new JButton("Información General");
        bpanel.add(addItem);
        bpanel.add(search);
        bpanel.add(sub);
        bpanel.add(print);

        addItem.addActionListener(e -> agregarItem());
        search.addActionListener(e -> buscarItem());
        sub.addActionListener(e -> ejecutarSubmenu());
        print.addActionListener(e -> printall());

        panel.add(bpanel, BorderLayout.CENTER);

        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void agregarItem() {
        String tipo = JOptionPane.showInputDialog("Ingrese el tipo de ítem (MOVIE o GAME):");
        if (tipo == null || tipo.isEmpty()) {
            return; 
        }

        tipo = tipo.toUpperCase();
        if (!tipo.equals("MOVIE") && !tipo.equals("GAME")) {
            JOptionPane.showMessageDialog(this, "TIPO INCORRECTO");
            return;
        }

        String codigoStr = JOptionPane.showInputDialog("Ingrese el código único del ítem:");
        if (codigoStr == null || codigoStr.isEmpty()) {
            return; 
        }

        int codigo;
        try {
            codigo = Integer.parseInt(codigoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Código inválido. Debe ser un número.");
            return;
        }

        for (RentItem item : items) {
            if (item.getCodigo() == codigo) {
                JOptionPane.showMessageDialog(this, "Código ya existe.as Ingrese un código único.");
                return;
            }
        }
        String nombreItem = JOptionPane.showInputDialog("Ingrese el nombre del ítem:");
        if (nombreItem == null || nombreItem.isEmpty()) {
            return; 
        }

        if (tipo.equals("MOVIE")) {
            String precioStr = JOptionPane.showInputDialog("Ingrese el precio de renta:");
            if (precioStr == null || precioStr.isEmpty()) {
                return; 
            }

            double precio;
            try {
                precio = Double.parseDouble(precioStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Precio inválido. Debe ser un número.");
                return;
            }

            items.add(new Movie(codigo, nombreItem, precio));
        } else {
            items.add(new Game(codigo, nombreItem, 20));
        }

        JOptionPane.showMessageDialog(this, "Ítem agregado exitosamente.");
    }

    private void buscarItem() {
        String codigoStr = JOptionPane.showInputDialog("Ingrese el código del ítem:");
        if (codigoStr == null || codigoStr.isEmpty()) {
            return; 
        }

        int codigo;
        try {
            codigo = Integer.parseInt(codigoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Código inválido. Debe ser un número.");
            return;
        }

        RentItem item = find(codigo);
        if (item != null) {
            JOptionPane.showMessageDialog(this, item.toString());
            String diasStr = JOptionPane.showInputDialog("Ingrese los días de renta:");
            if (diasStr == null || diasStr.isEmpty()) {
                return;
            }

            int dias;
            try {
                dias = Integer.parseInt(diasStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Número de días inválido.");
                return;
            }

            double pago = item.pagoRenta(dias);
            JOptionPane.showMessageDialog(this, "Monto a pagar: " + pago + " Lps");
        } else {
            JOptionPane.showMessageDialog(this, "Item No Existe");
        }
    }

    private RentItem find(int codigo) {
        for (RentItem item : items) {
            if (item.getCodigo() == codigo) {
                return item;
            }
        }
        return null;
    }

    private void printall() {
        String listaItems = "Listado de Ítems:\n";
        for (RentItem item : items) {
            listaItems += item.toString() + "\n";
        }
        JOptionPane.showMessageDialog(this, listaItems);
    }

    private void ejecutarSubmenu() {
        String codigoStr = JOptionPane.showInputDialog("Ingrese el código del ítem:");
        if (codigoStr == null || codigoStr.isEmpty()) {
            return; 
        }

        int codigo;
        try {
            codigo = Integer.parseInt(codigoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Código inválido. Debe ser un número.");
            return;
        }

        RentItem item = find(codigo);
        if (item instanceof MenuActions) {
            String submenu = ((MenuActions) item).submenu();
            String opcionStr = JOptionPane.showInputDialog(submenu + "\nEscoja Opción:");
            if (opcionStr == null || opcionStr.isEmpty()) {
                return; 
            }

            int opcion;
            try {
                opcion = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Opción inválida. Debe ser un número.");
                return;
            }

            ((MenuActions) item).ejecutarOpcion();
        } else {
            JOptionPane.showMessageDialog(this, "Este ítem no tiene submenú disponible.");
        }
    }
}
