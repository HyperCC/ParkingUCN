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
     * The Persona class.
     */
    ["cs:property"]
    class Persona{
		
        /**
     	 * The id: Primary Key.
     	 */    
    	private int uid;

    	/**
     	 * The web id from UCN directory.
     	 */    
    	private int webId;

    	/**
     	 * The Nombre.
     	 */    
    	private String nombre;

    	/**
    	 * The RUT.
    	 */    
    	private String rut;

    	/**
     	 * The enum Sexo.
     	 */    
    	private Sexo sexo;

    	/**
     	 * The Cargo.
     	 */    
    	private String cargo;

    	/**
     	 * The Unidad.
    	 */    
    	private String unidad;

    	/**
    	 * The E-Mail.
    	 */    
    	private String email;

    	/**
    	 * The Telefono.
    	 */    
    	private String telefono;

    	/**
    	 * The Oficina.
    	 */    
    	private String oficina;

    	/**
    	 * The work address.
    	 */    
    	private String direccionTrabajo;

    	/**
    	 * The home address.
    	 */    
    	private String direccionCasa;

    	/**
     	 * The City.
    	 */    
    	private String comuna;
    }

    /**
     * Sex of Person.
     */
    enum Sexo{
        VAR,
        MUJ
    }

    /**
     * The Vehiculo class.
     */
    ["cs:property"]
    class Vehiculo{

        /**
      	 * The id: Primary Key.
     	 */    
    	private int uid;

        /**
     	 * Vehicle Plate.
     	 */    
    	private String Patente;

    	/**
     	 * Vehicle Brand.
     	 */    
    	private String marca;

    	/**
     	 * Vehicle Model.
     	 */    
    	private String Modelo;

    	/**
    	 * Vehicle manufacture Year.
    	 */    
    	private int anio;

    	/**
     	 * Obvsevations of the vehicle.
     	 */    
    	private String observacion;

    	/**
     	 * The owner of the vehicle.
    	 */    
    	private String responsable;    	
    }

    /**
     * The Contratos.
     */
    interface Contratos {

        /**
         * Create a Persona with a persona instance.
         * 
         * @param functionary to create
         * @return Functionary created
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
         * Search a Persona with a rut.
         *
         * @param rut de la persona a buscar.
         * @return Functionary buscado.
         */
        Persona obtenerFunctionary(string rut);

	/**
         * Search a Vehiculo with a patente.
	 *
         * @param patente del vehiculo a buscar.
         * @return Vehiculo buscado.
         */
        Vehiculo obtenerVehiculo(string patente);
    }

}
