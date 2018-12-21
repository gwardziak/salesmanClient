/*
* City.java
* Models a city
*/

public class City {
    int x;
    int y;

    // Constructs a city at chosen x, y location
    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Gets city's x coordinate
    public int getX() {
        return this.x;
    }

    // Gets city's y coordinate
    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return getX() + ", " + getY();
    }
}