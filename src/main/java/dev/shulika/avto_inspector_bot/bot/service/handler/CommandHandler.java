package dev.shulika.avto_inspector_bot.bot.service.handler;

import dev.shulika.avto_inspector_bot.bot.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import static dev.shulika.avto_inspector_bot.bot.utils.BotConst.*;

@Service
public class CommandHandler {

    private final String adminLink;
    private final MessageUtils messageUtils;

    public CommandHandler(@Value("${bot.admin-link}") String adminLink, MessageUtils messageUtils) {
        this.adminLink = adminLink;
        this.messageUtils = messageUtils;
    }

    public void distribute(Message message) {
        String command = message.getText();
        Long chatId = message.getChatId();
        switch (command) {
            case COMMAND_START -> messageUtils.sendStartMessage(chatId);
            case COMMAND_CONTACT -> messageUtils.sendMessageWithText(chatId, adminLink);
            default -> messageUtils.sendMessageWithText(chatId, UNSUPPORTED_COMMAND);
        }
    }

}
