package testSuites;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testClasses.Logeo;
import utils.Constants.Navegador;
import utils.DriverContext;
import utils.ReadProperties;
import utils.Reporte.PdfQaNovaReports;

import java.text.ParseException;

public class Prueba {

    ChromeDriver webDriver;
    String url = "https://www.qanovagroup.com/piloto/";

    @BeforeTest
    public void setUp(){
        DriverContext.setUp(Navegador.Chrome, ReadProperties.readFromConfig("Propiedades.properties").getProperty("url"));
        PdfQaNovaReports.createPDF();
    }

    @AfterTest
    public void closeDriver(){
        DriverContext.closeDriver();
    }

    @Test
    public void pruebaLogin() throws ParseException {
        Logeo logeo = new Logeo();
        String usuario = ReadProperties.readFromConfig("Propiedades.properties").getProperty("usuario");
        String clave = ReadProperties.readFromConfig("Propiedades.properties").getProperty("clave");
        logeo.CasoLogin1(usuario, clave);
        PdfQaNovaReports.closePDF();
    }
}
