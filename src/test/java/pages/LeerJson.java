package pages;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public class LeerJson {

    public LeerJson() {}

    public void recuperarJson() throws IOException {
        String url = "https://farmanet.minsal.cl/index.php/ws/getlocalesTurnos";
        InputStream is = new URL(url).openStream();
        Gson gson = new Gson();
        String linea = "";
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")))){
            String lineas;
            while ((lineas = rd.readLine()) != null) {
                linea = linea + lineas;
            }
            Type listadoFarmacias = new TypeToken<List<Farmacias>>(){}.getType();
            List<Farmacias> farmacias = gson.fromJson(linea, listadoFarmacias);
            System.out.println("Resuelto");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
