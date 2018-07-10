package rcas.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import rcas.model.RaceCar;

import java.io.*;
import java.util.ArrayList;

public class DataUtil {
    private static String _pathToRaceCars = new File("src\\rcas\\resources\\RaceCars.json").getAbsolutePath();
    public static ArrayList<RaceCar> GetAllRaceCars(){
        try{
            System.out.println(_pathToRaceCars);
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(_pathToRaceCars));
            ArrayList<RaceCar> data = gson.fromJson(reader, new TypeToken<ArrayList<RaceCar>>(){}.getType()); // contains the whole reviews list
            return data;
        }
        catch(Exception ex){
            //Todo: Fix method
            return new ArrayList<RaceCar>();
        }
    }
    public static void AddRaceCar(RaceCar raceCar){
        ArrayList<RaceCar> raceCars = GetAllRaceCars();
        if(raceCars== null)
            raceCars = new ArrayList<RaceCar>();
        raceCars.add(raceCar);
        try (Writer writer = new FileWriter(_pathToRaceCars)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(raceCars, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}