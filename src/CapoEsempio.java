import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CapoEsempio {
    private static List<Capo> capi;

    public CapoEsempio() {
        this.capi = new ArrayList<>();
    }

    public static List<Capo> getCapi() {
        return capi;
    }

    public void caricaCapi(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(";");
                if (parts.length != 6) {
                    System.out.println("Riga non valida: " + line);
                    continue;
                }
                int id = Integer.parseInt(parts[0]);
                String dataInserimento = parts[1];
                String tipologia = parts[2];
                String marca = parts[3];
                String taglia = parts[4];
                double prezzo = Double.parseDouble(parts[5]);
                Capo capo = new Capo(id, dataInserimento, tipologia, marca, taglia, prezzo);
                capi.add(capo);
            }
        } catch (IOException e) {
            System.out.println("Errore durante la lettura del file: " + e.getMessage());
        }
    }
}
