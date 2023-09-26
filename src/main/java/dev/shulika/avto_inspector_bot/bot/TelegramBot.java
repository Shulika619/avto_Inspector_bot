package dev.shulika.avto_inspector_bot.bot;

import dev.shulika.avto_inspector_bot.bot.Service.UpdateDispatcher;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final String botName;
    private UpdateDispatcher updateDispatcher;

    public TelegramBot(
            TelegramBotsApi telegramBotsApi,
            @Value("${bot.name}") String botName,
            @Value("${bot.token}") String botToken,
            UpdateDispatcher updateDispatcher
    ) throws TelegramApiException {
        super(botToken);
        this.botName = botName;
        this.updateDispatcher = updateDispatcher;
        telegramBotsApi.registerBot(this);
    }

    @PostConstruct
    public void init() {
        updateDispatcher.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        updateDispatcher.processUpdate(update);
    }

}
