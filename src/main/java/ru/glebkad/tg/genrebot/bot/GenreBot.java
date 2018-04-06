package ru.glebkad.tg.genrebot.bot;

import lombok.extern.log4j.Log4j2;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.glebkad.tg.genrebot.config.StringConfig;
import ru.glebkad.tg.genrebot.config.SystemConfig;

@Log4j2
public class GenreBot extends TelegramLongPollingBot {

    private static final String BOT_NAME = "musicPioneerBot";

    private ReplyHandler replyHandler = new ReplyHandler();


    @Override
    public void onUpdateReceived(Update update) {
        String textToHandle = null;
        Message message = null;
        boolean sendDefault = false;
        if (update.hasMessage() && update.getMessage().hasText()) {
            message = update.getMessage();
            String text = message.getText();
            User user = message.getFrom();
            if (message.isCommand()) {
                textToHandle = text;
                log.debug("Got command '{}' from '{}'", text, user);
            } else if (message.isReply()) {
                textToHandle = message.getReplyToMessage().getText();
                log.debug("Got reply '{}' from '{}' on", text, user, textToHandle);
                sendDefault = true;
            } else sendDefault = true;
        } else if (update.hasMessage() && update.getMessage().getSticker() != null) {
            message = update.getMessage();
            textToHandle = StringConfig.STICKER;
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            message = callbackQuery.getMessage();
            String callbackString = callbackQuery.getData();
            log.debug("Handling callback query '{}' from '{}'", callbackString, message.getFrom());
            textToHandle = callbackString;
        }
        SendMessage reply = replyHandler.handle(textToHandle, message);
        if (reply != null)
            sendReply(reply);
        if (sendDefault) {
            SendMessage defaultReply = replyHandler.handleDefault(message);
            sendReply(defaultReply);
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return SystemConfig.TG_BOT_API_KEY;
    }

    private void sendReply(SendMessage message) {
        try {
            execute(message);
        } catch(TelegramApiException e) {
            log.error("Error occurred while sending message.", e);
        }
    }
}
