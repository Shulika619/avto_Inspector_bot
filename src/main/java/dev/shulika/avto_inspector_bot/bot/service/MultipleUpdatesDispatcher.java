package dev.shulika.avto_inspector_bot.bot.service;

import dev.shulika.avto_inspector_bot.bot.cache.UsersAdsDataCache;
import dev.shulika.avto_inspector_bot.bot.service.handler.MessageHandler;
import dev.shulika.avto_inspector_bot.bot.service.handler.PhotoHandler;
import dev.shulika.avto_inspector_bot.bot.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static dev.shulika.avto_inspector_bot.bot.utils.BotConst.WRONG_INPUT;

@Service
@Slf4j
public class MultipleUpdatesDispatcher {

    private final PhotoHandler photoHandler;
    private final MessageHandler messageHandler;
    private final UsersAdsDataCache dataCache;
    private final MessageUtils messageUtils;

    public MultipleUpdatesDispatcher(PhotoHandler photoHandler,
                                     MessageHandler messageHandler,
                                     UsersAdsDataCache dataCache,
                                     MessageUtils messageUtils) {
        this.photoHandler = photoHandler;
        this.messageHandler = messageHandler;
        this.dataCache = dataCache;
        this.messageUtils = messageUtils;
    }

    public void distribute(List<Update> updates) {
        log.info("+++ IN MultipleUpdatesDispatcher :: distribute");

        Long chatId = updates.get(0).getMessage().getChatId();
        Integer state = dataCache.checkState(chatId);

        if (state == null) {
            log.info("--- MultipleUpdatesDispatcher :: distribute:: state null");
            messageUtils.sendMessageWithText(chatId, WRONG_INPUT);
        } else if (state == 14) {
            log.info("+++ MultipleUpdatesDispatcher :: distribute:: state 14 photo");
            updates.stream()
                    .filter(update -> update.getMessage().hasPhoto())
                    .peek(update -> photoHandler.distribute(update.getMessage()))
                    .map(update -> update.getMessage().getPhoto().stream()
                            .max(Comparator.comparing(PhotoSize::getFileSize))
                            .orElse(null)
                            .getFileId())
                    .collect(Collectors.toList());
            messageHandler.finish(updates.get(0).getMessage().getChatId());
        } else {
            log.info("+++ IN MultipleUpdatesDispatcher :: distribute :: state < 14 not photo");
            messageHandler.repeat(updates.get(0).getMessage());
        }
    }

}
