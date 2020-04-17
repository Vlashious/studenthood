using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using System.IO;
using Microsoft.Extensions.FileProviders;

namespace dotnet
{
    public class Startup
    {
        private IWebHostEnvironment _env;
        public Startup(IWebHostEnvironment environment)
        {
            _env = environment;
        }
        // This method gets called by the runtime. Use this method to add services to the container.
        // For more information on how to configure your application, visit https://go.microsoft.com/fwlink/?LinkID=398940
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddTransient<ICounter, RandomCounter>(); // Create instance of the ICounter object.
            services.AddTransient<CounterService>(); // Cuz CounterService holds an ICounter object, create another object for it.
            // Thus, each time there is a request, TWO objects are created. For ICounter implementation and for CounterService.

            services.AddScoped<ICounter, RandomCounter>(); // Create instance of the ICounter object.
            services.AddScoped<CounterService>(); // Cuz instance of the ICounter exists, CounterService will hold alrdy created service.
            // Thus, each time there is a request, ONE object is created. They are recreated each time there is a new request.

            services.AddSingleton<ICounter, RandomCounter>(); // Create instance of the ICounter object.
            services.AddSingleton<CounterService>(); // Cuz instance of the ICounter exists, CounterService will hold alrdy created service.
            // Thus, each time there is a request, ONE object is created, and they last until app is closed.
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            app.UseMiddleware<CounterMiddleware>();
        }
    }
}
