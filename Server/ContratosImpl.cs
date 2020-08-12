using Ice;
using System.Linq;
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

            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                pc.Personas.Add(persona);
                pc.SaveChanges();
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

            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                pc.Vehiculos.Add(vehiculo);
                pc.SaveChanges();
                return vehiculo;
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
                    vehiculo = pc.Vehiculos.Where(vehiculo => vehiculo.Patente == patente).First();

                }
                catch (Exception e)
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

                // the default Persona
                Persona persona = null;

                try
                {
                    // try find the Persona by rut
                    _logger.LogDebug($"Searching the Persona by rut: {rut}");
                    persona = pc.Personas.Where(persona => persona.rut == rut).First();

                }
                catch (Exception e)
                {
                    // error in the query. return null Persona
                    _logger.LogDebug($"Error in query to search a Persona by rut: {e.GetBaseException()}");

                }

                if (persona != null)
                {
                    // Persona finded correctly
                    _logger.LogDebug($"Persona founded: {persona.nombre}");

                }
                else
                {
                    // the system must return a null Persona
                    _logger.LogDebug($"Persona with rut: {rut} not found");

                }

                _logger.LogDebug("Persona not found in database");
                // pc.SaveChanges();
                return persona;
            }
        }

        /// <summary>
        /// Given a patente, records that a vehicle has entered the university
        /// </summary>
        /// <param name="patente"></param>
        /// <param name="current"></param>
        /// <returns> The Vehiculo joined</returns>
        public override Vehiculo ingresarVehiculo(string patente, Current current)
        {
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                Vehiculo vehiculo = pc.Vehiculos.Find(patente);
                //TODO: entry of a vehicle in a register of entry to the university
                pc.SaveChanges();
                return vehiculo;
            }
        }
    }
}