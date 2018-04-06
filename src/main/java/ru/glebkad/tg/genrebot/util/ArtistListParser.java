package ru.glebkad.tg.genrebot.util;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Getter
public class ArtistListParser {

    public static final String ATTRIBUTE_ARTIST = "artist";
    private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private static final String TAG_NAME = "name";
    private static final String TAG_ERROR = "error";
    private static final String TAG_SIMILAR_ARTISTS = "similarartists";

    private String message;
    private boolean isError = false;
    private List<String> artists;
    private String correctedArtistName;


    public ArtistListParser(String message) {
        this.message = message;
    }

    public void parse() {
        checkError();
        if (isError) return;
        DocumentBuilder builder;
        artists = new ArrayList<>();
        try {
            builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(message));
            Document document = builder.parse(is);
            parseForError(document);
            if (isError) return;
            parseForCorrectedName(document);
            parseForArtists(document);
        } catch(ParserConfigurationException | SAXException | IOException e) {
            log.warn("Error occurred while parsing message", e);
            isError = true;
        }
    }

    private void checkError() {
        if (message == null)
            isError = true;
    }

    private void parseForError(Document document) {
        NodeList elementsByTagName = document.getElementsByTagName(TAG_ERROR);
        if (elementsByTagName.getLength() < 1) return;
        isError = true;
        String errorValue = elementsByTagName.item(0).getNodeValue();
        log.warn("Error occurred while parsing xml response: {}", errorValue);
    }

    private void parseForCorrectedName(Document document) {
        NodeList elementsByTagName = document.getElementsByTagName(TAG_SIMILAR_ARTISTS);
        if (elementsByTagName.getLength() < 1) return;
        Node item = elementsByTagName.item(0)
                .getAttributes()
                .getNamedItem(ATTRIBUTE_ARTIST);
        correctedArtistName = item.getTextContent();
    }

    private void parseForArtists(Document document) {
        NodeList elementsByTagName = document.getElementsByTagName(TAG_NAME);
        int length = elementsByTagName.getLength();
        for (int i = 0; i < length; i++) {
            Node item = elementsByTagName.item(i);
            String nodeValue = item.getTextContent();
            artists.add(nodeValue);
        }
    }

}
