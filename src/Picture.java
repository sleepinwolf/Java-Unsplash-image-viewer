import java.awt.*;
import java.net.URL;


public class Picture {
    Image image;
    URL url;

    public Picture(Image img, URL url) { // konstruktors kurš nodefinē objekta/ bildes vērtības
        this.image = img;
        this.url = url;
    }

    protected URL getURL() {  /// Metode kas atgriež objekta adresi jeb url
        return this.url;
    }

    protected Image getPicture() { /// metode kas atgriež objekta attēlu
        return this.image;
    }

}

