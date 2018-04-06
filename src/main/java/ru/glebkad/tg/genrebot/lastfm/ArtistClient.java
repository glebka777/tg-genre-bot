package ru.glebkad.tg.genrebot.lastfm;

import feign.Param;
import feign.RequestLine;

public interface ArtistClient {

    @RequestLine("GET /2.0/?method=artist.getsimilar&artist={artist}&autocorrect=1&limit=50&api_key={api_key}")
    String findSimilar(@Param("artist") String artist, @Param("api_key") String apiKey);

    @RequestLine("GET /2.0/?method=tag.gettopartists&tag={tag}&limit=50&api_key={api_key}")
    String findByTag(@Param("tag") String tag, @Param("api_key") String apiKey);

}
