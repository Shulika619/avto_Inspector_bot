package dev.shulika.avto_inspector_bot.bot.service.handler;

import dev.shulika.avto_inspector_bot.bot.utils.MessageUtils;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import static dev.shulika.avto_inspector_bot.bot.utils.BotConst.*;

@Service
public class CommandHandler {

    private final MessageUtils messageUtils;

    public CommandHandler(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    public void distribute(Message message) {
        String command = message.getText();
        Long chatId = message.getChatId();
        switch (command) {
            case COMMAND_START -> messageUtils.sendStartMessage(chatId);
            case COMMAND_CONTACT -> messageUtils.sendMessageWithText(chatId, CONTACT_MSG);
            default -> messageUtils.sendMessageWithText(chatId, UNSUPPORTED_COMMAND);
        }
    }

}
