package dev.shulika.avto_inspector_bot.bot.cache;

import dev.shulika.avto_inspector_bot.bot.model.UserAdData;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Component
public class UsersAdsDataCache {
    private Map<Long, UserAdData> dataMap = new HashMap<>();

    public Integer checkState(Long chatId){
        UserAdData userAdData = dataMap.get(chatId);
        if(userAdData == null) {
            return null;
        }
        return userAdData.getState();
    }

    public void createUserAdData(Long chatId, String userName){
        dataMap.put(chatId, UserAdData.builder()
                    .state(1)
                    .userName(userName)
                    .build());
    }

    public void incrementState(Long chatId, Integer state){
        dataMap.get(chatId).setState(state+1);
    }

    public void decrementState(Long chatId, Integer state){
        dataMap.get(chatId).setState(state-1);
    }

    public void removeUserAdData(Long chatId){
        dataMap.remove(chatId);
    }
}
