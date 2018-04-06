package ru.glebkad.tg.genrebot.lastfm;

import feign.Feign;
import feign.FeignException;
import feign.okhttp.OkHttpClient;
import ru.glebkad.tg.genrebot.config.SystemConfig;

public class LastFMClientController {

    private static final String LAST_FM_API_URL = "http://ws.audioscrobbler.com";

    private static ArtistClient artistClient;

    static {
        artistClient = Feign.builder()
                .client(new OkHttpClient())
                .target(ArtistClient.class, LAST_FM_API_URL);
    }


    public static String findSimilar(String artist) {
        try {
            return artistClient.findSimilar(artist, SystemConfig.LAST_FM_API_KEY);
        } catch(FeignException e) {
            return null;
        }
    }

    public static String findByTag(String tag) {
        try {
            return artistClient.findByTag(tag, SystemConfig.LAST_FM_API_KEY);
        } catch(FeignException e) {
            return null;
        }
    }
}
