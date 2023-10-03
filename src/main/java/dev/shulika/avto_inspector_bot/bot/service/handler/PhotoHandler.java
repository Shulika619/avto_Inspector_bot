package dev.shulika.avto_inspector_bot.bot.service.handler;

import dev.shulika.avto_inspector_bot.bot.cache.UsersAdsDataCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class PhotoHandler {

    UsersAdsDataCache dataCache;

    public PhotoHandler(UsersAdsDataCache dataCache) {
        this.dataCache = dataCache;
    }

    public void distribute(Message message) {
        Long chatId = message.getChatId();
        String text = message.getCaption();
        String mediaGroupId = message.getMediaGroupId();
        List<PhotoSize> listPhotoSize = message.getPhoto();
        PhotoSize maxSizePhoto = listPhotoSize.stream()
                .max(Comparator.comparing(PhotoSize::getFileSize))
                .orElse(null);
        String fileId = maxSizePhoto.getFileId();

        if(mediaGroupId.isEmpty()){
            log.info("--- IN PhotoHandler :: distribute :: MediaGroupId null");
            // TODO: Process 1 photo
        } else {
            log.info("+++ IN PhotoHandler :: distribute :: MediaGroupId - {}", mediaGroupId);
            dataCache.addPhoto(chatId, fileId);

        }
    }

}
