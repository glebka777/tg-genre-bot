package ru.glebkad.tg.genrebot.bot.replies;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

class ReplyHelp extends SendMessage {

    private static final String HELP_MESSAGE = "Привет! Я помогу подобрать исполнителей, соответствующих Вашему " +
            "музыкальному вкусу. Введите /start отображения опций.";


    ReplyHelp(Message message) {
        setChatId(message.getChatId());
        setText(HELP_MESSAGE);
    }

}
