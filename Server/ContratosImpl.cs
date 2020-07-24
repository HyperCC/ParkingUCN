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
            
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                Vehiculo vehiculo = pc.Vehiculos.Find(patente);
                pc.SaveChanges();
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
            
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                Persona persona = pc.Personas.Find(rut);
                pc.SaveChanges();
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