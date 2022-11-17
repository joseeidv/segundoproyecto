package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverContext;
import utils.ReadProperties;
import utils.Utils;
import utils.Validaciones;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class DescargaArchivos {

    @FindBy(xpath = "/html/body/div[3]/div[2]/div[2]/ul/li[3]/a/span/span/span[2]")
    private WebElement btnDescargarArchivos;

    @FindBy(xpath = "//*[@id=\"imPgTitle\"]")
    private WebElement titulo;

    @FindBy(xpath = "//*[@id=\"pluginAppObj_5_06\"]")
    private WebElement btnDescarga;

    @FindBy(xpath = "//*[@id=\"imTextObject_5_05_tab0\"]/div/div[2]/a")
    private WebElement linkDescarga;

    @FindBy(xpath = "//*[@id=\"imCellStyle_4\"]/a")
    private WebElement imagenDescarga;

    public DescargaArchivos() {
        PageFactory.initElements(DriverContext.getDriver(), this);
    }

    public void validarDespliegue() {
        Validaciones.validarObjeto(titulo, "Titulo pagina");
    }

    public void validarTextoTitulo(String texto) {
        Validaciones.validarTexto(titulo, texto);
    }

    public void ingresarDescargarArchivos() {
        btnDescargarArchivos.click();
    }

    public void descargarPorBoton() throws IOException {
        String ruta = ReadProperties.readFromConfig("Propiedades.properties").getProperty("directorioDescargas");
        String url = btnDescarga.findElement(By.tagName("a")).getAttribute("href");
        String nombreArchivo = url.substring(url.lastIndexOf("/"));
        HttpURLConnection httpURLConnection = (HttpURLConnection) (new URL(url).openConnection());
        httpURLConnection.setRequestMethod("GET");
        InputStream inputStream = httpURLConnection.getInputStream();
        Files.copy(inputStream, new File(ruta + "" + nombreArchivo).toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Descarga Realizada");
    }

    public void descargarPorLink() throws IOException {
        Utils.descargarArchivo(linkDescarga);
    }

    public void descargarPorImagen() throws IOException {
        Utils.descargarArchivo(imagenDescarga);
    }
}


