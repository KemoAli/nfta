package schedule.busapp.nfta.nftabus;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DataStructures {

    public static AssetManager getMyAssets(Context context)
    {
        return context.getResources().getAssets();
    }
    ArrayList <Stops> arr;
    String path = "C:\\Users\\kemok\\AndroidStudioProjects\\MyApplication\\Android-Development\\app\\src\\debug\\assets\\stops.txt";
    public  ArrayList<Stops> getArr() {

        return arr;
    }

    String stops[] ={"2182,3870,Berkshire Ave & Bailey Ave,,42.940998,-78.814002,1,0",
            "2183,3890,Berkshire Ave & Comstock Ave,,42.940959,-78.817270,1,0",
            "2184,3900,Berkshire Ave & Parkridge Ave,,42.940918,-78.821553,1,0",
            "2185,3930,Best St & Ellicott St,,42.903919,-78.866302,1,0",
            "2187,3380,Bailey Ave & Veterans Hospital,,42.951668,-78.813891,1,0",
            "2188,3250,Bailey Ave & Rounds Ave,,42.947298,-78.813540,1,0",
            "2189,3260,Bailey Ave & Lisbon Ave,,42.947029,-78.813782,1,0",
            "2190,3270,Bailey Ave & Scajaquada St,,42.917448,-78.813784,1,0",
            "2192,3280,Bailey Ave & Scheu Place,,42.903070,-78.813732,1,0",
            "2193,3290,Bailey Ave & Scheu Place,,42.902957,-78.813920,1,0",
            "2194,3300,Bailey Ave & Seneca St,,42.866350,-78.824000,1,0",
            "2195,3310,Bailey Ave & Seneca St,,42.866947,-78.823872,1,0",
            "2196,3330,Bailey Ave & Shirley Ave,,42.944368,-78.813792,1,0",
            "2197,3340,Bailey Ave & Stanley St,,42.892078,-78.814043,1,0",
            "2198,3350,1348 Bailey Ave,,42.890999,-78.814220,1,0",
            "2199,3610,Baynes St & Breckenridge St,,42.916949,-78.885791,1,0",
            "2200,3370,Bailey Ave & Veterans Hospital,,42.950838,-78.813614,1,0",
            "2201,5280,Broadway & Ellicott St,,42.886069,-78.871420,1,0",
            "2202,3390,Bailey Ave & Walden Ave,,42.905169,-78.813652,1,0",
            "2203,3400,Bailey Ave & Walden Ave,,42.905029,-78.813911,1,0",
            "2204,3410,Bailey Ave & Wecker St,,42.920679,-78.813593,1,0",
            "2205,3420,Bailey Ave & Wecker St,,42.920868,-78.813772,1,0",
            "2206,3430,Bailey Ave & Weston Ave,,42.928149,-78.813572,1,0"};

   public DataStructures(){
       arr = new ArrayList<Stops>();

    }

}
