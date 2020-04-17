using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;

namespace dotnet
{
    public class CounterMiddleware
    {
        private readonly RequestDelegate _next;
        private int i = 0;
        public CounterMiddleware(RequestDelegate next)
        {
            _next = next;
        }
        public async Task InvokeAsync(HttpContext context, ICounter counter, CounterService service)
        {
            i++;
            context.Response.ContentType = "text/html;charset=utf-8";
            await context.Response.WriteAsync($"Request {i}; Counter {counter.Value}; Service {service.Counter.Value}");
        }
    }
}