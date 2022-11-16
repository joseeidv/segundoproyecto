package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverContext;
import utils.Reporte.EstadoPrueba;
import utils.Reporte.PdfQaNovaReports;
import utils.Validaciones;

public class Login {

    @FindBy(xpath = "//*[@id=\"imUname\"]")
    WebElement inputUsuario;

    @FindBy(xpath = "//*[@id=\"imPwd\"]")
    WebElement inputClave;

    @FindBy(xpath = "//*[@id=\"imLogin\"]/form/div[3]/input")
    WebElement btnIngresar;

    @FindBy(xpath = "//*[@id=\"imLogin\"]/form/div[1]/label/span")
    WebElement labelUsuario;

    public Login(){
        PageFactory.initElements(DriverContext.getDriver(),this );
    }

    public void ingresarUsuario(String usuario){
        Validaciones.validarObjeto(inputUsuario,"input usuario");
        PdfQaNovaReports.addWebReportImage("Despliegue Login", "Login desplegado correctamente", EstadoPrueba.PASSED, false);
        inputUsuario.sendKeys(usuario);
    }

    public void ingresarClave(String clave){
        inputClave.sendKeys(clave);
    }

    public void clickBtnIngresar(){
        PdfQaNovaReports.addWebReportImage("Datos Login", "Se ingresa usuario y contraseña", EstadoPrueba.PASSED, false);
        btnIngresar.click();
    }

    public void validarTextoUsuario(String texto) {
        Validaciones.validarTexto(labelUsuario, texto);
    }
}
