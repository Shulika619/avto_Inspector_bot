package dev.shulika.avto_inspector_bot.bot.service.handler;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import static dev.shulika.avto_inspector_bot.bot.utils.BotConst.BTN_BACK_CALLBACK;
import static dev.shulika.avto_inspector_bot.bot.utils.BotConst.BTN_START_ADS_CALLBACK;

@Service
public class CallbackQueryHandler {

    private final MessageHandler messageHandler;

    public CallbackQueryHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void distribute(CallbackQuery callbackQuery) {
        String queryData = callbackQuery.getData();
        Message message = callbackQuery.getMessage();
        String[] param = queryData.split(":");
        String action = param[0];
        String value = param[1];

        switch (action) {
            case BTN_START_ADS_CALLBACK -> messageHandler.start(message);
            case BTN_BACK_CALLBACK -> messageHandler.back(message, Integer.parseInt(value));
        }
    }

}
