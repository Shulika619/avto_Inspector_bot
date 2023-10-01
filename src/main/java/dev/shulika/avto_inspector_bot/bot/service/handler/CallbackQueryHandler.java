package dev.shulika.avto_inspector_bot.bot.service.handler;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import static dev.shulika.avto_inspector_bot.bot.utils.BotConst.BTN_START_ADS_CALLBACK;

@Service
public class CallbackQueryHandler {

    public void distribute(CallbackQuery callbackQuery) {
        var queryData = callbackQuery.getData();
        var message = callbackQuery.getMessage();
        String[] param = queryData.split(":");
        String action = param[0];
        String value = param[1];

        switch (action) {
            case BTN_START_ADS_CALLBACK -> System.out.println("BTN_START_ADS_CALLBACK" + action + value);
//            case BTN_PAID_CALLBACK -> System.out.println("BTN_PAID_CALLBACK");
        }
    }

}
