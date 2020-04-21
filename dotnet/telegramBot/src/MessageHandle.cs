using System.Threading;
using System;
using Telegram.Bot;
using Telegram.Bot.Args;

namespace Handler
{
    public class MessageHandler
    {
        private ITelegramBotClient _botClient;

        public MessageHandler(ITelegramBotClient botClient)
        {
            _botClient = botClient;
        }

        public void UseOnMessageProcessing()
        {
            _botClient.OnMessage += OnMessage;
        }

        private async void OnMessage(object sender, MessageEventArgs eventArgs)
        {
            if(eventArgs.Message.Text != null)
            {
                Console.WriteLine($"Message received " + eventArgs.Message.Text);

                await _botClient.SendTextMessageAsync(eventArgs.Message.Chat, "You said:\n" + eventArgs.Message.Text);
            }
        }
    }
}