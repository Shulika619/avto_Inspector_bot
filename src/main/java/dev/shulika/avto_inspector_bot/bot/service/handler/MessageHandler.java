package dev.shulika.avto_inspector_bot.bot.service.handler;

import dev.shulika.avto_inspector_bot.bot.cache.UsersAdsDataCache;
import dev.shulika.avto_inspector_bot.bot.model.UserAdData;
import dev.shulika.avto_inspector_bot.bot.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

import static dev.shulika.avto_inspector_bot.bot.utils.BotConst.QUESTION_1;
import static dev.shulika.avto_inspector_bot.bot.utils.BotConst.QUESTION_2;

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
        Map<Long, UserAdData> dataMap = dataCache.getDataMap();
        UserAdData userAdData = dataMap.get(chatId);

        if (userAdData == null) {
            log.info("--- MessageHandler :: distribute :: userAdData null - set state1 now");
            dataMap.put(chatId, UserAdData.builder()
                    .state(1)
                    .userName(message.getChat().getUserName())
                    .build());
            messageUtils.sendMessageQuestion(chatId, QUESTION_1);
            return;
        }
        Integer state = userAdData.getState();
        String text = message.getText();
        switch (state) {
            case 1 -> {
                // TODO: save text
                // TODO: save newState
                messageUtils.sendMessageQuestion(chatId, QUESTION_1);
            }
            case 2 -> {

                messageUtils.sendMessageQuestion(chatId, QUESTION_2);
            }
        }
    }


    public void back(Message message) {
        Long chatId = message.getChatId();
        Map<Long, UserAdData> dataMap = dataCache.getDataMap();
        UserAdData userAdData = dataMap.get(chatId);
        Integer state = userAdData.getState();

        if (state.equals(1)) {
            log.info("<-- MessageHandler :: back :: userAdData.state = {} - set null now", state);
            dataMap.put(chatId, null);
            messageUtils.sendStartMessage(chatId);
        } else {
            log.info("<-- MessageHandler :: back :: userAdData.state = {} - set {} now", state, state - 1);
            dataMap.put(chatId, userAdData.changeState(state - 1));
            distribute(message);
        }
    }

}
