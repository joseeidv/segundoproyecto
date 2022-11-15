package testSuites;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import testClasses.BusquedaAnimalesGoogle;
import testClasses.Logeo;

public class Prueba {

    ChromeDriver webDriver;
    String url = "https://www.qanovagroup.com/piloto/";

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "driverNavegador/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get(url);
    }

    @AfterTest
    public void closeDriver(){

    }

    @Test
    public void buscarPerroEnGoogle(){
        BusquedaAnimalesGoogle busquedaAnimalesGoogle = new BusquedaAnimalesGoogle();
        busquedaAnimalesGoogle.busquedaPerro(webDriver);
    }

    @Test
    public void pruebaLogin(){
        Logeo logeo = new Logeo(webDriver);
        logeo.CasoLogin1("nvivas", "qanova");
    }
}
