package dev.shulika.avto_inspector_bot.bot.service.handler;

import dev.shulika.avto_inspector_bot.bot.cache.UsersAdsDataCache;
import dev.shulika.avto_inspector_bot.bot.model.UserAdData;
import dev.shulika.avto_inspector_bot.bot.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

import static dev.shulika.avto_inspector_bot.bot.utils.BotConst.QUESTIONS_LIST;
import static dev.shulika.avto_inspector_bot.bot.utils.BotConst.WRONG_INPUT;

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
        dataCache.createUserAdData(
                message.getChatId(),
                message.getChat().getFirstName(),
                message.getChat().getUserName());
        distribute(message);
    }

    public void repeat(Message message) {
        log.info("<++> MessageHandler :: repeat :: state back -1");
        Long chatId = message.getChatId();
        dataCache.decrementState(chatId);
        Integer state = dataCache.checkState(chatId);

        messageUtils.sendMessageQuestion(chatId, QUESTIONS_LIST.get(state - 1));
        dataCache.incrementState(chatId, state);
    }

    public void back(Message message) {
        Long chatId = message.getChatId();
        dataCache.decrementState(chatId);
        Integer state = dataCache.checkState(chatId);
        log.info("<++ MessageHandler :: back:: from state-{} to state-{}", state + 1, state);

        if (state <= 1) {
            log.info("<++ MessageHandler :: back:: state <= 1 :: removeUserAdData and sendStartMessage");
            dataCache.removeUserAdData(chatId);
            messageUtils.sendStartMessage(chatId);
        } else {
            messageUtils.sendMessageQuestion(chatId, QUESTIONS_LIST.get(state - 2));
        }
    }

    public void runQuestion(Long chatId, Integer state, Integer questionNumber) {
        dataCache.incrementState(chatId, state);
        messageUtils.sendMessageQuestion(chatId, QUESTIONS_LIST.get(questionNumber - 1));
    }

    public void distribute(Message message) {

        Long chatId = message.getChatId();
        Integer state = dataCache.checkState(chatId);
        if (state == null) {
            log.info("--- MessageHandler :: distribute:: state null");
            messageUtils.sendMessageWithText(chatId, WRONG_INPUT);
            return;
        }

        String text = message.getText();
        UserAdData userAdData = dataCache.getDataMap().get(chatId);

        switch (state) {
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
            }
        }
    }

    public void finish(Long chatId) {
        log.info("+++ MessageHandler :: finish :: send msg and delete cache");
        UserAdData userAdData = dataCache.getDataMap().get(chatId);
        Integer contentSize = userAdData.getPhoto().size();
        List<String> photosId = userAdData.getPhoto();
        if (contentSize == 1) {
            messageUtils.sendPhotoMessageToAdmin(userAdData.toString(), photosId.get(0));
        } else {
            messageUtils.sendPhotoMediaGroupToAdmin(userAdData.toString(), photosId);
        }
        messageUtils.sendFinishMessage(chatId);
        dataCache.removeUserAdData(chatId);
    }

}
