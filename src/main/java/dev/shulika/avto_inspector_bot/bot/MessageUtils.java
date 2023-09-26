package dev.shulika.avto_inspector_bot.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MessageUtils {
    public SendMessage generateMessageWithText(Message message, String text) {
        return SendMessage.builder()
                .text(text)
                .chatId(message.getChatId())
                .build();
    }

    public SendMessage generateMessageWithBtn(Message message, String btnText, String callBackData, String text) {

        var chatId = message.getChatId();
        var userName = message.getChat().getUserName();

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(Collections.singletonList(
                InlineKeyboardButton.builder()
                        .text(btnText)
                        .callbackData(callBackData + ":" + userName)
                        .build()
        ));
        inlineKeyboardMarkup.setKeyboard(keyboard);

        var sendMessage = SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .build();
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage generateMessageWithBtnLink(Message message, String btnText, String url, String text) {

        var chatId = message.getChatId();
        var userName = message.getChat().getUserName();

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(Collections.singletonList(
                InlineKeyboardButton.builder()
                        .text(btnText)
                        .url(url)
                        .build()
        ));
        inlineKeyboardMarkup.setKeyboard(keyboard);

        var sendMessage = SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .build();
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}
