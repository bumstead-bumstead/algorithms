package programmers;

import java.util.*;

public class BestAlbum {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = new int[1];
        HashMap<String, PriorityQueue<Music>> musicsPerGenre = new HashMap<>();
        HashMap<String, Integer> playsPerGenre = new HashMap<>();

        for (int i = 0; i < plays.length; i++) {
            String tmpGenre = genres[i];
            int tmpPlay = plays[i];
            if (!musicsPerGenre.containsKey(tmpGenre)) {
                musicsPerGenre.put(tmpGenre, new PriorityQueue<Music>(Collections.reverseOrder()));
            }
            musicsPerGenre.get(tmpGenre).offer(new Music(i, tmpPlay));
            playsPerGenre.put(tmpGenre, playsPerGenre.getOrDefault(tmpGenre, 0) + tmpPlay);
        }

        PriorityQueue<Popularity> popularityQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (Map.Entry<String, Integer> entry : playsPerGenre.entrySet()) {
            popularityQueue.offer(new Popularity(entry.getKey(), entry.getValue()));
        }

        List<Integer> answerList = new ArrayList<>();

        while (!popularityQueue.isEmpty()) {
            PriorityQueue<Music> tmp = musicsPerGenre.get(popularityQueue.poll().genre);
            if (tmp.size() >= 2) {
                answerList.add(tmp.poll().number);
            }

            answerList.add(tmp.poll().number);

        }
        return toArray(answerList);
    }

    public static int[] toArray(List<Integer> list) {
        int[] result = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}

class Music implements Comparable<Music> {
    int number;
    int plays;

    public Music(int number, int plays) {
        this.number = number;
        this.plays = plays;
    }

    public int compareTo(Music music) {
        if (music.plays == this.plays) return music.number - this.number;
        return this.plays - music.plays;
    }

    public String toString() {
        return "[" + number + ", " + plays + "]";
    }
}

class Popularity implements Comparable<Popularity> {
    String genre;
    int plays;

    public Popularity(String genre, int plays) {
        this.genre = genre;
        this.plays = plays;
    }

    public int compareTo(Popularity popularity) {
        return this.plays - popularity.plays;
    }

    public String toString() {
        return "[" + genre + ", " + plays + "]";
    }
}

