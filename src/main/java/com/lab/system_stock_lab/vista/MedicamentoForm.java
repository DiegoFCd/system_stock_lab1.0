package com.lab.system_stock_lab.vista;

import com.lab.system_stock_lab.modelo.Medicamento;
import com.lab.system_stock_lab.servicio.MedicamentoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MedicamentoForm extends JFrame {

    MedicamentoServicio medicamentoServicio;
    private JPanel panel;
    private JTable tablaMedicamaentos;
    private JTextField idTexto;
    private JTextField medicamentoTexto;
    private JTextField loteTexto;
    private JTextField fechaIngresoTexto;
    private JTextField vencimientoTexto;
    private JTextField entregasTexto;
    private JTextField stockTexto;

    private JButton agregarButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton entregarButton;

    private JLabel Entregas;
    private DefaultTableModel tablaModeloMedicamentos;


    @Autowired
    public MedicamentoForm(MedicamentoServicio medicamentoServicio){
        this.medicamentoServicio = medicamentoServicio;
        iniciarForma();
        agregarButton.addActionListener(e -> agregarMedicamento());
        //muesta los datos del medicamento seleccionado en la tabla
        tablaMedicamaentos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarMedicamentoSeleccionado();
            }
        });
        modificarButton.addActionListener(e -> modificarMedicamento());
        eliminarButton.addActionListener(e -> eliminarMedicamento());
        entregarButton.addActionListener(e -> entregarMedicamento());

    }



    private void iniciarForma(){
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900,700);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla = toolkit.getScreenSize();
        int x = (tamanioPantalla.width - getWidth()/ 2);
        int y = (tamanioPantalla.height = getHeight() / 2);
        setLocation(x, y);
    }

    private void agregarMedicamento(){
        //Leemos los datos de los campos requeridos
        if(medicamentoTexto.getText().equals("")){
            mostrarMensaje("Rellena el campo vacio");
            medicamentoTexto.requestFocusInWindow(); //Con este comando hacemos que el cursor pase al campo siguiente
            return;

        }//Si los campos estan completados seguirá el siguiente flujo:
        //Se obtiene cada uno de los campos ingresados a las variables de la entidad Libro
        var nombreMedicamento = medicamentoTexto.getText();
        var lote = loteTexto.getText();
        var fechaIngreso = fechaIngresoTexto.getText();    //Convertimos los datoos porque estan en texto..
        var vencimiento = vencimientoTexto.getText(); //...Tienen que estar en tipo de numero int y double
        var stock = Integer.parseInt(stockTexto.getText()); //Se obtiene el stock

        //Se crea el objeto Medicamento

        //Esta es otra forma de crear el objeto Libro Y Asociar los campos ingresados con las variables correspondientes
        var medicamento = new Medicamento();
        medicamento.setNombreMedicamento(nombreMedicamento);
        medicamento.setLote(lote);
        medicamento.setFechaIngreso(LocalDate.parse(fechaIngreso));
        medicamento.setVencimiento(LocalDate.parse(vencimiento));
        medicamento.setStock(stock);
        this.medicamentoServicio.guardarMedicamento(medicamento); //Usan la clase libro servicio usamos el metodo para guardar libro
        mostrarMensaje("Se agrego un nuevo Medicamento..");

        limpiarFormulario();                //Se genera una funcion que limpie el formulario una vez hecha la insercion
        listarMedicamentos();                     //Por ultimo llamamos el metodo que muestre la lista de libro updated

    }

    private void cargarMedicamentoSeleccionado(){
        //Los indices de las columnas empiezan en 0

        var renglon = tablaMedicamaentos.getSelectedRow();
        if(renglon != -1){ //Esto significa q es un valor valido(que se selecciono una fila) si no devuelve -1
            String idMedicamento=
                    tablaMedicamaentos.getModel().getValueAt(renglon,0).toString();
            idTexto.setText(idMedicamento);
            String nombreMedicamento=
                    tablaMedicamaentos.getModel().getValueAt(renglon,1).toString();
            medicamentoTexto.setText(nombreMedicamento);
            String lote=
                    tablaMedicamaentos.getModel().getValueAt(renglon,2).toString();
            loteTexto.setText(lote);
            String fechaIngreso=
                    tablaMedicamaentos.getModel().getValueAt(renglon,3).toString();
            fechaIngresoTexto.setText(fechaIngreso);
            String vencimiento =
                    tablaMedicamaentos.getModel().getValueAt(renglon,4).toString();
            vencimientoTexto.setText(vencimiento);
            String stock=
                    tablaMedicamaentos.getModel().getValueAt(renglon,5).toString();
            stockTexto.setText(stock);

        }
    }

    private void modificarMedicamento(){

        if (this.idTexto.getText().equals("")){ //si el idTexto esta vacio sifgnifica que no se selecciono nada
            mostrarMensaje("Debe seleccionar un registro para modificarlo");
        }else {
            //Verificamos que el nombre no sea null
            if (medicamentoTexto.getText().equals("")){
                mostrarMensaje("Proporciona el nombre del Medicamento");
                medicamentoTexto.requestFocusInWindow(); //Luego de mostrar el mensaje el cursor se coloca en el campo a completar
                return;
            }
            //Si el libroTexto es distinto de nulo, es decir se selecciono un registro a modificar
            //LLenamos el objeto del libro a modificar
            int idMedicamento = Integer.parseInt(idTexto.getText());
            var nombreMedicamento =medicamentoTexto.getText();
            var lote = loteTexto.getText();
            var fechaIngreso = fechaIngresoTexto.getText();
            var vencimiento= vencimientoTexto.getText();
            var stock = Integer.parseInt(stockTexto.getText());

            var medicamento = new Medicamento(null,nombreMedicamento,lote,LocalDate.parse(fechaIngreso),LocalDate.parse(vencimiento),stock);
            medicamentoServicio.guardarMedicamento(medicamento);
            mostrarMensaje("Se modifico el Medicamento...");
            limpiarFormulario();
            listarMedicamentos();
            }
    }

    private void eliminarMedicamento(){

        var renglon = tablaMedicamaentos.getSelectedRow();
        if (renglon != -1){ //Si se selecciono alguna fila
            String idMedicamento =
                    tablaMedicamaentos.getModel().getValueAt(renglon,0).toString();
            var medicamento = new Medicamento();
            medicamento.setIdMedicamento(Integer.parseInt(idMedicamento));
            medicamentoServicio.eliminarMedicamento(medicamento);
            mostrarMensaje("Se elimino el Medicamento Seleccionado "+"Con Id" + idMedicamento);
            limpiarFormulario();
            listarMedicamentos();
        }else{
            mostrarMensaje("No se ha seleccionado ningun Medicamento");
        }
    }





    private void entregarMedicamento() {

        //Traigo la lista de los medicamentos
        var medicamentos = medicamentoServicio.listarMedicamentos();

        if (this.idTexto.getText().equals("")) {
            mostrarMensaje("Debe seleccionar un registro para entregarlo");
        } else {
            //Verificamos que el stock no sea null
            if (stockTexto.getText().equals("")) {
                mostrarMensaje("Seleccione el registro y proporciona el stock a entregar del Medicamento");
                stockTexto.requestFocusInWindow(); //Luego de mostrar el mensaje el cursor se coloca en el campo a completar
                return;
            }

            int idMedicamento = Integer.parseInt(idTexto.getText());
            var nombreMedicamento =medicamentoTexto.getText();
            var lote = loteTexto.getText();
            var fechaIngreso = fechaIngresoTexto.getText();
            var vencimiento= vencimientoTexto.getText();
            var stock =Integer.parseInt(stockTexto.getText());
            var entregas =Integer.parseInt(entregasTexto.getText());

            Medicamento medicamentoExistente = null;
            for (Medicamento m : medicamentos){
                if(m.getNombreMedicamento().equals(nombreMedicamento) && m.getVencimiento().isBefore(LocalDate.now())){
                    medicamentoExistente = m;
                    break;
                }
            }
            if (medicamentoExistente != null) {
                // Si el medicamento existe, sumamos el stock
                medicamentoExistente.setStock(medicamentoExistente.getStock() + stock);
                mostrarMensaje("Stock actualizado para el medicamento existente: " + nombreMedicamento);

            } else {
                // Si el medicamento no existe, lo agregamos a la lista
                Medicamento nuevoMedicamento = new Medicamento();
                medicamentoServicio.guardarMedicamento(nuevoMedicamento);
                mostrarMensaje("Nuevo medicamento agregado: " + nombreMedicamento);
            }


            // Se verifica si la cantidad a entregar es mayor que el stock disponible
            if (entregas > stock) {
                mostrarMensaje("La cantidad a entregar no puede ser mayor que el stock disponible");
                entregasTexto.requestFocusInWindow();
                return;
            }

            /*
            for por cada lote
            restas cantidad necesaria del primer lote, si no alcanza seguir

             */

            int nuevoStock = stock - entregas;


            //Se crea el nuevo objeto medicamento
            var medicamento = new Medicamento(idMedicamento,nombreMedicamento,lote,LocalDate.parse(fechaIngreso),LocalDate.parse(vencimiento),nuevoStock);
            medicamento.setNombreMedicamento(nombreMedicamento);
            medicamento.setLote(lote);
            medicamento.setFechaIngreso(LocalDate.parse(fechaIngreso));
            medicamento.setVencimiento(LocalDate.parse(vencimiento));
            medicamento.setStock(nuevoStock);

            this.medicamentoServicio.entregarMedicamento(medicamento);
            mostrarMensaje("Se entrego el medicamento");
            limpiarFormulario();
            listarMedicamentos();
        }

    }




    private void limpiarFormulario(){
        //SOLO seteamos cada campo en un campo vacio:
        medicamentoTexto.setText("");
        loteTexto.setText("");
        fechaIngresoTexto.setText("");
        vencimientoTexto.setText("");
        stockTexto.setText("");
    }

    private void mostrarMensaje(String mensaje){

        JOptionPane.showMessageDialog(this,mensaje); //Mostramos el mensaje que no se llenaron los campos
    }
    private void mostrarVencimiento(LocalDate vencimiento,String mensaje){
        if (LocalDate.now().isBefore(vencimiento)){

            //Codiificar alerta de vencimiento
        }
    }

    private void createUIComponents() {

        //como en la tabla generada no hay un campo para el id del libro se genera un label oculto
        idTexto =new JTextField("");
        idTexto.setVisible(false);

        this.tablaModeloMedicamentos = new DefaultTableModel(0, 6){

            @Override
            //Esto evita que el usuario pueda editar desde la columna en la vista
            public boolean isCellEditable(int row,int column){
                return false;}
        };
        //Establecemos las cabeceras de las columnas
        String[] cabeceros = {"Id", "Nombre Medicamento", "Lote", "Fecha Ingreso", "Vencimiento", "Stock"};
        this.tablaModeloMedicamentos.setColumnIdentifiers(cabeceros);

        // Intanciar el objeto JTable
        this.tablaMedicamaentos = new JTable(tablaModeloMedicamentos);
        //Evitar que se seleccionen varios libros
        tablaMedicamaentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listarMedicamentos();
    }

    private void listarMedicamentos(){
        // Limpiar la tabla
        tablaModeloMedicamentos.setRowCount(0);
        // Obtener los libros
        var medicamentos = medicamentoServicio.listarMedicamentos();
        medicamentos.forEach((medicamento)->{        //ForEach en funcion Lambda
            Object[] renglonMedicamento = {    //este arreglo del tipo Object obtiene la info de cada renglon de la tabla
                    medicamento.getIdMedicamento(),
                    medicamento.getNombreMedicamento(),
                    medicamento.getLote(),
                    medicamento.getFechaIngreso(),
                    medicamento.getVencimiento(),
                    medicamento.getStock()   //En el arreglo object se guardara cada variable de la tabla
            };
            this.tablaModeloMedicamentos.addRow(renglonMedicamento);  //Por cada dato q obtuvo en cada renglon lo agrega a la tabla
        });
    }



}
