package testClasses;

import org.openqa.selenium.WebDriver;
import pages.CargaInformacion;
import pages.Login;

public class Logeo {

    private Login login;
    private CargaInformacion cargaInformacion;
    WebDriver webDriver;

    public Logeo(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void CasoLogin1(String usuario, String clave){
        login = new Login(webDriver);
        cargaInformacion = new CargaInformacion(webDriver);
        login.ingresarUsuario(usuario);
        login.ingresarClave(clave);
        login.clickBtnIngresar();
        cargaInformacion.recuperarTitulo();
        cargaInformacion.rellenarCampoTexto("Prueba");
        cargaInformacion.rellenarCampoMail("prueba@gmail.com");
        cargaInformacion.rellenarCampoAreaTexto("prueba texto largo");
        cargaInformacion.rellenarFecha("26/02/2001");
        cargaInformacion.rellenarLista("valor 3");
        cargaInformacion.seleccionMultiple("1,3");
        cargaInformacion.seleccionRadioButton(2);
        cargaInformacion.clickBtnEnviar();
    }
}
