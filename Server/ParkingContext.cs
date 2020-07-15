using System.Reflection;
using ServerZeroIce.model;
using Microsoft.EntityFrameworkCore;

namespace parking_Dao
{
    /// <summary>
    /// The Connection to the ParkingDatabase.
    /// </summary>
    public class ParkingContext : DbContext
    {
        /// <summary>
        /// The Connection to the database to Persona.
        /// </summary>
        /// <value> </value>
        public DbSet<Persona> Personas { get; set; }

        /// <summary>
        /// The Connection to the database to Vehiculo.
        /// </summary>
        /// <value> </value>
        public DbSet<Vehiculo> Vehiculos { get; set; }

        /// <summary>
        /// Configuration.
        /// </summary>
        /// <param name="optionsBuilder"> </param>
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            // Using SQLite
            optionsBuilder.UseSqlite("Data Source=parking.db", options =>
            {
                options.MigrationsAssembly(Assembly.GetExecutingAssembly().FullName);
            });
            base.OnConfiguring(optionsBuilder);
        }

        /// <summary>
        /// Create the ER from Entity.
        /// </summary>
        /// <param name="modelBuilder">to use</param>
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            // Make the model to Persona in Db. 
            modelBuilder.Entity<Persona>(p =>
            {
                // Primary Key
                p.HasKey(p => p.uid);
                // Directorio UCN Cod
                p.Property(p => p.webId).IsRequired();
                // The Name
                p.Property(p => p.nombre);
                // Required rut
                p.Property(p => p.rut);
                // The Sexo
                p.Property(p => p.sexo);
                // The Cargo
                p.Property(p => p.cargo);
                // The Unidad
                p.Property(p => p.unidad);
                // The Email 
                p.Property(p => p.email);
                // The Telefono
                p.Property(p => p.telefono);
                // The Oficina in UCN
                p.Property(p => p.oficina);
                // The Workd Address
                p.Property(p => p.direccionTrabajo);
                // The city
                p.Property(p => p.direccionCasa);
                // The Comuna
                p.Property(p => p.comuna);
            });

            // Make the model to Vehiculo in Db. 
            modelBuilder.Entity<Vehiculo>(v =>
            {
                // Primary Key
                v.HasKey(v => v.uid);
                // The Patente
                v.Property(v => v.Patente);
                // The Marca
                v.Property(v => v.marca);
                // The Modelo
                v.Property(v => v.Modelo);
                // The anio
                v.Property(v => v.anio);
                // The Observation
                v.Property(v => v.observacion);
                // The legal owner
                v.Property(v => v.responsable);
            });

        }

    }


}