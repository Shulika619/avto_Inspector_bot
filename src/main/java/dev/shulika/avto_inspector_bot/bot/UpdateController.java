package dev.shulika.avto_inspector_bot.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static dev.shulika.avto_inspector_bot.bot.BotConst.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UpdateController {

    private TelegramBot telegramBot;
    private final MessageUtils messageUtils;

    public void registerBot(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void processUpdate(Update update) {
        if (update == null) {
            log.error("--- IN UpdateController :: processUpdate update :: Received update is null");
            return;
        } else if (update.hasMessage()) {
            distributeMessagesByType(update);
        } else if (update.hasCallbackQuery()) {
            distributeCallbackQuery(update);
        } else {
            log.error("--- IN UpdateController :: processUpdate update :: is not Message/Callback - {} ---", update);
        }
    }

    private void distributeMessagesByType(Update update) {
        if (update.getMessage().hasText()) {
            processMessageText(update);
        } else if (update.getMessage().hasPhoto()) {
            processMessagePhoto(update);
        } else {
            log.error("--- IN UpdateController :: distributeMessagesByType :: Some text - UNSUPPORTED Message");
//            executeSendMessage(messageUtils.generateMessageWithText(update.getMessage(), UNSUPPORTED_MSG));
        }
    }

    private void processMessageText(Update update) {
        var messageText = update.getMessage().getText();
        log.info("+++ IN UpdateController :: processMessageText :: hasText - {}", messageText);
        switch (messageText) {
            case COMMAND_START -> executeSendMessage(messageUtils.generateMessageWithBtnLink(
                    update.getMessage(), BTN_PAID, ADMIN_LINK, HELP_MSG
            ));
            case COMMAND_CONTACT ->
                    executeSendMessage(messageUtils.generateMessageWithText(update.getMessage(), CONTACT_MSG));
            case COMMAND_HELP ->
                    executeSendMessage(messageUtils.generateMessageWithText(update.getMessage(), HELP_MSG));
            case COMMAND_POST ->
                    executeSendMessage(messageUtils.generateMessageWithText(update.getMessage(), HELP_MSG));
        }
    }

    private void processMessagePhoto(Update update) {
        log.info("+++ IN UpdateController :: processMessagePhoto :: hasPhoto");
    }

    private void distributeCallbackQuery(Update update) {
        log.info("+++ IN UpdateController :: processUpdate :: hasCallbackQuery");
        var query = update.getCallbackQuery();
        var queryData = query.getData();
        var message = query.getMessage();
        String[] param = queryData.split(":");
        String action = param[0];
        String value = param[1];

        switch (action) {
            case BTN_PAID_CALLBACK -> System.out.println("BTN_PAID_CALLBACK");
        }
    }

    private void executeSendMessage(SendMessage sendMessage) {
        try {
            telegramBot.execute(sendMessage);
            log.info("+++ IN UpdateController :: ExecuteSendMessage :: COMPLETE");
        } catch (TelegramApiException e) {
            log.error("--- IN UpdateController :: ExecuteSendMessage :: FAIL - ", e);
        }
    }
}