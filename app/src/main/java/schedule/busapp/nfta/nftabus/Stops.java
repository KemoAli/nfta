package schedule.busapp.nfta.nftabus;

public class Stops {
    private String stop_id;
    private String stop_code;
    private String stop_name;
    private double stop_lat;
    private double stop_lon;
    String wheelchair_boarding;

    public void setStop_lon(double stop_lon) {
        this.stop_lon = stop_lon;
    }

    public void setStop_lat(double stop_lat) {
        this.stop_lat = stop_lat;
    }

    public double getStop_lat() {
        return stop_lat;
    }

    public double getStop_lon() {
        return stop_lon;
    }

    public String getStop_id() {
        return stop_id;
    }

    public String getStop_code() {
        return stop_code;
    }

    public String getStop_name() {
        return stop_name;
    }

    public Stops(String stop_id, String stop_code, String stop_name, double stop_lat, double stop_lon, String wheelchair_boarding) {
        this.stop_id = stop_id;
        this.stop_code = stop_code;
        this.stop_name = stop_name;
        this.stop_lat = stop_lat;
        this.stop_lon = stop_lon;
        this.wheelchair_boarding = wheelchair_boarding;
    }
}
