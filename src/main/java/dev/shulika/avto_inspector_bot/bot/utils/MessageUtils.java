package dev.shulika.avto_inspector_bot.bot.utils;

import dev.shulika.avto_inspector_bot.bot.TelegramBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static dev.shulika.avto_inspector_bot.bot.utils.BotConst.*;

@Component
@Slf4j
public class MessageUtils {

    private TelegramBot telegramBot;

    public void registerBot(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void sendStartMessage(Long chatId){

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(Collections.singletonList(
                InlineKeyboardButton.builder()
                        .text(BTN_START_ADS)
                        .callbackData(BTN_START_ADS_CALLBACK + ":" + chatId)
                        .build()
        ));
        keyboard.add(Collections.singletonList(
                InlineKeyboardButton.builder()
                        .text(BTN_CONTACT)
                        .url(ADMIN_LINK)
                        .build()
        ));
        inlineKeyboardMarkup.setKeyboard(keyboard);

        var sendMessage = SendMessage.builder()
                .text(START_MSG)
                .chatId(chatId)
                .build();
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        executeSendMessage(sendMessage);
    }

    public void sendMessageWithText(Long chatId, String text) {
        SendMessage sendMessage = SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .build();
        executeSendMessage(sendMessage);
    }

    public void sendMessageQuestion(Long chatId, String text) {
        SendMessage sendMessage = SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .build();

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(Collections.singletonList(
                InlineKeyboardButton.builder()
                        .text(BTN_BACK)
                        .callbackData(BTN_BACK_CALLBACK + ":" + chatId)
                        .build()
        ));
        inlineKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        executeSendMessage(sendMessage);
    }

//    public void sendMessageWithBtn(Message message, String btnText, String callBackData, String text) {
//
//        var chatId = message.getChatId();
//        var userName = message.getChat().getUserName();
//
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
//        keyboard.add(Collections.singletonList(
//                InlineKeyboardButton.builder()
//                        .text(btnText)
//                        .callbackData(callBackData + ":" + userName)
//                        .build()
//        ));
//        inlineKeyboardMarkup.setKeyboard(keyboard);
//
//        var sendMessage = SendMessage.builder()
//                .text(text)
//                .chatId(chatId)
//                .build();
//        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
//        executeSendMessage(sendMessage);
//    }
//
//    public void SendMessageWithBtnLink(Message message, String btnText, String url, String text) {
//
//        var chatId = message.getChatId();
//        var userName = message.getChat().getUserName();
//
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
//        keyboard.add(Collections.singletonList(
//                InlineKeyboardButton.builder()
//                        .text(btnText)
//                        .url(url)
//                        .build()
//        ));
//        inlineKeyboardMarkup.setKeyboard(keyboard);
//
//        var sendMessage = SendMessage.builder()
//                .text(text)
//                .chatId(chatId)
//                .build();
//        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
//        executeSendMessage(sendMessage);
//    }

    private void executeSendMessage(SendMessage sendMessage) {
        try {
            telegramBot.execute(sendMessage);
            log.info("+++ IN MessageUtils :: executeSendMessage :: COMPLETE");
        } catch (TelegramApiException e) {
            log.error("--- IN MessageUtils :: executeSendMessage :: FAIL - ", e);
        }
    }

}