
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Contactos;

public class ContactosController {
	
	
	//METODO DE CONSULTA
	  public List<Contactos> consultarContactos() {
	   
		  List<Contactos> listaContactos = null;
	        
	        try {
	        	
	        	SessionFactory sessionFactory = new  Configuration().configure("doc.cfg.xml").addAnnotatedClass(Contactos.class).buildSessionFactory();
	    		
				Session session = sessionFactory.openSession();
				    // Iniciar transacción
	            session.beginTransaction();
	            
	            // Ejecutar consulta para obtener todos los contactos
	            listaContactos = session.createQuery("from Contactos", Contactos.class).getResultList();
	            
	            // Commit de la transacción
	            session.getTransaction().commit();
	            
	        } catch (Exception e) {
	            // Manejar excepciones
	            e.printStackTrace();
	        } finally {
	            // Cerrar sesión
	            //if (session != null) {
	            	//session.close();
	            //}
	        }
	        
	        return listaContactos;
	    }	
	
	
		//Metodo de inserccion
		public String createContactos(String Correo_electronico, int Telefono_contacto,String Mensaje){
			
			SessionFactory sessionFactory = new  Configuration().configure("doc.cfg.xml").addAnnotatedClass(Contactos.class).buildSessionFactory();
		
			Session session = sessionFactory.openSession();
			
			try {
				
				Contactos contactos = new Contactos(Correo_electronico,Telefono_contacto,Mensaje);
				///abro conexion///
				session.beginTransaction();
				
				session.persist(contactos);
				
				session.getTransaction().commit(); ///el commit funciona para aprobar el insert///
				////cierra la conexion///
				sessionFactory.close();
				
				
			return "Solicitud Creada";
			
			} catch(Exception e) {
				
				return "Error de envio de solicitud" + e;
			}
		
		}
		public String UpdateContactos(int id_requerimiento, String Corre_electronico, int Telefono, String Mensaje) {		
			SessionFactory sessionFactory = new  
			Configuration().configure("doc.cfg.xml").addAnnotatedClass(Contactos.class).buildSessionFactory();
			
			Session session = sessionFactory.openSession();
			
			try {
				
				Contactos contacto = new Contactos(Corre_electronico, Telefono, Mensaje);
				
				session.beginTransaction();
				
				
				session.merge(contacto);
				
				session.getTransaction().commit(); ///el commit funciona para aprobar el insert///
				////cierra la conexion///
				sessionFactory.close();
				
				
			return "Solicitud Modificada";
			
			} catch(Exception e) {
				
				return "Error en la actualizacion de solicitud" + e;
		}
}
			public String DeleteContactos(int id_requerimiento) {		
				SessionFactory sessionFactory = new  
				Configuration().configure("doc.cfg.xml").addAnnotatedClass(Contactos.class).buildSessionFactory();
				
				Session session = sessionFactory.openSession();
				
				try {
					
					Contactos contacto = session.get(Contactos.class , id_requerimiento);
					
					session.beginTransaction();
					
					session.remove(contacto);
					
					session.getTransaction().commit(); ///el commit funciona para aprobar el insert///
					////cierra la conexion///
					sessionFactory.close();
					
					
				return " Su solicitud ha sido Eliminada";
				
				} catch(Exception e) {
					
					return "Error en la Eliminacion de solicitud" + e;	
		}
		
	}
}

