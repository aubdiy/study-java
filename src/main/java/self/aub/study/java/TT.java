package self.aub.study.java;

/**
 * @author liujinxin
 * @since 2016-03-18 10:34
 */
public class TT {

    public static String getBlockCoordinate(double longitude, double latitude) {
        double blockLatitude = Math.floor(latitude);
        System.out.println("blockLatitude: " + blockLatitude);

        //double longitudePer100m = 180 / ((2 * Math.PI * (6371000 * Math.cos(Math.toRadians(blockLatitude)))) / 100);
        double longitudePer100m = 9 / (Math.PI * 6371 * Math.cos(Math.toRadians(blockLatitude)));
        System.out.println("longitudePer100m: " + longitudePer100m);


        double blockLongitude = Math.floor(longitude / longitudePer100m) * longitudePer100m;

        System.out.println("blockLongitude: " + blockLongitude);
        System.out.println("result(blockLatitude,blockLongitude): " + blockLatitude + " , " + blockLongitude);

        return blockLatitude + "," + blockLongitude;

    }


    public static void main(String[] args) {
        System.out.println("=============>" + getBlockCoordinate(91.123, 35.343));
    }
}
