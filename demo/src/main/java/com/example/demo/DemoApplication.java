package com.example.demo;

import com.example.demo.dtos.RowDto;
import com.example.demo.services.FileService;
import com.example.demo.services.ToXmlService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(DemoApplication.class, args);

		List<RowDto> rows = Arrays.asList(
				new RowDto("Tier 1 - Regresion Automation", "Caso 1", "Alta de Oportunidad", "1 - Agregar el producto Pago a Proveedores", "Pass", "04.02.2025 17:03", "UAT", "- -"),
				new RowDto("Tier 2 - Smoke Test", "Caso 2", "Eliminar Cuenta", "2 - Eliminar cuenta de usuario", "Fail", "05.02.2025 10:15", "DEV", "Error 500"),
				new RowDto("Tier 3 - Performance", "Caso 3", "Carga Masiva", "3 - Insertar 10000 registros", "Pass", "06.02.2025 14:30", "PROD", "Tiempo: 12s"),
				new RowDto("Tier 1 - Regresion Automation", "Caso 4", "Modificar Usuario", "4 - Actualizar datos personales", "Pass", "07.02.2025 09:45", "QA", "- -"),
				new RowDto("Tier 2 - Integración", "Caso 5", "Pago con Tarjeta", "5 - Procesar pago con Visa", "Fail", "08.02.2025 12:00", "UAT", "Timeout"),
				new RowDto("Tier 3 - Seguridad", "Caso 6", "Autenticación 2FA", "6 - Validar OTP", "Pass", "09.02.2025 16:20", "PROD", "- -")
		);

		ToXmlService toXmlService = new ToXmlService();

		toXmlService.createDocument(rows);

	}

}
