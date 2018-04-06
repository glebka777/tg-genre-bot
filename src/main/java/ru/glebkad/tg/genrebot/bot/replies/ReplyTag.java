package ru.glebkad.tg.genrebot.bot.replies;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import ru.glebkad.tg.genrebot.config.StringConfig;
import ru.glebkad.tg.genrebot.lastfm.LastFMClientController;
import ru.glebkad.tg.genrebot.util.ArtistListParser;
import ru.glebkad.tg.genrebot.util.RandomListFilter;

import java.util.List;

class ReplyTag extends SendMessage {

    private static final String NEWLINE = "\n";
    private static final String NO_DATA_FOUND = "К сожалению, не смог ничего найти  :-(";


    ReplyTag(Message message) {
        String tag = message.getText();
        String xmlResponse = LastFMClientController.findByTag(tag);
        ArtistListParser parser = new ArtistListParser(xmlResponse);
        parser.parse();
        if (parser.isError())
            setText(NO_DATA_FOUND);
        else {
            List<String> artists = RandomListFilter.filter(parser.getArtists());
            String replyText = buildReply(artists);
            setText(replyText);
        }
        setChatId(message.getChatId());
    }

    private String buildReply(List<String> artists) {
        StringBuilder sb = new StringBuilder();
        sb.append(StringConfig.LINE);
        sb.append(NEWLINE);
        sb.append(StringConfig.ARTIST_REPLY());
        sb.append(NEWLINE);
        for (String s : artists) {
            sb.append(s);
            sb.append(NEWLINE);
        }
        sb.append(StringConfig.LINE);
        return sb.toString();
    }

}
