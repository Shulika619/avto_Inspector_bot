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
        log.info("++> MessageHandler :: start :: state null - create and set state1 now");
        dataCache.createUserAdData(message.getChatId(), message.getChat().getUserName());
        distribute(message);
    }

    public void back(Message message, Integer state) {
        log.info("<++ MessageHandler :: back:: from state-{} to state-{}", state, state-1);
        Long chatId = message.getChatId();
        dataCache.decrementState(chatId, state);
        if (state < 3)
            distribute(message);
        else
            messageUtils.sendMessageQuestion(chatId, QUESTIONS_LIST.get(state-2), state-1);
    }

    public void runQuestion(Long chatId, Integer state, Integer questionNumber) {
        dataCache.incrementState(chatId, state);
        messageUtils.sendMessageQuestion(chatId, QUESTIONS_LIST.get(questionNumber-1), state);
    }

    public void distribute(Message message) {

        Long chatId = message.getChatId();
        Integer state = dataCache.checkState(chatId);
        String text = message.getText();
        UserAdData userAdData = dataCache.getDataMap().get(chatId);
        log.info("+++ MessageHandler :: distribute:: state-{} now", state);

        switch (state) {
            case 0 -> {
                dataCache.removeUserAdData(chatId);
                messageUtils.sendStartMessage(chatId);
            }
            case 1 -> {
                runQuestion(chatId, state, 1);
            }
            case 2 -> {
                userAdData.setMakeModel(text);
                runQuestion(chatId, state, 2);
            }
            case 3 -> {
                userAdData.setPrice(text);
                runQuestion(chatId, state, 3);
            }
            case 4 -> {
                userAdData.setCity(text);
                runQuestion(chatId, state, 4);
            }
            case 5 -> {
                userAdData.setYear(text);
                runQuestion(chatId, state, 5);
            }
            case 6 -> {
                userAdData.setKm(text);
                runQuestion(chatId, state, 6);
            }
            case 7 -> {
                userAdData.setCubicCapacity(text);
                runQuestion(chatId, state, 7);
            }
            case 8 -> {
                userAdData.setFuelType(text);
                runQuestion(chatId, state, 8);
            }
            case 9 -> {
                userAdData.setTransmission(text);
                runQuestion(chatId, state, 9);
            }
            case 10 -> {
                userAdData.setOwner(text);
                runQuestion(chatId, state, 10);
            }
            case 11 -> {
                userAdData.setDescription(text);
                runQuestion(chatId, state, 11);
            }
            case 12 -> {
                userAdData.setPhone(text);
                runQuestion(chatId, state, 12);
            }
            case 13 -> {
                userAdData.setMessengers(text);
                runQuestion(chatId, state, 13);
            }
            default -> {
                log.info("--- MessageHandler :: distribute:: default");
                messageUtils.sendMessageWithText(chatId, userAdData.toString());
                System.out.println("==== FOTOS - " + userAdData.getPhoto());
                dataCache.removeUserAdData(chatId);
            }
        }
    }

}
