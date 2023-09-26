package dev.shulika.avto_inspector_bot.bot.Service.handler;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import static dev.shulika.avto_inspector_bot.bot.Utils.BotConst.BTN_PAID_CALLBACK;

@Service
public class CallbackQueryHandler {

    public void distribute(CallbackQuery callbackQuery) {
        var queryData = callbackQuery.getData();
        var message = callbackQuery.getMessage();
        String[] param = queryData.split(":");
        String action = param[0];
        String value = param[1];

        switch (action) {
            case BTN_PAID_CALLBACK -> System.out.println("BTN_PAID_CALLBACK");
        }
    }

}