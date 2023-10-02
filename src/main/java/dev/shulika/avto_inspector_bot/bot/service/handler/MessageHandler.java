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

    public void distribute(Message message) {

        Long chatId = message.getChatId();
        Integer state = dataCache.checkState(chatId);
        System.out.println("===== State = " + state);   // TODO: remove

        if (state == null) {
            log.info("--- MessageHandler :: distribute :: state null - create and set state1 now");
            dataCache.createUserAdData(chatId, message.getChat().getUserName());
            messageUtils.sendMessageQuestion(chatId, QUESTION_1);
            return;
        }

        String text = message.getText();
        UserAdData userAdData = dataCache.getDataMap().get(chatId);

        switch (state) {
            case 0 -> {
                dataCache.remove(chatId);
                messageUtils.sendStartMessage(chatId);
            }
            case 1 -> {
                log.info("+++ MessageHandler :: distribute:: case1 now");
                userAdData.setMakeModel(text);
                dataCache.incrementState(chatId, state);
                messageUtils.sendMessageQuestion(chatId, QUESTION_2);
            }
            case 2 -> {
                log.info("+++ MessageHandler :: distribute:: case2 now");
                userAdData.setPrice(text);
                dataCache.incrementState(chatId, state);
                messageUtils.sendMessageQuestion(chatId, QUESTION_3);
            }
            case 3 -> {
                log.info("+++ MessageHandler :: distribute:: case3 now");
                userAdData.setCity(text);
                dataCache.incrementState(chatId, state);
                messageUtils.sendMessageQuestion(chatId, QUESTION_4);
            }
            case 4 -> {
                log.info("+++ MessageHandler :: distribute:: case4 now");
                userAdData.setYear(text);
                dataCache.incrementState(chatId, state);
                messageUtils.sendMessageQuestion(chatId, QUESTION_5);
            }
            default -> log.info("--- MessageHandler :: distribute:: default = " + userAdData);
        }

    }

    public void back(Message message) {
        Long chatId = message.getChatId();
        Integer state = dataCache.checkState(chatId);
        dataCache.decrementState(chatId, state);
        distribute(message);
    }

}
