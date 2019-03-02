package defaultpackage;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class BDOOII_Ej3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Paises pais1 = new Paises(1,"España");
		Paises pais2 = new Paises(2,"Mexico");
		Paises pais3 = new Paises(3,"China");
		Paises pais4 = new Paises(4,"Canada");
		
		Jugadores j1 = new Jugadores("Maria", "voleibol", "Madrid", 13, pais1); // Crear instancias para almacenar en BD
		Jugadores j2 = new Jugadores("Miguel", "tenis", "Madrid", 15, pais2);
		Jugadores j3 = new Jugadores("Mario", "baloncesto", "Guadalajara", 21, pais3);
		Jugadores j4 = new Jugadores("Alicia", "tenis", "Madrid", 20, pais4);
		ODB odb = ODBFactory.open("EQUIPOS3.BD"); // Abrir BD
		odb.store(j1); // Almacenamos objetos
		odb.store(j2);
		odb.store(j3);
		odb.store(j4);
		odb.store(pais1); // Almacenamos objetos
		odb.store(pais2);
		odb.store(pais3);
		odb.store(pais4);
		System.out.println("Se han insertado los datos");
		
		IQuery query=null;
		
		Objects<Jugadores>objects=null;

		query = new CriteriaQuery(Jugadores.class);
		
		objects = odb.getObjects(query);
		 
		 for (Jugadores jugador : objects) {
			 jugador.setEdad(jugador.getEdad() + 1);
			 odb.store(jugador);
		 }
		 odb.commit();		
		 System.out.println("Edades actualizadas.");
		 
		 for (Jugadores jugador : objects) {
			 System.out.println(jugador.getNombre()+ ", " + jugador.getDeporte()+ ", "  +
						jugador.getCiudad()+ ", "  + jugador.getEdad());
		 }
		 
		 odb.close();

	}

}
