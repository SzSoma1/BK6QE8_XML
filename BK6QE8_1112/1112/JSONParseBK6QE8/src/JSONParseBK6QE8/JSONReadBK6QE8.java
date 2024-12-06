package JSONParseBK6QE8;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JSONReadBK6QE8 {

    public static void main(String[] args) {
        
        String filePath = "BK6QE8_orarend.json";

  
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {
           
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray orarendArray = (JSONArray) jsonObject.get("SZS_orarend");

           
            for (Object item : orarendArray) {
                JSONObject ora = (JSONObject) item;

                System.out.println("ID: " + ora.get("id"));
                System.out.println("T�pus: " + ora.get("tipus"));
                System.out.println("T�rgy: " + ora.get("targy"));

                
                JSONObject idopont = (JSONObject) ora.get("idopont");
                System.out.println("Nap: " + idopont.get("nap"));
                System.out.println("Kezd�s: " + idopont.get("tol"));
                System.out.println("Befejez�s: " + idopont.get("ig"));

                System.out.println("Helysz�n: " + ora.get("helyszin"));
                System.out.println("Oktat�: " + ora.get("oktato"));
                System.out.println("Szak: " + ora.get("szak"));
                System.out.println("---------------------------");
            }

        } catch (IOException e) {
            System.out.println("F�jl hiba: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("JSON form�tum hiba: " + e.getMessage());
        }
    }
}