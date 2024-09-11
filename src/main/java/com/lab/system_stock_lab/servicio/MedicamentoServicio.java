package com.lab.system_stock_lab.servicio;

import com.lab.system_stock_lab.modelo.Medicamento;
import com.lab.system_stock_lab.repositorio.MedicamentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //SE DEFINE COMO SERVICIO
public class MedicamentoServicio implements IntMedicamentoServicio {

    @Autowired         //Se define como bean de Spring
    private MedicamentoRepositorio medicamentoRepositorio;//Se genera un instancia de interfaz para usar sus metodos



    @Override  //Se reescriben todos los metodos de la Interfaz y se desarrollan
    public List<Medicamento> listarMedicamentos() {
        return medicamentoRepositorio.findAll();
    }

    @Override
    public Medicamento buscarLibroPorId(Integer idMedicamento) {
//Se genera una instancia de la entidad principal y ademas se usa los metodos de la capa repositorio (medicamentoRepoositorio)
    Medicamento medicamento = medicamentoRepositorio.findById(idMedicamento).orElse(null);
        return medicamento;
    }

    @Override
    public void guardarMedicamento(Medicamento medicamento) {
    medicamentoRepositorio.save(medicamento);
    }

    @Override
    public void eliminarMedicamento(Medicamento medicamento) {
    medicamentoRepositorio.delete(medicamento);
    }

    @Override
    public void entregarMedicamento(Medicamento medicamento) {
     medicamentoRepositorio.save(medicamento);
     medicamentoRepositorio.findAll();
    }

}
