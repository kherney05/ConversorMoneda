package vista;


import Util.Util;
import controlador.ControlMain;
import modelo.Producto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaMoneda extends JFrame {

    //variables del layaut
    private JComboBox comBoxProducto;
    private JTextField textCantidad;
    private JTextField textCOP;
    private JTextField textUSD;
    private JButton obtenerPrecioButton;
    private JPanel JPanelContenedor;
    private JTextField textUnitarioCOP;
    private JTextField textUnitarioUSD;


    //definicion de variables
    private ControlMain control;


    public VistaMoneda(ControlMain control) {
        this.control = control;
        addProducto();

        comBoxProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select = comBoxProducto.getSelectedIndex();
                Producto product = control.getListaProductos().get(select);
                control.setCalPrecio(product);
                // mostramos los precios de los productos cuando son selecionados
                textUnitarioCOP.setText(Double.toString(Util.redondearDecimales(product.getPrecio(),2)));
                textUnitarioUSD.setText(Double.toString(Util.redondearDecimales(product.precioUSD(),2)));
            }
        });

        obtenerPrecioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // variables que reciben los datos de los texfield
                String cantidadTxt = textCantidad.getText();
                int cantidad = Integer.parseInt(cantidadTxt);

                //converion de strin a double
                double precioTotalUSD = control.getCalPrecio().precioTotalUSD(cantidad);
                String precioTotalCOP = String.valueOf(control.getCalPrecio().precioTotalCOP(cantidad));
                // imprimimos por consola
                System.out.println("El precio total en dolares es: "+precioTotalUSD);
                System.out.println("El precio total en dolares es: "+precioTotalCOP);
                // redondeamos los valores con la clase Util
                textCOP.setText(String.valueOf(Util.redondearDecimales(Double.parseDouble(precioTotalCOP),2)));
                textUSD.setText(String.valueOf(Util.redondearDecimales(precioTotalUSD,2)));

            }
        });
    }

    //metodo Getter
    public JPanel getJPanelContenedor() {
        return JPanelContenedor;
    }

    public void addProducto(){
        for(Producto product: control.getListaProductos()){
            comBoxProducto.addItem(product.getNombre());
        }
    }
}


