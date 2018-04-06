package ru.glebkad.tg.genrebot.config;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StringConfig {

    public static final String FAVOURITE_ARTIST_REQUEST = "Напишите мне имя любого исполнителя";
    public static final String BY_TAG_REQUEST = "Напишите мне какой-нибудь стиль (на английском языке)";
    public static final String ARTIST_INIT_SIMILAR_COMMAND = "init_similar";
    public static final String TAG_INIT_COMMAND = "by_tag";
    public static final String START_COMMAND = "/start";
    public static final String HELP_COMMAND = "/help";
    public static final String STICKER = "<sticker>";
    public static final String LINE = "<~>";


    private static final List<String> stickerReplies = Arrays.asList(
            "Вау!",
            "Классный стикер!",
            "Неплохо.",
            "Прикольно."
    );

    private static final List<String> artistsReplies = Arrays.asList(
            "Вот это вот Вам должно понравиться:",
            "Вам стоит послушать:",
            "Попробуйте вот это:"
    );


    public static String ARTIST_REPLY() {
        return getRandom(artistsReplies);
    }

    public static String STICKER_REPLY() {
        return getRandom(stickerReplies);
    }

    private static String getRandom(List<String> list) {
        int min = 0;
        int max = list.size();
        int randomNumber = ThreadLocalRandom.current().nextInt(min, max);
        return list.get(randomNumber);
    }

}
