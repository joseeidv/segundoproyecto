package utils;

import org.openqa.selenium.WebElement;
import utils.Reporte.EstadoPrueba;
import utils.Reporte.PdfQaNovaReports;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

import static utils.Constants.Constants.AMBIENTE;

public class Utils {

    public static String tipoAmbiente() {
        if (AMBIENTE.equals("QA")) {
            return "Certificación";
        } else if (AMBIENTE.equals("INT")) {
            return "Integración";
        } else {
            return "Desarrollo";
        }
    }

    public static byte[] getCaptura(String datosBD, int heightImagen) throws Exception {

        HashMap<RenderingHints.Key, Object> renderingProperties = new HashMap<>();

        //String screenText = StringUtils.join(s.readScreen(), "\n");
        renderingProperties.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderingProperties.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        renderingProperties.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        Font font = new Font("Consolas", Font.PLAIN, 12);
        FontRenderContext fontRenderContext = new FontRenderContext(null, true, true);

        BufferedImage bufferedImage = new BufferedImage(600, heightImagen, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setRenderingHints(renderingProperties);
        graphics2D.setBackground(Color.black);
        graphics2D.setColor(Color.white);
        graphics2D.setFont(font);
        graphics2D.clearRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        TextLayout textLayout = new TextLayout(datosBD, font, fontRenderContext);

        Double cont = 0.0;
        for (String line : datosBD.split(",")) {
            graphics2D.drawString(line, 15, (int) (15 + cont * textLayout.getBounds().getHeight()));
            cont = cont + 1.5;
        }

        graphics2D.dispose();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "PNG", out);
        return out.toByteArray();
    }

    public static void descargarArchivo (WebElement elementoDescarga) throws IOException {
        String ruta = ReadProperties.readFromConfig("Propiedades.properties").getProperty("directorioDescargas");
        String url = elementoDescarga.getAttribute("href");
        String nombreArchivo = url.substring(url.lastIndexOf("/") +1);
        File file = new File(ruta + "//" +nombreArchivo);
        if (file.exists()) {
            System.out.println("Archivo '" +nombreArchivo + "' existe, se procede a borrarlo");
            try {
                file.delete();
                System.out.println("Archivo '"+nombreArchivo+"' borrado");
                PdfQaNovaReports.addReport("Borrado "+nombreArchivo, "El archivo '"+nombreArchivo+"' existe en la ruta: '"+ruta+"', por lo cual se procede a borrarlo.", EstadoPrueba.PASSED, false);
            } catch (Exception e) {
                System.out.println("Archivo '"+nombreArchivo+"' no pudo ser borrado");
                PdfQaNovaReports.addReport("Borrado "+nombreArchivo, "El archivo '"+nombreArchivo+"' existe en la ruta: '"+ruta+"', pero no pudo ser borrado.", EstadoPrueba.FAILED, true);
            }
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) (new URL(url).openConnection());
        httpURLConnection.setRequestMethod("GET");
        try (InputStream inputStream = httpURLConnection.getInputStream()) {
            Files.copy(inputStream, new File(ruta + "//" + nombreArchivo).toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Descarga Realizada");
            PdfQaNovaReports.addReport("Descarga Archivo" + nombreArchivo, "Se realiza correctamente la descaga de archivo '"+nombreArchivo+"', el cual se ubica en: \n" +ruta, EstadoPrueba.PASSED, false);
        } catch (Exception e) {
            PdfQaNovaReports.addReport("Descarga Archivo" + nombreArchivo, "No se realiza la descaga de archivo '"+nombreArchivo+"'", EstadoPrueba.FAILED, true);
        }
    }

}
