package dev.shulika.avto_inspector_bot.bot.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> photo;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder()
                .append("Марка/модель: " + makeModel + "\n")
                .append("Цена: " + price + "\n")
                .append("Город: " + city + "\n")
                .append("Год выпуска: " + year + "\n")
                .append("Пробег: " + km + "\n")
                .append("Объём двигателя: " + cubicCapacity + "\n")
                .append("Топливо: " + fuelType + "\n")
                .append("КПП: " + transmission + "\n")
                .append("Собственник: " + owner + "\n")
                .append("Описание от продавца: " + description + "\n")
                .append("Номер тел: " + phone + "\n")
                .append("WhatsApp, Viber, Telegram: " + messengers + "\n");
        return stringBuilder.toString();
    }

}
