package JSONParseBK6QE8;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONWriteBK6QE8 {

    public static void main(String[] args) {
        String inputFilePath = "BBK6QE8_orarend.json";
        String outputFilePath = "orarendBK6QE8.json";

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(inputFilePath)) {
       
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray orarendArray = (JSONArray) jsonObject.get("SZS_orarend");

     
            try (FileWriter file = new FileWriter(outputFilePath)) {
                file.write("{\n  \"KS_orarend\": [\n");
                
                for (int i = 0; i < orarendArray.size(); i++) {
                    JSONObject ora = (JSONObject) orarendArray.get(i);

                    file.write("    {\n");
                    file.write("      \"id\": \"" + ora.get("id") + "\",\n");
                    file.write("      \"tipus\": \"" + ora.get("tipus") + "\",\n");
                    file.write("      \"targy\": \"" + ora.get("targy") + "\",\n");

                    JSONObject idopont = (JSONObject) ora.get("idopont");
                    file.write("      \"idopont\": {\n");
                    file.write("        \"nap\": \"" + idopont.get("nap") + "\",\n");
                    file.write("        \"tol\": \"" + idopont.get("tol") + "\",\n");
                    file.write("        \"ig\": \"" + idopont.get("ig") + "\"\n");
                    file.write("      },\n");

                    file.write("      \"helyszin\": \"" + ora.get("helyszin") + "\",\n");
                    file.write("      \"oktato\": \"" + ora.get("oktato") + "\",\n");
                    file.write("      \"szak\": \"" + ora.get("szak") + "\"\n");
                    file.write("    }" + (i < orarendArray.size() - 1 ? ",\n" : "\n"));
                }

                file.write("  ]\n}");
                System.out.println("Strukturált JSON fájlba mentés befejezve: " + outputFilePath);
            }

           
            System.out.println("Adatok blokk formában:");
            for (Object item : orarendArray) {
                JSONObject ora = (JSONObject) item;

                System.out.println("ID: " + ora.get("id"));
                System.out.println("Típus: " + ora.get("tipus"));
                System.out.println("Tárgy: " + ora.get("targy"));

                JSONObject idopont = (JSONObject) ora.get("idopont");
                System.out.println("Nap: " + idopont.get("nap"));
                System.out.println("Kezdés: " + idopont.get("tol"));
                System.out.println("Befejezés: " + idopont.get("ig"));

                System.out.println("Helyszín: " + ora.get("helyszin"));
                System.out.println("Oktató: " + ora.get("oktato"));
                System.out.println("Szak: " + ora.get("szak"));
                System.out.println("---------------------------");
            }

        } catch (IOException e) {
            System.out.println("Fájl hiba: " + e.getMessage());
        } catch (ParseException e) {
        	System.out.println("JSON formátum hiba: " + e.getMessage());
        }
    }
}