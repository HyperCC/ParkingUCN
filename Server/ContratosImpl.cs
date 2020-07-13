using Ice;
using ServerZeroIce.model;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;



namespace ServerParkingUCN
{
    public class ContratosImpl : ContratosDisp_
    {
        // The Logger
        private readonly ILogger<ContratosImpl> _logger;

        // The Provider of DbContext
        private readonly IServiceScopeFactory _serviceScopeFactory;

        // The constructor
        public ContratosImpl(ILogger<ContratosImpl> logger, IServiceScopeFactory serviceScopeFactory)
        {
            _logger = logger;
            _logger.LogDebug("Building the ContratosImpl ..");
            _serviceScopeFactory = serviceScopeFactory;

            /*TODO:
            // Create the database
            _logger.LogInformation("Creating the Database ..");
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                pc.Database.EnsureCreated();
                pc.SaveChanges();
            }
            */
            _logger.LogDebug("Done.");            
        }

        // Adds a Persona to Database
        public override Persona crearPersona(Persona persona, Current current = null)
        {
            /*TODO:
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                pc.Personas.Add(persona);
                pc.SaveChanges();
                return persona;
            }
            */
            throw new System.NotImplementedException();
        }

        // Adds a Vehiculo to Database
        public override Vehiculo crearVehiculo(Vehiculo vehiculo, Current current = null)
        {
            /*TODO:
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                pc.Vehiculos.Add(vehiculo);
                pc.SaveChanges();
                return vehiculo;
            }
            */
            throw new System.NotImplementedException();
        }

        // Given a patente, returns a vehiculo from Database
        public override Vehiculo obtenerVehiculo(string patente, Current current)
        {
            /*TODO:
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                Vehiculo vehiculo = pc.Vehiculos.Find(patente);
                pc.SaveChanges();
                return vehiculo;
            }
            */
            throw new System.NotImplementedException();
        }

        // Given a rut, returns a persona from Database
        public override Persona obtenerPersona(string rut, Current current)
        {
            /*TODO:
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                Persona persona = pc.Personas.Find(rut);
                pc.SaveChanges();
                return persona;
            }
            */
            throw new System.NotImplementedException();
        }
    }
}