package com.lab.system_stock_lab.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

@Entity             //Se define como la clase entidad de la aplicacion
@Data              //Traemos la api de Lombok
@NoArgsConstructor
@AllArgsConstructor //Genera el constructor con todos los argumentos(LOMBOK)
@ToString          //Metodo toString para acceder facilmente a los datos de la entidad(LOMBOK)


public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Genera el constructor vacio, sin argumentos(LOMBOK)


    Integer idMedicamento;
    String nombreMedicamento;
    String lote;
    LocalDate fechaIngreso;
    LocalDate vencimiento;
    Integer stock;


}
