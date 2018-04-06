package ru.glebkad.tg.genrebot;

import lombok.extern.log4j.Log4j2;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import ru.glebkad.tg.genrebot.bot.GenreBot;

@Log4j2
public class App {

    private TelegramBotsApi tgBotApi;


    private App() {
        ApiContextInitializer.init();
        tgBotApi = new TelegramBotsApi();
    }

    public static void main(String[] args) {
        App app = new App();
        app.registerGenreBot();
    }

    private void registerGenreBot() {
        try {
            GenreBot genreBot = new GenreBot();
            tgBotApi.registerBot(genreBot);
        } catch(TelegramApiRequestException e) {
            log.error("Error occurred while registering bot.", e);
            System.exit(-1);
        }
        log.info("Successfully registered bot");
    }

}
