package ru.glebkad.tg.genrebot.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomListFilter {

    private static final int SIZE = 7;

    public static List<String> filter(List<String> in) {
        List<Integer> binded = new ArrayList<>();
        List<String> out = new ArrayList<>(SIZE);
        int min = 0;
        int max = in.size();
        if (max <= min) return in;
        int border = Math.min(SIZE, in.size());
        while (binded.size() != border) {
            int randomNumber = ThreadLocalRandom.current().nextInt(min, max);
            if (binded.contains(randomNumber)) continue;
            out.add(in.get(randomNumber));
            binded.add(randomNumber);
        }
        return out;
    }

}
