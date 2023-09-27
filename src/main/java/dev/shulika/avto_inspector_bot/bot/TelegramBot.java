package dev.shulika.avto_inspector_bot.bot;

import dev.shulika.avto_inspector_bot.bot.service.UpdateDispatcher;
import dev.shulika.avto_inspector_bot.bot.utils.MessageUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final UpdateDispatcher updateDispatcher;
    private final MessageUtils messageUtils;

    @Autowired
    public TelegramBot(
            TelegramBotsApi telegramBotsApi,
            @Value("${bot.name}") String botName,
            @Value("${bot.token}") String botToken,
            UpdateDispatcher updateDispatcher,
            MessageUtils messageUtils
    ) throws TelegramApiException {
        super(botToken);
        this.botName = botName;
        this.updateDispatcher = updateDispatcher;
        this.messageUtils = messageUtils;
        telegramBotsApi.registerBot(this);
    }

    @PostConstruct
    public void init() {
        messageUtils.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        updateDispatcher.distribute(update);
    }

}
