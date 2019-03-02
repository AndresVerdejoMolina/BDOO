package defaultpackage;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class EjemploNeodatis {

	public static void main(String[] args) {
		Paises pais1 = new Paises(1,"España");
		Paises pais2 = new Paises(2,"Mexico");
		Paises pais3 = new Paises(3,"China");
		Paises pais4 = new Paises(4,"Canada");
		
		Jugadores j1 = new Jugadores("Maria", "voleibol", "Madrid", 14, pais1); // Crear instancias para almacenar en BD
		Jugadores j2 = new Jugadores("Miguel", "tenis", "Madrid", 15, pais2);
		Jugadores j3 = new Jugadores("Mario", "baloncesto", "Guadalajara", 15, pais3);
		Jugadores j4 = new Jugadores("Alicia", "tenis", "Madrid", 14, pais4);
		ODB odb = ODBFactory.open("EQUIPOS.BD"); // Abrir BD
		odb.store(j1); // Almacenamos objetos
		odb.store(j2);
		odb.store(j3);
		odb.store(j4);
		odb.store(pais1); // Almacenamos objetos
		odb.store(pais2);
		odb.store(pais3);
		odb.store(pais4);
		
		Objects<Paises> objectsP = odb.getObjects(Paises.class);//recuperamos todos los objetos de Paises
		System.out.printf("%d Paises: %n", objectsP.size());
		int i = 1;

		while(objectsP.hasNext()){ // visualizar los objetos de Paises
			Paises country = objectsP.next();
			System.out.printf("%d: %s, %d %n",
					i++, country.getNombrepais(), country.getId());
		}
		
		
		Objects<Jugadores> objects = odb.getObjects(Jugadores.class);//recuperamos todos los objetos Jugadores
		System.out.printf("%d Jugadores: %n", objects.size());
		i = 1;

		while(objects.hasNext()){ // visualizar los objetos de Jugadores
			Jugadores jug = objects.next();
			System.out.printf("%d: %s, %s, %s %n",
					i++, jug.getNombre(), jug.getDeporte(),
					jug.getCiudad(), jug.getEdad());
		}
		odb.close(); // Cerrar BD
	}
}