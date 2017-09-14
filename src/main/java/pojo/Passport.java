package pojo;

/**
 * Created by fabienbk on 14/09/17.
 */
public class Passport extends Document {

    private final String name;
    private final String number;
    private final byte[] image;

    public Passport(String name, String number, byte[] image) {
        this.name = name;
        this.number = number;
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
