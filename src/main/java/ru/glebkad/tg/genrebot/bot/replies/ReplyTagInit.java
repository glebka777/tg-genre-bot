package ru.glebkad.tg.genrebot.bot.replies;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import ru.glebkad.tg.genrebot.config.StringConfig;

class ReplyTagInit extends SendMessage {

    ReplyTagInit(Message message) {
        ReplyKeyboard keyboardMarkUp = replyKeyboard();
        setReplyMarkup(keyboardMarkUp);
        setText(StringConfig.BY_TAG_REQUEST);
        setChatId(message.getChatId());
    }

    private ReplyKeyboard replyKeyboard() {
        return new ForceReplyKeyboard();
    }

}
