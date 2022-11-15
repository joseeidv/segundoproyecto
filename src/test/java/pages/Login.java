package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

    @FindBy(xpath = "//*[@id=\"imUname\"]")
    WebElement inputUsuario;

    @FindBy(xpath = "//*[@id=\"imPwd\"]")
    WebElement inputClave;

    @FindBy(xpath = "//*[@id=\"imLogin\"]/form/div[3]/input")
    WebElement btnIngresar;

    public Login(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public void ingresarUsuario(String usuario){
        inputUsuario.sendKeys(usuario);
    }

    public void ingresarClave(String clave){
        inputClave.sendKeys(clave);
    }

    public void clickBtnIngresar(){
        btnIngresar.click();
    }

}