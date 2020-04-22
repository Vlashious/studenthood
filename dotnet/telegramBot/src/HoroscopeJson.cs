using System.Text.Json;
using System.Text.Json.Serialization;

namespace json
{
    public class HoroscopeJson
    {
        public string date { get; set; }
        public string horoscope { get; set; }
        public string sunsign { get; set; }
    }
}