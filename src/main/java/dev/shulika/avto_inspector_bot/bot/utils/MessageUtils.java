package dev.shulika.avto_inspector_bot.bot.utils;

import dev.shulika.avto_inspector_bot.bot.TelegramBot;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static dev.shulika.avto_inspector_bot.bot.utils.BotConst.*;

@Component
@Slf4j
public class MessageUtils {

    private TelegramBot telegramBot;

    private final Long ADMIN_CHAT_ID;
    private final String ADMIN_LINK;

    public MessageUtils(@Value("${bot.admin-chatId}") Long ADMIN_CHAT_ID,
                        @Value("${bot.admin-link}") String ADMIN_LINK) {
        this.ADMIN_CHAT_ID = ADMIN_CHAT_ID;
        this.ADMIN_LINK = ADMIN_LINK;
    }

    public void registerBot(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void sendMessageWithText(Long chatId, String text) {
        SendMessage sendMessage = SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .build();
        executeSendMessage(sendMessage);
    }

    public void sendStartMessage(Long chatId) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(Collections.singletonList(
                InlineKeyboardButton.builder()
                        .text(BTN_START_ADS)
                        .callbackData(BTN_START_ADS_CALLBACK)
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
                        .callbackData(BTN_BACK_CALLBACK)
                        .build()
        ));
        inlineKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        executeSendMessage(sendMessage);
    }

    public void sendPhotoMessageToAdmin(String text, String fileId) {
//        int uniqueID = new Random().nextInt(1000, 9999);
        SendPhoto sendPhoto = SendPhoto.builder()
                .chatId(ADMIN_CHAT_ID)
                .photo(new InputFile(fileId))
                .build();

        if (text.length() > 4000) {
            String cutCaption = StringUtils.abbreviate(text, 4000);
            sendPhoto.setCaption(cutCaption);
        } else {
            sendPhoto.setCaption(text);
        }

        executeSendPhoto(sendPhoto);
        sendMessageWithText(ADMIN_CHAT_ID, text);
    }

    public void sendPhotoMediaGroupToAdmin(String text, List<String> filesId) {
//        int uniqueID = new Random().nextInt(1000, 9999);
        List<InputMedia> medias = filesId.stream()
                .map(fileId -> InputMediaPhoto.builder()
                        .media(fileId)
                        .build())
                .limit(10)
                .collect(Collectors.toList());

        SendMediaGroup sendMediaGroup = SendMediaGroup.builder()
                .chatId(ADMIN_CHAT_ID)
                .medias(medias)
                .build();
        try {
            telegramBot.execute(sendMediaGroup);
            log.info("+++ IN MessageUtils :: execute sendPhotoMediaGroup :: COMPLETE");
        } catch (TelegramApiException e) {
            log.error("--- MessageUtils :: sendPhotoMediaGroup :: FAIL - Can't send", e);
        }

        if (text.length() > 4000) {
            String cutCaption = StringUtils.abbreviate(text, 4000);
            sendMessageWithText(ADMIN_CHAT_ID, cutCaption);
        } else {
            sendMessageWithText(ADMIN_CHAT_ID, text);
        }
    }

    @SneakyThrows
    public void sendFinishMessage(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(Collections.singletonList(
                InlineKeyboardButton.builder()
                        .text(BTN_SEND_PROOF)
                        .url(ADMIN_LINK)
                        .build()
        ));
        keyboard.add(Collections.singletonList(
                InlineKeyboardButton.builder()
                        .text(BTN_UPDATE_ADS)
                        .url(ADMIN_LINK)
                        .build()
        ));
        keyboard.add(Collections.singletonList(
                InlineKeyboardButton.builder()
                        .text(BTN_START_ADS)
                        .callbackData(BTN_START_ADS_CALLBACK)
                        .build()
        ));
        inlineKeyboardMarkup.setKeyboard(keyboard);

        var imgPath = "/static/images/ads_finish.jpg";
        SendPhoto sendPhoto = SendPhoto.builder()
                .chatId(chatId)
                .photo(new InputFile(getClass().getClassLoader().getResourceAsStream(imgPath), imgPath))
//                .photo(new InputFile(new File("src/main/resources/static/images/ads_finish.jpg")))
                .caption(FINISH_MSG)
                .parseMode(ParseMode.MARKDOWNV2)
                .build();
        sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
        executeSendPhoto(sendPhoto);
    }

    private void executeSendPhoto(SendPhoto sendPhoto) {
        try {
            telegramBot.execute(sendPhoto);
            log.info("+++ IN MessageUtils :: executeSendPhoto :: COMPLETE");
        } catch (TelegramApiException e) {
            log.error("--- IN MessageUtils :: executeSendPhoto :: FAIL - ", e);
        }
    }

    private void executeSendMessage(SendMessage sendMessage) {
        try {
            telegramBot.execute(sendMessage);
            log.info("+++ IN MessageUtils :: executeSendMessage :: COMPLETE");
        } catch (TelegramApiException e) {
            log.error("--- IN MessageUtils :: executeSendMessage :: FAIL - ", e);
        }
    }

}
