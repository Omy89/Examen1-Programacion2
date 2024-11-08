/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1progra2;

public abstract class RentItem {

    public int getCodigo() {
        return codigo;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public double getPrecio() {
        return precio;
    }
    
    int codigo;
    String nombreItem;
    double precio;
    
    public RentItem(int codigo, String nombreItem, double precio){
        this.codigo=codigo;
        this.nombreItem=nombreItem;
        this.precio=precio;
    }
    
    public String toString(){
        return "Codigo: "+codigo+" Nombre del Item: "+nombreItem+" Precio: "+precio;
    }
    
    public abstract double pagoRenta(int dias);
    
}
