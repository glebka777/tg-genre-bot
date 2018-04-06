package ru.glebkad.tg.genrebot.bot.replies;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import ru.glebkad.tg.genrebot.config.StringConfig;

class ReplySticker extends SendMessage {

    ReplySticker(Message message) {
        setText(StringConfig.STICKER_REPLY());
        setChatId(message.getChatId());
    }

}
