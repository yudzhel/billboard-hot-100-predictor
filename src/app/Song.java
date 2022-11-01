package app;

public class Song {

    private String title;
    private String artist;
    private int pureSales;              //pure sales consist of digital and physical sales
    private int paidStreams;            //on-demand streams from streaming services (Spotify, Apple Music, Amazon Music and etc)
    private int freeStreams;            //streams from YouTube, Spotify(free), Audiomack, SoundCloud and etc
    private int programmedStreams;      //Pandora
    private int radio;                  //https://kworb.net/radio

    public Song(String title, String artist, int pureSales, int paidStreams, int freeStreams, int programmedStreams, int radio) {
        this.title = title;
        this.artist = artist;
        this.pureSales = pureSales;
        this.paidStreams = paidStreams;
        this.freeStreams = freeStreams;
        this.programmedStreams = programmedStreams;
        this.radio = radio;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getPureSales() {
        return pureSales;
    }

    public void setPureSales(int pureSales) {
        this.pureSales = pureSales;
    }

    public int getPaidStreams() {
        return paidStreams;
    }

    public void setPaidStreams(int paidStreams) {
        this.paidStreams = paidStreams;
    }

    public int getFreeStreams() {
        return freeStreams;
    }

    public void setFreeStreams(int freeStreams) {
        this.freeStreams = freeStreams;
    }

    public int getProgrammedStreams() {
        return programmedStreams;
    }

    public void setProgrammedStreams(int programmedStreams) {
        this.programmedStreams = programmedStreams;
    }

    public int getRadio(){
        return radio;
    }

    public void setRadio(int radio){
        this.radio = radio;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                        "\nArtist: " + artist +
                        "\nPure Sales: " + pureSales +
                        "\nPaid Streams: " + paidStreams +
                        "\nFree Streams: " + freeStreams +
                        "\nProgrammed Streams: " + programmedStreams +
                        "\nRadio: " + radio;
    }

    public String toStringArtistAndTitle() {
        return artist + " - " + title;
    }
}
