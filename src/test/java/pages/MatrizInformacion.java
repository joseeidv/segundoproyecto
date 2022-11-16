package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverContext;
import utils.Validaciones;

import java.util.ArrayList;
import java.util.List;

public class MatrizInformacion {

    @FindBy(xpath = "//*[@id=\"imPgTitle\"]")
    private WebElement titulo;

    @FindBy(xpath = "//*[@id=\"pluginAppObj_4_01_filter_field\"]")
    private WebElement txtFiltro;

    @FindBy(xpath = "//*[@id=\"pluginAppObj_4_01_filter_button\"]")
    private WebElement btnFiltrar;

    @FindBy(xpath = "//*[@id=\"pluginAppObj_4_01_jtable\"]/div/div[4]/div[1]/span[1]/span[8]")
    private WebElement btnPaginaSiguiente;

    @FindBy(xpath = "//*[@id=\"pluginAppObj_4_01_jtable\"]/div/div[4]/div[2]")
    private WebElement spanCantidad;

    @FindBy(xpath = "//*[@id=\"pluginAppObj_4_01_jtable\"]/div")
    private WebElement tabla;

    public MatrizInformacion() {
        PageFactory.initElements(DriverContext.getDriver(), this);
    }

    public void validarDespliegue() {
        Validaciones.validarObjeto(titulo, "Titulo pagina");
    }

    public void validarTextoTitulo(String texto) {
        Validaciones.validarTexto(titulo, texto);
    }

    public void escribirFiltro(String filtro) {
        txtFiltro.sendKeys(filtro);
    }

    public void clickBtnFiltrar() {
        btnFiltrar.click();
    }

    public List<String> recuperarDatosFiltrados() {
        WebElement cuerpo = tabla.findElement(By.tagName("tbody"));
        WebElement fila;
        List<String> datosFila = new ArrayList<String>();
        String datosColumna = "";
        for (int x = 0; x < cuerpo.findElements(By.tagName("tr")).size(); x++) {
            fila = cuerpo.findElements(By.tagName("tr")).get(x);
            for (int y = 0; y < fila.findElements(By.tagName("td")).size(); y++) {
                datosColumna = datosColumna + fila.findElements(By.tagName("td")).get(y).getText() + ";";
            }
            datosColumna = datosColumna.substring(0, datosColumna.length() - 1);
            System.out.println("Se recupera la fila número "+(x+1)+", con los siguentes datos: \n" +datosColumna);
            datosFila.add(datosColumna);
            datosColumna = "";
        }
        return datosFila;
    }
}
