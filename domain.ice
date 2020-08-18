/*
Copyright (c) 2020. Castillo - Condorcet - Pizarro Engineering Students.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

// https://doc.zeroc.com/ice/3.7/language-mappings/java-mapping/client-side-slice-to-java-mapping/customizing-the-java-mapping
["java:package:cl.ucn.disc.pdis.simplescraper.zeroice", "cs:namespace:ServerZeroIce"]
module model {

    /**
     * Sex of Person.
     */
    enum Sexo {
        VAR, MUJ
    }

    /**
     * Estado of Registro.
     */
    enum Estado {
        ENTRADA, SALIDA
    }

    /**
     * The Persona class.
     */
    ["cs:property"]
    class Persona {
		
        /**
     	 * The id: Primary Key.
     	 */    
    	int uid;

    	/**
     	 * The web id from UCN directory.
     	 */    
    	int webId;

    	/**
     	 * The Nombre.
     	 */    
    	string nombre;

    	/**
    	 * The RUT.
    	 */    
    	string rut;

    	/**
     	 * The enum Sexo.
     	 */    
    	Sexo sexo;

    	/**
     	 * The Cargo.
     	 */    
    	string cargo;

    	/**
     	 * The Unidad.
    	 */    
    	string unidad;

    	/**
    	 * The eMail.
    	 */    
    	string email;

    	/**
    	 * The Telefono.
    	 */    
    	string telefono;

    	/**
    	 * The Oficina.
    	 */    
    	string oficina;

    	/**
    	 * The work address.
    	 */    
    	string direccionTrabajo;

    	/**
    	 * The home address.
    	 */    
    	string direccionCasa;

    	/**
     	 * The City.
    	 */    
    	string comuna;
    }

    /**
     * The Vehiculo class.
     */
    ["cs:property"]
    class Vehiculo {

        /**
      	 * The id: Primary Key.
     	 */    
    	int uid;

        /**
     	 * Vehicle Plate.
     	 */    
    	string patente;

    	/**
     	 * Vehicle Brand.
     	 */    
    	string marca;

    	/**
     	 * Vehicle Model.
     	 */    
    	string modelo;

    	/**
    	 * Vehicle manufacture Year.
    	 */    
    	string anio;

    	/**
     	 * Obvsevations of the vehicle.
     	 */    
    	string observacion;

    	/**
     	 * The owner of the vehicle.
    	 */    
    	string responsable;    	
    }

    /**
     * The Registro class.
     */
    ["cs:property"]
    class Registro{

        /**
      	 * The id: Primary Key.
     	 */    
    	int uid;

    	/**
     	 * The patente of vehicle.
    	 */    
    	string patente; 

    	/**
     	 * The actual driver (rut) of the vehicle.
    	 */    
    	string responsable;   

    	/**
     	 * The date of entry.
    	 */    
    	string fecha;

    	/**
     	 * The time of entry.
    	 */    
    	string hora;

    	/**
     	 * The Estado (ENTRADA/ SALIDA).
    	 */    
    	Estado estado;
    }
	
    /**
     * The Contratos.
     */
    interface Contratos {

        /**
         * Create a Persona with a persona instance.
         * 
         * @param persona to create
         * @return Persona created
         */
        Persona crearPersona(Persona persona);

	/**
         * Create a Vehiculo with a vehiculo instance.
         *
         * @param vehiculo to create
         * @return Vehiculo created
         */
        Vehiculo crearVehiculo(Vehiculo vehiculo);

	/**
         * Vehiculo entry to university.
         *
         * @param registro de vehiculo a la universidad.
         * @return Registro ingresado
         */
        Registro crearRegistro(Registro registro);
      
        /**
         * Search a Persona with a rut.
         *
         * @param rut de la persona a buscar.
         * @return Persona buscado.
         */
        Persona obtenerPersona(string rut);

	/**
         * Search a Vehiculo with a patente.
	 *
         * @param patente del vehiculo a buscar.
         * @return Vehiculo buscado.
         */
        Vehiculo obtenerVehiculo(string patente);
    }

}
