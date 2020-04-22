using System.Threading;
using Telegram.Bot;

using Handler;

namespace Main
{
    class Program
    {
        public static void Main(string[] args)
        {
            var botClient = new TelegramBotClient("1079849012:AAHcGJcwNLuqV0RZaTIkFBRFxzzUgiC4Nzs");
            var bot = botClient.GetMeAsync().Result;

            MessageHandler handler = new MessageHandler(botClient);

            botClient.StartReceiving();
            Thread.Sleep(int.MaxValue);
        }
    }
}