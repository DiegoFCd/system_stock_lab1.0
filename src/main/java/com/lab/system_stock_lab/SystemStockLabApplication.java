package com.lab.system_stock_lab;

import com.lab.system_stock_lab.vista.MedicamentoForm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

//Dependecias utilizadas
//LOMBOK
//SPRING DATA JPA
//MYSQL Driver

@SpringBootApplication
public class SystemStockLabApplication {
	public static void main(String[] args) {

		//Configuracion para que spring detecte que no es una aplicacion web
		ConfigurableApplicationContext contextoSpring =
				new SpringApplicationBuilder(SystemStockLabApplication.class)
						.headless(false)
						.web(WebApplicationType.NONE)
						.run(args);

		// Ejecutamos el codigo para cargar el form en el momento que Spring ejecute la app
		EventQueue.invokeLater(()->{
			// Obtenemos el objeto form a traves de Spring
			MedicamentoForm medicamentoForm = contextoSpring.getBean(MedicamentoForm.class);
			medicamentoForm.setVisible(true);
		});
	}

}
