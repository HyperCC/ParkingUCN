﻿using ServerZeroIce;
using ServerZeroIce.model;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;

namespace ServerParkingUCN
{
    class Program
    {
        /// <summary>
        /// Main starting point.
        /// </summary>
        /// <param name="args"></param>
        static void Main(string[] args)
        {
            CreateHostBuilder(args).Build().Run(); 
        }

         public static IHostBuilder CreateHostBuilder(string[] args) =>
            Host
            .CreateDefaultBuilder(args)
            // Development, Staging, Production
            .UseEnvironment("Development")
            // Logging configuration
            .ConfigureLogging(logging => 
            {
                logging.ClearProviders();
                logging.AddConsole(options => 
                {
                    options.IncludeScopes = true;
                    options.TimestampFormat = "[yyyyMMdd.HHmmss.fff]";
                    options.DisableColors = false;
                });
                logging.SetMinimumLevel(LogLevel.Trace);
            })
            // Enable Control+C listener
            .UseConsoleLifetime()
            // Service inside the DI
            .ConfigureServices((hostContext, services) => 
            {
                //Singleton
                services.AddSingleton<ContratosDisp_, ContratosImpl>();
                // The ServerParkingUcnService
                services.AddHostedService<ServerParkingUcnService>();
                
                // The Logger
                services.AddLogging();
                // The wait for finish
                services.Configure<HostOptions>(option => 
                {
                    option.ShutdownTimeout = System.TimeSpan.FromSeconds(15);
                });
            });
        
    }
}
