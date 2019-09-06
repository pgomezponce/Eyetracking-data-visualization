package Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Model.GazePoint;

import javax.swing.text.View;

public class Information {

    private static final String VIEWPORT = "Viewport";
    private static final String SCREEN = "Screen";
    private static final String GUI = "GUI";

    private static final String TIMESTAMP = "Timestamp";
    private static final String PRECISETIMESTAMP = "PreciseTimestamp";

    ArrayList<GazePoint> gazePoint;
    ArrayList<UVCoord> UVList;
    String id;


    public Information(JSONObject information) {
        //iniciar información
        this.id = information.getString("id");
        this.gazePoint = new ArrayList<GazePoint>();
        this.UVList = new ArrayList<UVCoord>();


        //Obtener info
        JSONArray gazePoints = information.getJSONArray("gazePoints");
        JSONArray UVCoord = information.getJSONArray("UVCoord");


        //GAZE on screen
        for (int gazePointObjectPosition = 0; gazePointObjectPosition < gazePoints.length(); gazePointObjectPosition++) {

            GazePoint GP = new GazePoint();


            //Get array item
            JSONObject jo = gazePoints.getJSONObject(gazePointObjectPosition);

            //item fields that are objects
            JSONObject ViewPortJSON = jo.getJSONObject(VIEWPORT);
            JSONObject ScreenJSON = jo.getJSONObject(SCREEN);
            JSONObject GUIJSON = jo.getJSONObject(GUI);


            //generate information
            Viewport v = new Viewport();
            v.setX(ViewPortJSON.getInt("x"));
            v.setY(ViewPortJSON.getInt("y"));

            Screen s = new Screen();
            s.setX(ScreenJSON.getInt("x"));
            s.setY(ScreenJSON.getInt("y"));

            Model.GUI g = new GUI();
            g.setX(GUIJSON.getInt("x"));
            g.setY(GUIJSON.getInt("y"));

            //pass infromation
            GP.setValid(jo.getBoolean("IsValid"));
            GP.setGUI(g);
            GP.setScreen(s);
            GP.setViewport(v);
            GP.setTimestamp(jo.getFloat(TIMESTAMP));
            GP.setPreciseTimeStamp(jo.getFloat(PRECISETIMESTAMP));

            this.gazePoint.add(GP);
        }

        //Get UVCoords
        for (int UVCoordPos = 0; UVCoordPos < UVCoord.length(); UVCoordPos++) {
            UVCoord u = new UVCoord(UVCoord.get(UVCoordPos).getInt("x"), UVCoord.get(UVCoordPos).getInt("y"));
            this.UVList.add(u);
        }
    }
}

