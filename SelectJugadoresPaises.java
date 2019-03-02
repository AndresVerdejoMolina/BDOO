package defaultpackage;

import org.neodatis.odb.ODB;

import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class SelectJugadoresPaises {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ODB odb = ODBFactory.open("EQUIPOS.BD");
		Objects<Paises> objectsP = odb.getObjects(Paises.class);
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
