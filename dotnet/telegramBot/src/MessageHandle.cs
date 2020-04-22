using System.Threading;
using System.Net.Http;
using System.Collections.Generic;
using Telegram.Bot;
using Telegram.Bot.Args;
using Telegram.Bot.Types.Enums;
using Telegram.Bot.Types.ReplyMarkups;
using System.Text.Json;

namespace Handler
{
    public class MessageHandler
    {
        private ITelegramBotClient _botClient;
        private HttpClient _httpClient;
        private List<string> zodiacSigns = new List<string>() 
        {
            "Aries", "Cancer", "Taurus", 
            "Leo", "Gemini", "Virgo", 
            "Libra", "Capricorn", "Scorpio",
            "Aquarius", "Sagittarius", "Pisces"
        };

        private List<List<InlineKeyboardButton>> signsKeyBoard = new List<List<InlineKeyboardButton>>();

        public MessageHandler(ITelegramBotClient botClient)
        {
            _botClient = botClient;
            _httpClient = new HttpClient();
            PopulateSignKeyboard();
        }

        public void UseOnMessageProcessing()
        {
            _botClient.OnMessage += OnMessage;
        }

        public void UseOnCallBackQueryProcessing()
        {
            _botClient.OnCallbackQuery += OnCallbackQuery;
        }

        private async void OnMessage(object sender, MessageEventArgs e)
        {
            await _botClient.SendChatActionAsync(e.Message.Chat, ChatAction.Typing);

            Thread.Sleep(500);

            if(e.Message.Text != null)
            {
                await _botClient.SendTextMessageAsync(
                    chatId: e.Message.Chat,
                    text: "Choose you sign.",
                    parseMode: ParseMode.Markdown,
                    replyMarkup: new InlineKeyboardMarkup(signsKeyBoard)
                );
            }
        }

        private async void OnCallbackQuery(object sender, CallbackQueryEventArgs callbackQueryEventArgs)
        {
            var query = callbackQueryEventArgs;

            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, "http://horoscope-api.herokuapp.com/horoscope/today/" + query.CallbackQuery.Data);

            HttpResponseMessage response = await _httpClient.SendAsync(request);
            response.EnsureSuccessStatusCode();

            var responeBody = await response.Content.ReadAsStringAsync();

            var horoscopeJson = JsonSerializer.Deserialize<Dictionary<string, string>>(responeBody);

            await _botClient.SendTextMessageAsync(query.CallbackQuery.Message.Chat.Id, horoscopeJson["horoscope"]);
        }

        private void PopulateSignKeyboard()
        {
            for(int i = 0; i < zodiacSigns.Count;)
            {
                List<InlineKeyboardButton> row = new List<InlineKeyboardButton>();
                for(int j = 0; j < 3; j++)
                {
                    InlineKeyboardButton button = InlineKeyboardButton.WithCallbackData(zodiacSigns[i], zodiacSigns[i].ToLower());
                    i++;
                    row.Add(button);
                }
                signsKeyBoard.Add(row);
            }
        }
    }
}