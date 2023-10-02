package dev.shulika.avto_inspector_bot.bot.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
        return "UserAdData{" +
                "state=" + state +
                ", userName='" + userName + '\'' +
                ", makeModel='" + makeModel + '\'' +
                ", price='" + price + '\'' +
                ", city='" + city + '\'' +
                ", year='" + year + '\'' +
                ", km='" + km + '\'' +
                ", cubicCapacity='" + cubicCapacity + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", transmission='" + transmission + '\'' +
                ", owner='" + owner + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", messengers='" + messengers + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
