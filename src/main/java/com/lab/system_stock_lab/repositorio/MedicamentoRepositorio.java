package com.lab.system_stock_lab.repositorio;

import com.lab.system_stock_lab.modelo.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepositorio extends JpaRepository <Medicamento, Integer>{



    //Extender esto permitira acceder a los metodos de eliminar,actualizar, ingresar el medicamento
    //Aparte se debe establecer la entidad principal y el tipo de llave primeria entre las llaves < >


}
