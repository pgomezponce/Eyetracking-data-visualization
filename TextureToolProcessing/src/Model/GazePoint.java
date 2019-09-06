package Model;

class Viewport
{
    int x;
    int y;

    public Viewport() {
        x = -1;
        y = -1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

class Screen
{
    int x;
    int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Screen() {
        x = -1;
        y = -1;
    }

}

class GUI
{
    int x;
    int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public GUI() {
        x = -1;
        y = -1;
    }

}

public class GazePoint {

    Viewport Viewport;
    Screen Screen;
    GUI GUI;

    boolean isValid;
    float Timestamp;
    float PreciseTimeStamp;

    public GazePoint(Model.Viewport viewport, Model.Screen screen, Model.GUI GUI, boolean isValid, float timestamp, float preciseTimeStamp) {
        Viewport = viewport;
        Screen = screen;
        this.GUI = GUI;
        this.isValid = isValid;
        Timestamp = timestamp;
        PreciseTimeStamp = preciseTimeStamp;
    }

    public GazePoint() {
        this.Viewport = new Viewport();
        this.Screen = new Screen();
        this.GUI = new GUI();

        this.isValid = false;
        this.Timestamp = -1;
        this.PreciseTimeStamp = -1;
    }

    public Model.Viewport getViewport() {
        return Viewport;
    }

    public void setViewport(Model.Viewport viewport) {
        Viewport = viewport;
    }

    public Model.Screen getScreen() {
        return Screen;
    }

    public void setScreen(Model.Screen screen) {
        Screen = screen;
    }

    public Model.GUI getGUI() {
        return GUI;
    }

    public void setGUI(Model.GUI GUI) {
        this.GUI = GUI;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public float getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(float timestamp) {
        Timestamp = timestamp;
    }

    public float getPreciseTimeStamp() {
        return PreciseTimeStamp;
    }

    public void setPreciseTimeStamp(float preciseTimeStamp) {
        PreciseTimeStamp = preciseTimeStamp;
    }
}
