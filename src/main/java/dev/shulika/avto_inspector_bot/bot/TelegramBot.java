package dev.shulika.avto_inspector_bot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static dev.shulika.avto_inspector_bot.bot.BotConst.*;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final String botName;

    public TelegramBot(
            TelegramBotsApi telegramBotsApi,
            @Value("${bot.name}") String botName,
            @Value("${bot.token}") String botToken
    ) throws TelegramApiException {
        super(botToken);
        this.botName = botName;
        telegramBotsApi.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            var message = update.getMessage();
            if (message.hasText()) {
                var messageText = message.getText();
                log.info("+++ IN TelegramBot :: onUpdateReceived :: hasText - {} +++", messageText);
                switch (messageText) {
                    case COMMAND_START -> sendDefaultMessage(update.getMessage(), HELP_MSG);
                    case COMMAND_CONTACT -> sendDefaultMessage(update.getMessage(), CONTACT_MSG);
                    case COMMAND_HELP -> sendDefaultMessage(update.getMessage(), HELP_MSG);
//                case "/start@avto_Inspector_bot" -> sendDefaultMessage(update.getMessage(), HELP_MSG);
//                    default -> sendDefaultMessage(update.getMessage(), UNSUPPORTED_MSG);
                }
            } else {
                log.error("--- IN TelegramBot :: onUpdateReceived:: UNSUPPORTED Message - {} ---", message);
            }
        } else if (update.hasCallbackQuery()) {
            log.info("+++ IN TelegramBot :: onUpdateReceived :: hasCallbackQuery +++");
        } else {
            log.error("--- IN TelegramBot :: onUpdateReceived:: update not Message/Callback - {} ---", update);
        }
    }

    private void sendDefaultMessage(Message message, String sendText) {
        var sendMsg = SendMessage.builder()
                .text(sendText)
                .chatId(message.getChatId())
                .build();
        try {
            execute(sendMsg);
            log.info("+++ IN TelegramBot :: sendDefaultMessage:: chatId - {} :: text - {}",
                    message.getChatId(), sendText);
        } catch (TelegramApiException e) {
            log.error("--- IN TelegramBot :: sendDefaultMessage FAIL :: message - {}", e.getMessage());
        }
    }
    
}
