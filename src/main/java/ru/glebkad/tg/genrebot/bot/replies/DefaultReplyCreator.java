package ru.glebkad.tg.genrebot.bot.replies;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

public class DefaultReplyCreator implements ReplyCreator {

    @Override
    public SendMessage create(Message message) {
        return new DefaultReply(message);
    }

    @Override
    public String getQuestion() {
        return "/start";
    }

}
