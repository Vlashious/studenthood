using System.Text.Encodings.Web;
using Microsoft.AspNetCore.Mvc;

namespace dotnet.Controllers
{
    public class HelloWorldController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Welcome(string name, int num = 1)
        {
            ViewData["Message"] = "Hello" + name;
            ViewData["NumTimes"] = num;

            return View();
        }
    }
}