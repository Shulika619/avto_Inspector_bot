package dev.shulika.avto_inspector_bot.bot.utils;

import java.util.List;

public class BotConst {


/* :::::  Telegram List Commands  :::::
start - Разместить объявление
contact - Контакты

/start@avto_Inspector_bot
/contact@avto_Inspector_bot
*/

    // ADMIN ---------------------------------------
    public static final String ADMIN_LINK = "https://t.me/LPR_Inspectors";

    // COMMANDS ---------------------------------------
    public static final String COMMAND_START = "/start";
    public static final String COMMAND_CONTACT = "/contact";

    // Messages ---------------------------------------
    public static final String UNSUPPORTED_COMMAND = "Такой команды не существует!";
    public static final String CONTACT_MSG = " ✏️ Контакты Администратора: " + ADMIN_LINK;
    public static final String START_MSG = """
            Доброго времени суток 💪
            🪙 СТОИМОСТЬ ПУБЛИКАЦИИ АВТО 300₽
            ✔️ Самая быстрая публикация;
            ✔️ Бесплатное обновление 1 раз по вашему запросу (в течение недели);\s
            ✔️ Публикация сроком на 7 дней затем автоудаление.
            ✔️ Если авто продано, сообщаете об этом и мы удаляем объявление
            - - - - - - - - - - - - - - - - - - - - -
            💎 Дополнительные услуги:
            🔥 Обновить публикацию (до 1 раз в сутки) - 100₽;
            🔥 Закреп в шапке канала 200₽;
            🔥 Публикация "громкая" с уведомлением для каждого подписчика канала 300₽
            🔥 VIP оформление объявления 400₽
            🔥 Попасть в ТОП список 500₽ (+дополнительная публикация)
            - - - - - - - - - - - - - - - - - - - - -
            ⬇️Реквизиты для оплаты⬇️
            🇷🇺 СберБанк
            2202206301894502
            🇷🇺 ПСБанк
            2200030510989693

            После оплаты опишите Авто по пунктам в одном сообщении ↙️↙️
            1. Марка/модель
            2. Цена:
            3. Город:
            4. Год выпуска:
            5. Пробег:
            6. Объём двигателя:
            7. Топливо:
            8. КПП:
            9. Собственник: (Да/Нет/ДКП)
            10. Описание от продавца:
            11. Номер тел:
            12. WhatsApp Viber, Telegram:
            13. Фото до 10 шт.
            ⚠️❗️ Обязательно отправьте скрин/чек оплаты ❗️⚠️
            """;

    // Questions Messages ---------------------------------------
    public static final List<String> QUESTIONS_LIST = List.of(
            "Марка/модель: ",
            "Цена: ",
            "Город: ",
            "Год выпуска: ",
            "Пробег: ",
            "Объём двигателя: ",
            "Топливо: ",
            "КПП: ",
            "Собственник: ",
            "Описание от продавца: ",
            "Номер тел: ",
            "WhatsApp, Viber, Telegram: ",
            "Фото от 2 до 10 шт: "
    );

    // COMMON Buttons and CallBackData ---------------------------------
    public static final String BTN_START_ADS = "➕ Разместить обьявление";
    public static final String BTN_START_ADS_CALLBACK = "START_ADS";
    public static final String BTN_BACK = "⬅ Назад";
    public static final String BTN_BACK_CALLBACK = "BACK";
    public static final String BTN_CONTACT = "✏️ Контакты";

//    public static final String BTN_PAID = "✅ Уже оплатил и готов разместить обьявление ✅";
//    public static final String BTN_PAID_CALLBACK = "ACCEPT_PAID";

}
