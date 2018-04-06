package ru.glebkad.tg.genrebot.bot.replies;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.exceptions.TelegramApiValidationException;
import ru.glebkad.tg.genrebot.config.StringConfig;

import java.util.ArrayList;
import java.util.List;

class DefaultReply extends SendMessage {

    private static final String INIT_MESSAGE = "Подобрать исполнителей по:";
    private static final String ARTIST_SIMILAR_BUTTON_TEXT = "по другому исполнителю";
    private static final String BY_TAG_BUTTON_TEXT = "по стилю";


    DefaultReply(Message message) {
        ReplyKeyboard keyboardMarkUp = replyKeyboard();
        setReplyMarkup(keyboardMarkUp);
        setText(INIT_MESSAGE);
        setChatId(message.getChatId());
    }

    private ReplyKeyboard replyKeyboard() {
        InlineKeyboardMarkup keyboardMarkUp = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> row_1 = new ArrayList<>();
        InlineKeyboardButton similarArtistsButton = getSimilarArtistsButton();
        row_1.add(similarArtistsButton);
        keyboard.add(row_1);
        List<InlineKeyboardButton> row_2 = new ArrayList<>();
        InlineKeyboardButton tagArtistsButton = getTagArtistsButton();
        row_2.add(tagArtistsButton);
        keyboard.add(row_2);
        keyboardMarkUp.setKeyboard(keyboard);
        try {
            keyboardMarkUp.validate();
        } catch(TelegramApiValidationException e) {
            e.printStackTrace();
        }
        return keyboardMarkUp;
    }

    private InlineKeyboardButton getSimilarArtistsButton() {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setCallbackData(StringConfig.ARTIST_INIT_SIMILAR_COMMAND);
        button.setText(ARTIST_SIMILAR_BUTTON_TEXT);
        return button;
    }

    private InlineKeyboardButton getTagArtistsButton() {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setCallbackData(StringConfig.TAG_INIT_COMMAND);
        button.setText(BY_TAG_BUTTON_TEXT);
        return button;
    }

}
