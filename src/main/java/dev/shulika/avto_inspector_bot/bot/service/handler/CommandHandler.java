package dev.shulika.avto_inspector_bot.bot.service.handler;

import dev.shulika.avto_inspector_bot.bot.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import static dev.shulika.avto_inspector_bot.bot.utils.BotConst.*;

@Service
public class CommandHandler {

    private final MessageUtils messageUtils;

    @Autowired
    public CommandHandler(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    public void distribute(Message message) {
        String command = message.getText();
        switch (command) {
            case COMMAND_START -> messageUtils.SendMessageWithBtnLink(
                    message, BTN_PAID, ADMIN_LINK, HELP_MSG);
            case COMMAND_SEND -> messageUtils.sendMessageWithText(message, CONTACT_MSG);
            case COMMAND_CONTACT -> messageUtils.sendMessageWithText(message, CONTACT_MSG);
            case COMMAND_HELP -> messageUtils.sendMessageWithText(message, HELP_MSG);
        }
    }

}
