using Newtonsoft.Json;
using System.Collections.Generic;
using Telegram.Bot;
using System.Linq;
using System.IO;

public class UserChatInfo
{
    public string userChatId { get; set; }
    public string userPreferredGroup { get; set; }
}

public class UsersInfo
{
    private static Dictionary<long, string> _infoDictionary = new Dictionary<long, string>();

    public UsersInfo()
    {
        
    }

    private static void ConvertToJson()
    {
        var json = JsonConvert.SerializeObject(_infoDictionary, Formatting.Indented);
        File.WriteAllText("data.json", json);
    }

    public static void SetUser(long chatId, string group)
    {
        if(_infoDictionary.Keys.Contains(chatId))
        {
            _infoDictionary[chatId] = group;
        }
        else
        {
            _infoDictionary.Add(chatId, group);
        }
        ConvertToJson();
    }

}