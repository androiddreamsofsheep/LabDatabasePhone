package edu.dlsu.mobapde.labdatabasephone;

/**
 * Created by G301 on 11/7/2017.
 */

public class Phone {

    public static final String EXTRA_ID = "id";
    public static final String EXTRA_TOADD = "add";

    public static final String TABLE_NAME = "phone";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SIZE = "size";
    public static final String COLUMN_RESOLUTION = "resolution";
    public static final String COLUMN_MANUFACTURER = "manufacturer";

    private long id;
    private String size;
    private int resolution;
    private String manufacturer;

    public Phone(){}

    public Phone(String size, int resolution, String manufacturer) {
        this.size = size;
        this.resolution = resolution;
        this.manufacturer = manufacturer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}










