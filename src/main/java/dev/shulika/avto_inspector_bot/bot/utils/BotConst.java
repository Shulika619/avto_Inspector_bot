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
    public static final String BTN_START_ADS = "➕ Разместить новое обьявление";
    public static final String BTN_START_ADS_CALLBACK = "START_ADS";
    public static final String BTN_BACK = "⬅ Назад";
    public static final String BTN_BACK_CALLBACK = "BACK";
    public static final String BTN_CONTACT = "✏️ Нечего не понял, хочу написать администратору";
    public static final String BTN_SEND_PROOF = "➡ Отправить скрин/чек оплаты";
    public static final String BTN_UPDATE_ADS = "🔄️ Обновить обьявление";

    // Messages ---------------------------------------
    public static final String UNSUPPORTED_COMMAND = """
            ⚠️ Такой команды не существует! ⚠️
            👉 /start - Разместить объявление""";
    public static final String UNSUPPORTED_FORMAT = """
            ❌ ОШИБКА ВВОДА - Неправильный ФОРМАТ ❌
            ⚠️ Разрешена отправка: ТЕКСТА или ФОТО ⚠️
            🚫 Запрещены: Видео, Документы, Голосовые, Файлы
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
            "Напишите МАРКУ/МОДЕЛЬ ниже и нажмите кнопку отправить: ",
            "Цена: ",
            "Город: ",
            "Год выпуска: ",
            "Пробег: ",
            "Объём двигателя: ",
            "Топливо: ",
            "КПП коробка: (автомат/вариатор/робот/механика) ",
            "Собственник: (Да/Нет/ДКП) ",
            "Описание от продавца: ",
            "Номер тел: ",
            "Telegram: (если хотите пропустить, напишите слово НЕТ) ",
            "Фото от 2 до 10 шт: "
    );

}
