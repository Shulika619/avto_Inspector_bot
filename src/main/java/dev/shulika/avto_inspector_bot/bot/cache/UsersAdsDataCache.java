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

}
