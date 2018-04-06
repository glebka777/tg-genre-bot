package ru.glebkad.tg.genrebot.bot.replies;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import ru.glebkad.tg.genrebot.config.StringConfig;

public class ReplyStickerCreator implements ReplyCreator {

    @Override
    public SendMessage create(Message message) {
        return new ReplySticker(message);
    }

    @Override
    public String getQuestion() {
        return StringConfig.STICKER;
    }

}
