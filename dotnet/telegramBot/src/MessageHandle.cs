using System.Threading;
using System.Net.Http;
using System.Collections.Generic;
using Telegram.Bot;
using Telegram.Bot.Args;
using Telegram.Bot.Types.Enums;
using Telegram.Bot.Types.ReplyMarkups;
using System;
using System.Linq;
using Newtonsoft.Json;
using Quartz;

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
            _botClient.OnMessage += OnMessage;
        }

        private async void OnMessage(object sender, MessageEventArgs e)
        {
            await _botClient.SendChatActionAsync(e.Message.Chat, ChatAction.Typing);

            Thread.Sleep(500);

            if(e.Message.Text == null)
            {
                return;
            }

            switch (e.Message.Text.Split(" ").First())
            {
                case "/start":
                    await _botClient.SendTextMessageAsync(
                        chatId: e.Message.Chat.Id,
                        text: "Hello! I'm Śniežań bot! I can do something what I CAN do uknow.\nTo learn more, use */menu*",
                        parseMode: ParseMode.Markdown
                    );
                    UsersInfo.SetUser(e.Message.Chat.Id, "none");
                break;
                case "/menu":
                    await _botClient.SendTextMessageAsync(
                        chatId: e.Message.Chat.Id,
                        text: "*/horoscope* - horoscope for today\n" +
                        "*/timetable group_number* - timetable for tomorrow",
                        parseMode: ParseMode.Markdown
                    );
                break;
                case "/horoscope":
                    _botClient.OnCallbackQuery += GetCallbackQueryData;
                    await _botClient.SendTextMessageAsync(
                        chatId: e.Message.Chat,
                        text: "Choose you sign.",
                        parseMode: ParseMode.Markdown,
                        replyMarkup: new InlineKeyboardMarkup(signsKeyBoard)
                    );
                break;
                case "/timetable":
                    SendTimetable(e);
                break;
                default:
                    await _botClient.SendTextMessageAsync(
                        chatId: e.Message.Chat.Id,
                        text: $"Incorrect command *:(*\n To show what the bot can do, type */menu*",
                        parseMode: ParseMode.Markdown
                    );
                break;
            }
        }

        private async void GetCallbackQueryData(object sender, CallbackQueryEventArgs callbackQueryEventArgs)
        {
            var query = callbackQueryEventArgs;

            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, "http://horoscope-api.herokuapp.com/horoscope/today/" + query.CallbackQuery.Data);

            HttpResponseMessage response = await _httpClient.SendAsync(request);
            response.EnsureSuccessStatusCode();

            var responeBody = await response.Content.ReadAsStringAsync();

            var horoscopeJson = JsonConvert.DeserializeObject<Dictionary<string, string>>(responeBody);
            await _botClient.SendTextMessageAsync(
                query.CallbackQuery.Message.Chat.Id,
                "*" + horoscopeJson["sunsign"].ToUpper() + "*" + $"\n{horoscopeJson["horoscope"]}",
                parseMode: ParseMode.Markdown
            );
            _botClient.OnCallbackQuery -= GetCallbackQueryData;
        }

        private async void SendTimetable(MessageEventArgs e)
        {

            var messageArgs = e.Message.Text.Split(" ");

            if(messageArgs.Length != 2)
            {
                await _botClient.SendTextMessageAsync(
                    chatId: e.Message.Chat,
                    text: "Please, provide a *group number*.",
                    parseMode: ParseMode.Markdown
                );
                return;
            }

            var request = new HttpRequestMessage(HttpMethod.Get, "https://journal.bsuir.by/api/v1/studentGroup/schedule?studentGroup=" + messageArgs[1]);

            var response = await _httpClient.SendAsync(request);

            var responseJSON = JsonConvert.DeserializeObject<JSONTimetable>(await response.Content.ReadAsStringAsync());
            string toUser = "";
            toUser += responseJSON.tomorrowDate + "\n";
            toUser += responseJSON.studentGroup.name + "\n";

            foreach(TomorrowSchedule schedule in responseJSON.tomorrowSchedules)
            {
                toUser += schedule.lessonType + "\n";
                try
                {
                toUser += schedule.auditory[0] + "\n";
                }
                catch (Exception exc)
                {
                    Console.WriteLine("Fizra");
                    Console.WriteLine(exc.StackTrace);
                }
                toUser += schedule.subject + "\n";
                toUser += schedule.lessonTime + "\n\n";
            }

            await _botClient.SendTextMessageAsync(
                chatId: e.Message.Chat.Id,
                text: toUser
            );

            UsersInfo.SetUser(e.Message.Chat.Id, messageArgs[1]);
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