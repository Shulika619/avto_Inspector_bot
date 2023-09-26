package dev.shulika.avto_inspector_bot.bot.Utils;

public class BotConst {

/*
 :::::  Telegram List Commands  :::::
start - Старт
contact - Контакты
help - Помощь

?post - Создать пост
?send - Отправить объявление
*/

    // ADMIN ---------------------------------------
    public static final String ADMIN_LINK = "https://t.me/LPR_Inspectors";
    public static final String COMMAND_POST = "/post";


    // COMMANDS ---------------------------------------
    public static final String COMMAND_START = "/start";
    public static final String COMMAND_CONTACT = "/contact";
    public static final String COMMAND_HELP = "/help";

    // INLINE COMMANDS ----------------------------------
//    /start@avto_Inspector_bot
//    /contact@avto_Inspector_bot
//    /help@avto_Inspector_bot

    // Messages ---------------------------------------
    public static final String UNSUPPORTED_MSG = "Неподдерживаемый тип сообщения";
    public static final String CONTACT_MSG = " ✏️ Контакты Администратора: https://t.me/LPR_Inspectors";
    public static final String HELP_MSG = """
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

    // Buttons and CallBackData---------------------------------
    public static final String BTN_PAID = "✅ Уже оплатил и готов разместить обьявление ✅";
    public static final String BTN_PAID_CALLBACK = "ACCEPT_PAID";

}