package dev.shulika.avto_inspector_bot.bot.utils;

import java.util.List;

public class BotConst {

/* :::::  Telegram List Commands  :::::
start - Разместить объявление
contact - Контакты
*/

    // COMMANDS ---------------------------------------
    public static final String COMMAND_START = "/start";
    public static final String COMMAND_CONTACT = "/contact";

    // COMMON Buttons and CallBackData ---------------------------------
    public static final String BTN_START_ADS = "➕ Разместить обьявление";
    public static final String BTN_START_ADS_CALLBACK = "START_ADS";
    public static final String BTN_BACK = "⬅ Назад";
    public static final String BTN_BACK_CALLBACK = "BACK";
    public static final String BTN_CONTACT = "✏️ Контакты";
    public static final String BTN_SEND_PROOF = "➡ Отправить скрин/чек оплаты";

    // Messages ---------------------------------------
    public static final String UNSUPPORTED_COMMAND = """
            ⚠️ Такой команды не существует! ⚠️
            👉 /start - Разместить объявление""";
    public static final String START_MSG = """
            - - - - - - - - - - - - - - - - - - - - - - - - - - -\s
            🔺 Стоимость публикации авто 300₽
            🔺 VIP публикация 400₽
            ✔️ Самая быстрая публикация;
            ✔️ Бесплатное обновление 1 раз по вашему запросу (в течение 6 дней, "Все верно, это две публикации по цене одной");\s
            ✔️ Публикация сроком на 7 дней затем автоудаление.
            ✔️ Если авто продано, сообщаете об этом и мы удаляем объявление
            - - - - - - - - - - - - - - - - - - - - - - - - - - -\s
            🔹 Дополнительные услуги:
            🔥 Обновить публикацию (до 1 раз в сутки) - 100₽;
            🔥 Закреп в шапке канала 200₽;
            🔥 Публикация "громкая" с уведомлением для каждого подписчика канала 300₽
            🔥 Попасть в ТОП список 500₽ (+ 1 дополнительная публикация)

            Опишите Авто по пунктам в одном сообщении ↙️↙️
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
            12. WhatsApp, Viber, Telegram:
            13. Фото до 10 шт.
            """;
    public static final String FINISH_MSG = """
            \\- \\- \\- \\- \\- \\- \\- \\- \\- \\- \\- \\- \\- \\- \\- \\- \\- \\- \\- \\- \\-
            ⬇️Реквизиты для оплаты⬇️
                        
            🇷🇺 СберБанк \\(Максим Русланович\\)
            `2202206301894502` 👈 *_нажми для копирования_*
            
            🇷🇺 ПСБ\\-банк
            `2200030510989693` 👈 *_нажми для копирования_*
            
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
            "Собственник: (Да/Нет/ДКП) ",
            "Описание от продавца: ",
            "Номер тел: ",
            "WhatsApp, Viber, Telegram: ",
            "Фото от 2 до 10 шт: "
    );

}
