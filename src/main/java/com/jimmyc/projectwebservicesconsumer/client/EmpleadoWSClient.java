/**
 * 
 */
package com.jimmyc.projectwebservicesconsumer.client;

import java.time.LocalDateTime;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jimmyc.projectwebservicesconsumer.dto.EmpleadoDTO;

/**
 * @author JimmyC
 * Clase cliente que permite consumir el webservice de Empleado
 */
public class EmpleadoWSClient {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//::::::::::::::::::: GET :::::::::::::::::::::::::
		/*Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/proyect-webservices/jimmyc/empleadosWS").path("consultarEmpleadoPorNumeroEmpleado").path("123456");
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		if(response.getStatus() == 204) {
			System.out.println("No se encontro el empleado con el Numero de Empleado");
		}
		
		if(response.getStatus() == 200) {
			EmpleadoDTO empleadoDTO = response.readEntity(EmpleadoDTO.class);
			
			System.out.println("Nombre Empleado: "+empleadoDTO.getNombre());
			System.out.println("Fecha de Creacion: "+ empleadoDTO.getFechaCreacion());
		}*/
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/proyect-webservices/jimmyc/empleadosWS").path("guardarEmpleado");
		
		EmpleadoDTO emp = new EmpleadoDTO();
		emp.setNumeroEmpleado("569865");
		emp.setNombre("Luis Jesus");
		emp.setPrimerApellido("Lopez");
		emp.setSegundoApellido("Romero");
		emp.setEdad(50);
		emp.setFechaCreacion(LocalDateTime.now());
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(emp, MediaType.APPLICATION_JSON));
		
		if(response.getStatus() == 400) {
			String error = response.readEntity(String.class);
			System.out.println(error);
		}
		
		if(response.getStatus() == 200) {
			EmpleadoDTO empleadoDTO = response.readEntity(EmpleadoDTO.class);
			
			System.out.println(empleadoDTO.getNombre());
			System.out.println(empleadoDTO.getFechaCreacion());
		}
		
		
		
	}
}
