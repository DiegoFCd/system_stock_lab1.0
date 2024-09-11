package com.lab.system_stock_lab.servicio;

import com.lab.system_stock_lab.modelo.Medicamento;

import java.util.List;

public interface IntMedicamentoServicio {
    //Aqui se establecen los metodos que luego aplicara la clase MedicamentoServicio

    public List <Medicamento> listarMedicamentos();

    public Medicamento buscarLibroPorId(Integer idMedicamento);

    public void guardarMedicamento(Medicamento medicamento);

    public void eliminarMedicamento(Medicamento medicamento);

    public void entregarMedicamento(Medicamento medicamento);


}
