package dev.shulika.avto_inspector_bot.bot.service;

import dev.shulika.avto_inspector_bot.bot.service.handler.CallbackQueryHandler;
import dev.shulika.avto_inspector_bot.bot.service.handler.CommandHandler;
import dev.shulika.avto_inspector_bot.bot.service.handler.MessageHandler;
import dev.shulika.avto_inspector_bot.bot.service.handler.PhotoHandler;
import dev.shulika.avto_inspector_bot.bot.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static dev.shulika.avto_inspector_bot.bot.utils.BotConst.UNSUPPORTED_FORMAT;

@Service
@Slf4j
public class UpdateDispatcher {

    private final MessageUtils messageUtils;
    private final MessageHandler messageHandler;
    private final CommandHandler commandHandler;
    private final CallbackQueryHandler callbackQueryHandler;
    private final PhotoHandler photoHandler;

    public UpdateDispatcher(MessageUtils messageUtils,
                            MessageHandler messageHandler,
                            CommandHandler commandHandler,
                            CallbackQueryHandler callbackQueryHandler,
                            PhotoHandler photoHandler) {
        this.messageUtils = messageUtils;
        this.messageHandler = messageHandler;
        this.commandHandler = commandHandler;
        this.callbackQueryHandler = callbackQueryHandler;
        this.photoHandler = photoHandler;
    }

    public void distribute(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                if (message.getText().charAt(0) == '/') {
                    log.info("+++ IN UpdateDispatcher :: distribute :: hasMessage :: hasText :: commandHandler - {}", message.getText());
                    commandHandler.distribute(message);
                } else {
                    log.info("+++ IN UpdateDispatcher :: distribute :: hasMessage :: hasText :: messageHandler");
                    messageHandler.distribute(message);
                }
            } else if (update.getMessage().hasPhoto()) {
                log.info("+++ IN UpdateDispatcher :: distribute :: hasMessage :: hasPhoto :: photoHandler");
                photoHandler.distribute(message);
            } else {
                log.info("--- IN UpdateDispatcher :: distribute :: hasMessage :: UNSUPPORTED FORMAT");
                messageUtils.sendMessageWithText(message.getChatId(), UNSUPPORTED_FORMAT);
            }
        } else if (update.hasCallbackQuery()) {
            log.info("+++ IN UpdateDispatcher :: distribute :: hasCallbackQuery");
            callbackQueryHandler.distribute(update.getCallbackQuery());
        } else {
            log.error("--- IN UpdateDispatcher :: distribute :: is not Message/Callback - {}", update);
        }
    }

}
