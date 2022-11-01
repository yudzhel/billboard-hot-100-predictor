package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Prediction {

    private static final List<Song> songs = new ArrayList<>();

    public static void addSong(){
        System.out.println("------------------ADD SONG-----------------");

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Title: ");
            String title = scanner.nextLine();
            System.out.print("Artist: ");
            String artist = scanner.nextLine();
            System.out.print("Pure Sales: ");
            int pureSales = scanner.nextInt();
            System.out.print("Paid Streams: ");
            int paidStreams = scanner.nextInt();
            System.out.print("Free Streams: ");
            int freeStreams = scanner.nextInt();
            System.out.print("Programmed Streams: ");
            int programmedStreams = scanner.nextInt();
            System.out.print("Radio: ");
            int radio = scanner.nextInt();

            if(songs.stream().noneMatch(songs -> (songs.getTitle().equals(title) && songs.getArtist().equals(artist)))){
                songs.add(new Song(title,artist,pureSales,paidStreams,freeStreams,programmedStreams,radio));
            }else {
                System.out.println("Song already exists!");
            }

        } catch (InputMismatchException ime) {
            System.out.println("Enter valid values!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteSong(){
        System.out.println("----------------DELETE SONG------------------");
        int i = 0;
        for(Song song: songs){
            System.out.println(i + ". " + song.toStringArtistAndTitle());
            i++;
        }

        System.out.println("Enter the INDEX of the song you want to DELETE: ");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();

        if(index >=0 && index <songs.size()){
            songs.remove(index);
            System.out.println("Song deleted!");
        }else {
            System.out.println("Index " + index + " doesn't exist!");
        }
    }

    public static void viewSongs() {
        if(songs.size()==0){
            System.out.println("Empty list!");
        }
        else {
            System.out.println("----------------SONGS------------------");
            int i = 1;
            for(Song song: songs){
                System.out.println(i + ". " + song.toString());
                System.out.println();
                i++;
            }
        }
    }

    public static void makePrediction(){

        System.out.print("Enter chart date: ");
        Scanner scanner = new Scanner(System.in);
        String chartDate = scanner.nextLine();

        System.out.println("------------------PREDICTION FOR THE WEEK OF: " + chartDate + " -----------------");
        System.out.println();

        AtomicInteger i = new AtomicInteger(1);
        songsSorted().forEach((k, v) -> System.out.println(i.getAndIncrement() + ". " + k + ": " + v + " points."));
    }

    private static Map<String, Double> calculateSongPoints() {

        Map<String, Double> pointsCalculated = new HashMap<>();

        for(Song song: songs) {
            double points;
            points = (((double) song.getPureSales()) / 750) +
                    (((double) song.getPaidStreams()) / 125_000) +
                    (((double) song.getFreeStreams()) / 187_500) +
                    (((double) song.getProgrammedStreams()) / 250_000) +
                    (((double) song.getRadio()) / 600_000);
            pointsCalculated.put(song.getArtist() + " - " + song.getTitle(), round(points));
        }

        return pointsCalculated;
    }


    private static Map<String, Double> songsSorted() {

        Map<String, Double> reverseSorted = new LinkedHashMap<>();

        calculateSongPoints().entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSorted.put(x.getKey(), x.getValue()));

        return reverseSorted;
    }

    private static Double round(double points) {

        DecimalFormat df = new DecimalFormat("###.##");
        return Double.valueOf(df.format(points));
    }

    public static void saveToFile() {

        System.out.print("Enter chart date (DD.MM.YYYY): ");
        Scanner scanner = new Scanner(System.in);
        String chartDate = scanner.nextLine();

        File file = new File("prediction_" + chartDate + ".txt");

        try {
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("------------------PREDICTION FOR THE WEEK OF: " + chartDate + " ------------------------");
            pw.println();

            AtomicInteger i = new AtomicInteger(1);
            songsSorted().forEach((k, v) -> pw.println(i.getAndIncrement() + ". " + k + ": " + v + " points."));
            System.out.println("File " + file + " was successfully created!");

            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
