package dev.shulika.avto_inspector_bot.bot.service;

import dev.shulika.avto_inspector_bot.bot.service.handler.MessageHandler;
import dev.shulika.avto_inspector_bot.bot.service.handler.PhotoHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MultipleUpdatesDispatcher {

    private final PhotoHandler photoHandler;
    private final MessageHandler messageHandler;

    public MultipleUpdatesDispatcher(PhotoHandler photoHandler, MessageHandler messageHandler) {
        this.photoHandler = photoHandler;
        this.messageHandler = messageHandler;
    }

    public void distribute(List<Update> updates){
        log.info("--- IN MultipleUpdatesDispatcher :: distribute");
        List<String> fileIdList = updates.stream()
                .filter(update -> update.getMessage().hasPhoto())
                .peek(update -> photoHandler.distribute(update.getMessage()))
                .map(update -> update.getMessage().getPhoto().stream()
                        .max(Comparator.comparing(PhotoSize::getFileSize))
                        .orElse(null)
                        .getFileId())
                .collect(Collectors.toList());
        messageHandler.finish(updates.get(0).getMessage().getChatId());
    }

}
