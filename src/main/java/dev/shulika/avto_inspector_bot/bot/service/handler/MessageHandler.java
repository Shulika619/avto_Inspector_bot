package dev.shulika.avto_inspector_bot.bot.service.handler;

import dev.shulika.avto_inspector_bot.bot.cache.UsersAdsDataCache;
import dev.shulika.avto_inspector_bot.bot.model.UserAdData;
import dev.shulika.avto_inspector_bot.bot.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import static dev.shulika.avto_inspector_bot.bot.utils.BotConst.*;

@Service
@Slf4j
public class MessageHandler {

    private final UsersAdsDataCache dataCache;
    private final MessageUtils messageUtils;

    public MessageHandler(UsersAdsDataCache dataCache, MessageUtils messageUtils) {
        this.dataCache = dataCache;
        this.messageUtils = messageUtils;
    }

    public void start(Message message) {
        log.info("--- MessageHandler :: start :: state null - create and set state1 now");
        dataCache.createUserAdData(message.getChatId(), message.getChat().getUserName());
        distribute(message);
    }

    public void back(Message message) {
        Long chatId = message.getChatId();
        Integer state = dataCache.checkState(chatId);
        dataCache.decrementState(chatId, state);
        distribute(message);
    }

    public void runQuestion(Long chatId, Integer state, String question) {
        dataCache.incrementState(chatId, state);
        messageUtils.sendMessageQuestion(chatId, question);
    }

    public void distribute(Message message) {

        Long chatId = message.getChatId();
        Integer state = dataCache.checkState(chatId);
        System.out.println("===== State = " + state);   // TODO: remove
        String text = message.getText();
        UserAdData userAdData = dataCache.getDataMap().get(chatId);

        switch (state) {
            case 0 -> {
                dataCache.remove(chatId);
                messageUtils.sendStartMessage(chatId);
            }
            case 1 -> {
                log.info("+++ MessageHandler :: distribute:: case1 now");
                runQuestion(chatId, state, QUESTION_1);
            }
            case 2 -> {
                log.info("+++ MessageHandler :: distribute:: case2 now");
                userAdData.setMakeModel(text);
                runQuestion(chatId, state, QUESTION_2);
            }
            case 3 -> {
                log.info("+++ MessageHandler :: distribute:: case3 now");
                userAdData.setPrice(text);
                runQuestion(chatId, state, QUESTION_3);
            }
            case 4 -> {
                log.info("+++ MessageHandler :: distribute:: case4 now");
                userAdData.setCity(text);
                runQuestion(chatId, state, QUESTION_4);
            }
            case 5 -> {
                log.info("+++ MessageHandler :: distribute:: case5 now");
                userAdData.setYear(text);
                runQuestion(chatId, state, QUESTION_5);
            }
            case 6 -> {
                log.info("+++ MessageHandler :: distribute:: case6 now");
                userAdData.setKm(text);
                runQuestion(chatId, state, QUESTION_6);
            }
            case 7 -> {
                log.info("+++ MessageHandler :: distribute:: case7 now");
                userAdData.setCubicCapacity(text);
                runQuestion(chatId, state, QUESTION_7);
            }
            case 8 -> {
                log.info("+++ MessageHandler :: distribute:: case8 now");
                userAdData.setFuelType(text);
                runQuestion(chatId, state, QUESTION_8);
            }
            case 9 -> {
                log.info("+++ MessageHandler :: distribute:: case9 now");
                userAdData.setTransmission(text);
                runQuestion(chatId, state, QUESTION_9);
            }
            case 10 -> {
                log.info("+++ MessageHandler :: distribute:: case10 now");
                userAdData.setOwner(text);
                runQuestion(chatId, state, QUESTION_10);
            }
            case 11 -> {
                log.info("+++ MessageHandler :: distribute:: case11 now");
                userAdData.setDescription(text);
                runQuestion(chatId, state, QUESTION_11);
            }
            case 12 -> {
                log.info("+++ MessageHandler :: distribute:: case12 now");
                userAdData.setPhone(text);
                runQuestion(chatId, state, QUESTION_12);
            }
            case 13 -> {
                log.info("+++ MessageHandler :: distribute:: case13 now :: Foto");
                userAdData.setMessengers(text);
                runQuestion(chatId, state, QUESTION_13);
            }
            default -> {
                log.info("--- MessageHandler :: distribute:: default = " + userAdData);
                messageUtils.sendMessageWithText(chatId, userAdData.toString());
                dataCache.remove(chatId);
            }
        }
    }

}
