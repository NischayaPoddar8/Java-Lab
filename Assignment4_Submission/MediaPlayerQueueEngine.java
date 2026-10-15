import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

public class MediaPlayerQueueEngine {
    private final LinkedList<Song> playlist = new LinkedList<>();
    private final LinkedList<Song> recentHistory = new LinkedList<>();
    private int currentIndex = -1;

    public void addSongToEnd(Song song) {
        playlist.addLast(song);
        if (currentIndex == -1) {
            currentIndex = 0;
        }
    }

    public void addSongToFront(Song song) {
        playlist.addFirst(song);
        if (currentIndex >= 0) {
            currentIndex++;
        } else {
            currentIndex = 0;
        }
    }

    public void addSongAtPosition(int position, Song song) {
        if (position < 0 || position > playlist.size()) {
            throw new IndexOutOfBoundsException("Invalid playlist position.");
        }
        playlist.add(position, song);
        if (currentIndex == -1) {
            currentIndex = 0;
        } else if (position <= currentIndex) {
            currentIndex++;
        }
    }

    public void removeDuplicateSongs() {
        Set<String> seen = new HashSet<>();
        ListIterator<Song> iterator = playlist.listIterator();
        int removedBeforeCurrent = 0;
        int originalCurrent = currentIndex;
        int index = 0;

        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (!seen.add(song.getId())) {
                iterator.remove();
                if (index < originalCurrent) {
                    removedBeforeCurrent++;
                } else if (index == originalCurrent) {
                    currentIndex = -1;
                }
            }
            index++;
        }

        if (playlist.isEmpty()) {
            currentIndex = -1;
        } else if (currentIndex == -1) {
            currentIndex = Math.min(originalCurrent - removedBeforeCurrent, playlist.size() - 1);
        } else {
            currentIndex = Math.max(0, originalCurrent - removedBeforeCurrent);
            currentIndex = Math.min(currentIndex, playlist.size() - 1);
        }
    }

    public Song playCurrent() {
        if (playlist.isEmpty() || currentIndex < 0) {
            return null;
        }
        Song song = playlist.get(currentIndex);
        recentHistory.addFirst(song);
        return song;
    }

    public Song playNext() {
        if (playlist.isEmpty()) {
            return null;
        }
        if (currentIndex < playlist.size() - 1) {
            currentIndex++;
        }
        return playCurrent();
    }

    public Song playPrevious() {
        if (playlist.isEmpty()) {
            return null;
        }
        if (currentIndex > 0) {
            currentIndex--;
        }
        return playCurrent();
    }

    public void traverseForward() {
        ListIterator<Song> iterator = playlist.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void traverseBackward() {
        ListIterator<Song> iterator = playlist.listIterator(playlist.size());
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }

    public void displayRecentHistory() {
        for (Song song : recentHistory) {
            System.out.println(song);
        }
    }

    public void displayPlaylist() {
        for (Song song : playlist) {
            System.out.println(song);
        }
    }

    public static class Song {
        private final String id;
        private final String title;
        private final String artist;

        public Song(String id, String title, String artist) {
            if (id == null || id.isBlank() || title == null || title.isBlank()) {
                throw new IllegalArgumentException("Song ID and title are required.");
            }
            this.id = id;
            this.title = title;
            this.artist = artist == null ? "Unknown" : artist;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getArtist() {
            return artist;
        }

        @Override
        public String toString() {
            return title + " - " + artist + " [" + id + "]";
        }
    }

    public static void main(String[] args) {
        MediaPlayerQueueEngine player = new MediaPlayerQueueEngine();
        Song first = new Song("1", "Midnight City", "M83");
        Song second = new Song("2", "Yellow", "Coldplay");
        Song third = new Song("3", "Blinding Lights", "The Weeknd");

        player.addSongToEnd(first);
        player.addSongToEnd(second);
        player.addSongAtPosition(1, third);
        player.addSongToFront(new Song("4", "Intro", "The xx"));
        player.addSongToEnd(second);
        player.removeDuplicateSongs();

        System.out.println("Forward:");
        player.traverseForward();

        System.out.println("Backward:");
        player.traverseBackward();

        System.out.println("Now playing: " + player.playCurrent());
        System.out.println("Next: " + player.playNext());

        System.out.println("Recent history:");
        player.displayRecentHistory();
    }
}
