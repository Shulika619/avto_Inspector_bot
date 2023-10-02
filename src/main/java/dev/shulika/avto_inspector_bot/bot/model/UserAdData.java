package dev.shulika.avto_inspector_bot.bot.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static dev.shulika.avto_inspector_bot.bot.utils.BotConst.*;

@Getter
@Setter
@Builder
public class UserAdData {
    private Integer state;
    private String userName;

    private String makeModel;
    private String price;
    private String city;
    private String year;
    private String km;
    private String cubicCapacity;
    private String fuelType;
    private String transmission;
    private String owner;
    private String description;
    private String phone;
    private String messengers;
    private String photo;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder()
                .append(QUESTION_1 + makeModel + "\n")
                .append(QUESTION_2 + price + "\n")
                .append(QUESTION_3 + city + "\n")
                .append(QUESTION_4 + year + "\n")
                .append(QUESTION_5 + km + "\n")
                .append(QUESTION_6 + cubicCapacity + "\n")
                .append(QUESTION_7 + fuelType + "\n")
                .append(QUESTION_8 + transmission + "\n")
                .append(QUESTION_9 + owner + "\n")
                .append(QUESTION_10 + description + "\n")
                .append(QUESTION_11 + phone + "\n")
                .append(QUESTION_12 + messengers + "\n");
        return stringBuilder.toString();
    }
}
