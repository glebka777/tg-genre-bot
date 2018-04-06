package ru.glebkad.tg.genrebot.config;

public class SystemConfig {

    public static final String TG_BOT_API_KEY;
    public static final String LAST_FM_API_KEY;

    static {
        TG_BOT_API_KEY = System.getProperty("tg.bot.api.key") == null ? System.getenv("tg.bot.api.key") : System.getProperty("tg.bot.api.key");
        LAST_FM_API_KEY = System.getProperty("last.fm.api.key") == null ? System.getenv("last.fm.api.key") : System.getProperty("last.fm.api.key");
    }

}
