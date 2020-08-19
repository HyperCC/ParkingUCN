using System;
using System.Linq;
using Ice;
using ServerZeroIce.model;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using ParkingDao;

namespace ServerParkingUCN
{
    /// <summary>
    /// The Implementation of the Contratos
    /// </summary>
    public class ContratosImpl : ContratosDisp_
    {
        /// <summary>
        /// The Logger
        /// </summary>
        private readonly ILogger<ContratosImpl> _logger;

        /// <summary>
        /// The Provider of DbContext
        /// </summary>
        private readonly IServiceScopeFactory _serviceScopeFactory;

        /// <summary>
        /// The Constructor.
        /// </summary>
        /// <param name="logger"></param>
        /// <param name="serviceScopeFactory"></param>
        public ContratosImpl(ILogger<ContratosImpl> logger, IServiceScopeFactory serviceScopeFactory)
        {
            _logger = logger;
            _logger.LogDebug("Building the ContratosImpl ..");
            _serviceScopeFactory = serviceScopeFactory;

            // Create the database
            _logger.LogInformation("Creating the Database ..");
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                pc.Database.EnsureCreated();
                pc.SaveChanges();
            }

            _logger.LogDebug("Done.");
        }

        /// <summary>
        /// Create a Persona
        /// </summary>
        /// <param name="persona">to save</param>
        /// <param name="current">the context of zeroIce</param>
        /// <returns>A Persona created</returns>
        public override Persona crearPersona(Persona persona, Current current = null)
        {
            _logger.LogDebug("Initialization of createPersona");
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                // instane the context
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();

                // updating the UID recibed for a viable UID
                try
                {
                    int nextid = pc.Personas.OrderByDescending(p => p.uid).First().uid;
                    persona.uid = nextid + 1;

                }
                catch (InvalidOperationException ex)
                {
                    _logger.LogDebug($"The Database is empty, adding the first Persona. Error details: {ex.GetBaseException()}");
                    // the  first Persona
                    persona.uid = 1;

                }

                // add the new Persona validated
                pc.Personas.Add(persona);
                pc.SaveChanges();
                _logger.LogDebug("Added the new Persona");
                return persona;
            }
        }

        /// <summary>
        /// Create a Vehiculo
        /// </summary>
        /// <param name="vehiculo">to save</param>
        /// <param name="current">the context of zeroIce</param>
        /// <returns>A Vehiculo created</returns>
        public override Vehiculo crearVehiculo(Vehiculo vehiculo, Current current = null)
        {
            _logger.LogDebug("Initialization of createVehiculo");
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                // instane the context
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();

                // updating the UID recibed for a viable UID
                try
                {
                    int nextid = pc.Vehiculos.OrderByDescending(v => v.uid).First().uid;
                    vehiculo.uid = nextid + 1;

                }
                catch (InvalidOperationException ex)
                {
                    _logger.LogDebug($"The Database is empty, adding the first Vehiculo. Error details: {ex.GetBaseException()}");
                    // the  first Vehiculo
                    vehiculo.uid = 1;

                }

                // add the new Vehiculo validated
                pc.Vehiculos.Add(vehiculo);
                pc.SaveChanges();
                _logger.LogDebug("Added the new Vehiculo");
                return vehiculo;
            }
        }

        /// <summary>
        /// Create a Registro
        /// </summary>
        /// <param name="registro">to save</param>
        /// <param name="current">the context of zeroIce</param>
        /// <returns>The Registro created</returns>
        public override Registro crearRegistro(Registro registro, Current current = null)
        {
            _logger.LogDebug("Initialization of createRegistro");
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                // instane the context
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();

                // updating the UID recibed for a viable UID
                try
                {
                    int nextid = pc.Registros.OrderByDescending(r => r.uid).First().uid;
                    registro.uid = nextid + 1;

                }
                catch (InvalidOperationException ex)
                {
                    _logger.LogDebug($"The Database is empty, adding the first Registro. Error details: {ex.GetBaseException()}");
                    // the  first Registro
                    registro.uid = 1;

                }

                _logger.LogDebug("Added the new Registro");

                // add the new Regitro validated
                pc.Registros.Add(registro);
                pc.SaveChanges();
                _logger.LogDebug("Added the new Registro");
                return registro;
            }
        }

        /// <summary>
        /// Given a patente, returns a vehiculo from Database
        /// </summary>
        /// <param name="patente">to search</param>
        /// <param name="current">the context of zeroIce</param>
        /// <returns></returns>
        public override Vehiculo obtenerVehiculo(string patente, Current current)
        {
            _logger.LogDebug("obtenerVehiculo initialization..");

            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();

                // the default Vehiculo
                Vehiculo vehiculo = null;

                try
                {
                    // try find the Vehiculo by patente
                    _logger.LogDebug($"Searching the Vehiculo by patente: {patente}");
                    vehiculo = pc.Vehiculos.Where(vehiculo => vehiculo.patente == patente).First();

                }
                catch (System.Exception e)
                {
                    // error in the query. return null Persona
                    _logger.LogDebug($"Error in query to search a Vehiculo by patente: {e.GetBaseException()}");

                }

                if (vehiculo != null)
                {
                    // Vehiculo finded correctly
                    _logger.LogDebug($"Vehiculo foundedwith owner: {vehiculo.responsable}");

                }
                else
                {
                    // the system must return a null Vehiculo
                    _logger.LogDebug($"Vehiculo with patente: {patente} not found");

                }

                _logger.LogDebug("Vehiculo not found in database");
                // pc.SaveChanges();
                return vehiculo;
            }
        }

        // 
        /// <summary>
        /// Given a rut, returns a persona from Database
        /// </summary>
        /// <param name="rut">to search</param>
        /// <param name="current">the context of zeroIce</param>
        /// <returns>A Persona founded</returns>
        public override Persona obtenerPersona(string rut, Current current)
        {
            _logger.LogDebug("obtenerPersona initialization..");

            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();

                Persona persona = new Persona();
                try
                {
                    _logger.LogDebug($"Searching the Persona by rut: {rut}");
                    persona = pc.Personas.Where(persona => persona.rut == rut).First();

                }
                catch (System.Exception e)
                {
                    // error in the query. return null Persona
                    _logger.LogDebug($"Error in query to search a Persona by rut: {e.GetBaseException()}");
                    return null;

                }

                _logger.LogDebug("Persona not found in database");
                // pc.SaveChanges();
                return persona;
            }
        }

    }
}