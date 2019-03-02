package defaultpackage;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.neodatis.odb.ODB;

import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class BDOOII_Ej1 {

	public static void main(String[] args) {
		Paises pais1 = new Paises(1,"España");
		Paises pais2 = new Paises(2,"Mexico");
		Paises pais3 = new Paises(3,"China");
		Paises pais4 = new Paises(4,"Canada");
		
		Jugadores j1 = new Jugadores("Maria", "voleibol", "Madrid", 13, pais1); // Crear instancias para almacenar en BD
		Jugadores j2 = new Jugadores("Miguel", "tenis", "Madrid", 15, pais2);
		Jugadores j3 = new Jugadores("Mario", "baloncesto", "Guadalajara", 21, pais3);
		Jugadores j4 = new Jugadores("Alicia", "tenis", "Madrid", 20, pais4);
		ODB odb = ODBFactory.open("EQUIPOS.BD"); // Abrir BD
		odb.store(j1); // Almacenamos objetos
		odb.store(j2);
		odb.store(j3);
		odb.store(j4);
		odb.store(pais1); // Almacenamos objetos
		odb.store(pais2);
		odb.store(pais3);
		odb.store(pais4);
		System.out.println("Se han insertado los datos");
		
		String nuevo_nombre="";
		Scanner persona;
		IQuery query=null;
		
		Objects<Jugadores>objects=null;			
		System.out.println("Introduce el nombre de la persona");
		persona = new Scanner(System.in);
		nuevo_nombre= persona.nextLine();		
		 
		 query = new CriteriaQuery(Jugadores.class, Where.equal("nombre", nuevo_nombre));
		 objects= odb.getObjects(query);
		 
		 if(objects.size()==0) {
			 System.out.println("No hay nadie con este nombre");
		 }else {
			 while(objects.hasNext()){ // visualizar los objetos de Jugadores
					Jugadores jug = objects.next();
					System.out.printf("%s, %s, %s, %d %n",
						 jug.getNombre(), jug.getDeporte(),
							jug.getCiudad(), jug.getEdad());
				}
		 }		 
		 
		 odb.close();

	}

}
