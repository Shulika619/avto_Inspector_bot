package dev.shulika.avto_inspector_bot.bot.service.handler;

import dev.shulika.avto_inspector_bot.bot.cache.UsersAdsDataCache;
import dev.shulika.avto_inspector_bot.bot.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import java.util.Comparator;
import java.util.List;

import static dev.shulika.avto_inspector_bot.bot.utils.BotConst.WRONG_INPUT;

@Service
@Slf4j
public class PhotoHandler {

    private final UsersAdsDataCache dataCache;
    private final MessageHandler messageHandler;
    private final MessageUtils messageUtils;

    public PhotoHandler(UsersAdsDataCache dataCache, MessageHandler messageHandler, MessageUtils messageUtils) {
        this.dataCache = dataCache;
        this.messageHandler = messageHandler;
        this.messageUtils = messageUtils;
    }

    public void distribute(Message message) {

        Long chatId = message.getChatId();
        Integer state = dataCache.checkState(chatId);

        if (state == null) {
            log.info("--- PhotoHandler :: distribute:: state null");
            messageUtils.sendMessageWithText(chatId, WRONG_INPUT);
            return;
        } else if (state == 14) {
            log.info("-+++ PhotoHandler :: distribute:: state 14 photo");
            String mediaGroupId = message.getMediaGroupId();
            List<PhotoSize> listPhotoSize = message.getPhoto();
            PhotoSize maxSizePhoto = listPhotoSize.stream()
                    .max(Comparator.comparing(PhotoSize::getFileSize))
                    .orElse(null);
            String fileId = maxSizePhoto.getFileId();

            dataCache.addPhoto(chatId, fileId);

            if (mediaGroupId == null) {
                log.info("--- IN PhotoHandler :: distribute :: MediaGroupId null - single photo");
                messageHandler.finish(chatId);
            } else {
                log.info("+++ IN PhotoHandler :: distribute :: MediaGroupId - {}", mediaGroupId);
            }
        } else {
            log.info("+++ IN PhotoHandler :: distribute :: state < 14 not photo");
            messageHandler.repeat(message);
        }


    }

}
