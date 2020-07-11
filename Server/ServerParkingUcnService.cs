using Ice;
using ServerZeroIce.model;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging; 

namespace ServerParkingUCN
{
    
    internal class ServerParkingUcnService : IHostedService
    {

        /// <summary>
        /// The Logger.
        /// </summary>
        private readonly ILogger<ServerParkingUcnService> _logger;

        /// <summary>
        /// The Port.
        /// </summary>
        private readonly int _port = 8080;

        /// <summary>
        /// The Communicator.
        /// </summary>
        private readonly Communicator _communicator;

        /// <summary>
        /// The ServerParkingUcnService.
        /// </summary>
        /// <param name="logger">Used to print debug message.</param>
        public ServerParkingUcnService(ILogger<ServerParkingUcnService> logger)
        {
            _logger = logger; 
            _communicator = buildCommunicator();
        }

        /// <summary>
        /// Triggered when the application host is ready to start the service.
        /// </summary>
        public Task StartAsync(CancellationToken cancellationToken)
        {
            _logger.LogDebug("Starting the ParkingService ..");

            // The adapter: https://doc.zeroc.com/ice/3.7/client-side-features/proxies/proxy-and-endpoint-syntax
            // tcp (protocol) -z (compression) -t 15000 (timeout in ms) -p 8080 (port to bind)
            var adapter = _communicator.createObjectAdapterWithEndpoints("Contratos", "tcp -z -t 15000 -p " + _port);

            // The interface Contratos from ice.
            Contratos contratos = new ContratosImpl();

            // Register in the communicator
            adapter.add(contratos, Util.stringToIdentity("Contratos"));

            // Activation
            adapter.activate();

            // All ok
            return Task.CompletedTask;
        }

        /// <summary>
        /// Triggered when the application host is performing a graceful shutdown.
        /// </summary>
        public Task StopAsync(CancellationToken cancellationToken)
        {
            _logger.LogDebug("Stopping the FivetService ..");

            _communicator.shutdown();

            _logger.LogDebug("Communicator Stopper!");

            return Task.CompletedTask;
        }

        /// <summary>
        /// Build the communicator.
        /// </summary>
        /// <returns>The Communicator</returns>
        private Communicator buildCommunicator()
        {
            _logger.LogDebug("Initializating Communicator v{0} ({1}) ..", Ice.Util.stringVersion(), Ice.Util.intVersion());

            // ZeroC properties
            Properties properties = Util.createProperties();
            // properties.setProperty("Ice.Trace.Network", "3");

            InitializationData initializationData = new InitializationData();
            initializationData.properties = properties;

            return Ice.Util.initialize(initializationData);
        }

    }

    /// <summary>
    /// The Implementation of TheSystem interface.
    /// </summary>
    public class ContratosImpl : ContratosDisp_
    {
        /// <summary>
        /// Create a new Persona.
        /// </summary>
        /// <param name="persona"></param>
        /// <param name="current"></param>
        /// <returns>Dhe Persona validated</returns>
        public override Persona crearPersona(Persona persona, Current current)
        {
            return default; 
        }

        /// <summary>
        /// Create a new Vehiculo.
        /// </summary>
        /// <param name="vehiculo"></param>
        /// <returns>The Vehiculo validated</returns>
        public override Vehiculo crearVehiculo(Vehiculo vehiculo, Current current)
        {
            return default; 
        }

        /// <summary>
        /// Get a Persona by Rut.
        /// </summary>
        /// <param name="rut"></param>
        /// <returns>The Persona searched</returns>
        public override Persona obtenerPersona(string rut, Current current)
        {
            return default; 
        }
        
        /// <summary>
        /// Get a Vehiculo by Patente.
        /// </summary>
        /// <param name="patente"></param>
        /// <returns>The Vehiculo searched</returns>
        public override Vehiculo obtenerVehiculo(string patente, Current current)
        {
            return default; 
        }
    }
    
}