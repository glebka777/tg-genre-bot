package ru.glebkad.tg.genrebot.bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import ru.glebkad.tg.genrebot.bot.replies.*;
import ru.glebkad.tg.genrebot.config.StringConfig;

import java.util.TreeMap;

public class ReplyHandler {

    private static final TreeMap<String, ReplyCreator> replies = new TreeMap<>();

    static {
        DefaultReplyCreator replyStartCreator = new DefaultReplyCreator();
        ReplyHelpCreator replyHelpCreator = new ReplyHelpCreator();
        ReplySimilarInitCreator replySimilarInitCreator = new ReplySimilarInitCreator();
        ReplySimilarCreator replySimilarCreator = new ReplySimilarCreator();
        ReplyStickerCreator replyStickerCreator = new ReplyStickerCreator();
        ReplyTagInitCreator replyTagInitCreator = new ReplyTagInitCreator();
        ReplyTagCreator replyTagCreator = new ReplyTagCreator();
        replies.put(replyStartCreator.getQuestion(), replyStartCreator);
        replies.put(replyHelpCreator.getQuestion(), replyHelpCreator);
        replies.put(replySimilarInitCreator.getQuestion(), replySimilarInitCreator);
        replies.put(replySimilarCreator.getQuestion(), replySimilarCreator);
        replies.put(replyStickerCreator.getQuestion(), replyStickerCreator);
        replies.put(replyTagInitCreator.getQuestion(), replyTagInitCreator);
        replies.put(replyTagCreator.getQuestion(), replyTagCreator);
    }


    SendMessage handle(String question, Message message) {
        ReplyCreator replyCreator;
        try {
            replyCreator = replies.get(question);
        } catch(NullPointerException e) {
            return null;
        }
        return replyCreator.create(message);
    }

    SendMessage handleDefault(Message message) {
        ReplyCreator replyCreator = replies.get(StringConfig.START_COMMAND);
        if (replyCreator == null) return null;
        return replyCreator.create(message);
    }


}
