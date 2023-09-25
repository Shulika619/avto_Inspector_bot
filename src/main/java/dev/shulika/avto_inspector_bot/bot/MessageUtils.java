package dev.shulika.avto_inspector_bot.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class MessageUtils {
    public SendMessage generateMessageWithText(Message message, String text) {
        return SendMessage.builder()
                .text(text)
                .chatId(message.getChatId())
                .build();
    }
}
