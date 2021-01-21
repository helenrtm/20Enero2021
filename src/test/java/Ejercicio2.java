import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Ejercicio2 {

    public static void main(String[] args)

    {
        String Loguin ="/html/body/div[2]/div[1]";
        String Usuario= "//*[@id=\"user-name\"]";
        String Clave="//*[@id=\"password\"]";

        String Name="//*[@id=\"first-name\"]";
        String Lastname="//*[@id=\"last-name\"]";
        String Postal="//*[@id=\"postal-code\"]";

        String EtiquetaProd = "//*[@id=\"inventory_filter_container\"]/div";
        String VerifContenedor= "//*[@id=\"inventory_container\"]/div";

        String VerifCarrito="//*[@id=\"contents_wrapper\"]/div[2]";
        String EtiqCheckout="//*[@id=\"contents_wrapper\"]/div[2]";
        String ContCheckout="//*[@id=\"checkout_info_container\"]/div/form";
        String BtnCheckout="//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]";

        String BtnContinue="//*[@id=\"checkout_info_container\"]/div/form/div[2]/input";
        String SubTotal01="//*[@id=\"checkout_summary_container\"]/div/div[1]/div[3]/div[2]/div[2]";
        String SubTotal02="//*[@id=\"checkout_summary_container\"]/div/div[1]/div[4]/div[2]/div[2]";
        String SubTotal03="//*[@id=\"checkout_summary_container\"]/div/div[1]/div[5]/div[2]/div[2]";

        String ItemTotal="//*[@id=\"checkout_summary_container\"]/div/div[2]/div[5]";
        String BtnFinish="//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]/a[2]";
        String EtiqFinish="//*[@id=\"contents_wrapper\"]/div[2]";
        String MsjConfirm="//*[@id=\"checkout_complete_container\"]/div";
        String TitleFin="//*[@id=\"checkout_complete_container\"]/h2";

        WebElement inputUser = null;
        WebElement inputPass=null;

        WebElement inputName=null;
        WebElement inputLastname=null;
        WebElement inputPostal=null;

        WebDriver driver = null;
        WebDriverManager.chromedriver().version("87.0.4280.88").setup();
        driver = new ChromeDriver();

        //Abrimos el browser
        driver.get("https://www.saucedemo.com/");

        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Loguin))); //verificamos se visualice contenedor de loguin

        inputUser = driver.findElement(By.xpath(Usuario));
        inputPass = driver.findElement(By.xpath(Clave));

        inputUser.sendKeys("standard_user");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        inputPass.sendKeys("secret_sauce");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        inputPass.sendKeys(Keys.ENTER); //al terminar de ingresar pass "se presiona enter"

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EtiquetaProd)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VerifContenedor)));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Obtener nombres de productos

        System.out.println("Obteniendo nombres de productos");


        String[] prod = new String[6];
        prod[0] = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText() ;
        prod[1] = driver.findElement(By.xpath("//*[@id=\"item_0_title_link\"]/div")).getText() ;
        prod[2] = driver.findElement(By.xpath("//*[@id=\"item_1_title_link\"]/div")).getText() ;
        prod[3] = driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]/div")).getText() ;
        prod[4] = driver.findElement(By.xpath("//*[@id=\"item_2_title_link\"]/div")).getText() ;
        prod[5] = driver.findElement(By.xpath("//*[@id=\"item_3_title_link\"]/div")).getText() ;

        System.out.println(" Ordenar de forma Ascendente ");
        Arrays.sort(prod);
        for(String i: prod)
            System.out.println(i);

        System.out.println(" ");

        System.out.println("Orden de forma Descendente");
        Arrays.sort(prod, Collections.reverseOrder());
        for(String i: prod)
            System.out.println(i);

        //Agregar los productos al carrito
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[2]/div[3]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[3]/div[3]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[4]/div[3]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[5]/div[3]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[6]/div[3]/button")).click();



        // Carrito de compras
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VerifCarrito)));

        driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[4]/div[2]/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[5]/div[2]/div[2]/button")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BtnCheckout)));
        driver.findElement(By.xpath(BtnCheckout)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EtiqCheckout)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContCheckout)));

        inputName = driver.findElement(By.xpath(Name));
        inputLastname = driver.findElement(By.xpath(Lastname));
        inputPostal = driver.findElement(By.xpath(Postal));

        inputName.sendKeys("Rosa");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        inputLastname.sendKeys("Alv Vald");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        inputPostal.sendKeys("051");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath(BtnContinue)).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("Sub Total de Productos");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SubTotal01)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SubTotal02)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SubTotal03)));

        System.out.println(driver.findElement(By.xpath("//*[@id=\"item_5_title_link\"]/div")).getText()+": "+driver.findElement(By.xpath(SubTotal01)).getText().substring(1));
        System.out.println(driver.findElement(By.xpath("//*[@id=\"item_2_title_link\"]/div")).getText()+": "+driver.findElement(By.xpath(SubTotal02)).getText().substring(1));
        System.out.println(driver.findElement(By.xpath("//*[@id=\"item_3_title_link\"]/div")).getText()+": "+driver.findElement(By.xpath(SubTotal03)).getText().substring(1));

        float st01 = Float.parseFloat(driver.findElement(By.xpath(SubTotal01)).getText().substring(1));
        float st02 = Float.parseFloat(driver.findElement(By.xpath(SubTotal02)).getText().substring(1));
        float st03 = Float.parseFloat(driver.findElement(By.xpath(SubTotal03)).getText().substring(1));
        float sum = st01 + st02 + st03;

        System.out.println("Subtotal(Resultado Esperado): " + sum);

        System.out.println("Subtotal(Resultado Obtenido): " + driver.findElement(By.xpath(ItemTotal)).getText().substring(13)); //valor obtenido
        float obt = Float.parseFloat(driver.findElement(By.xpath(ItemTotal)).getText().substring(13));

        if(sum==obt) {
            System.out.println("El resultado es correcto");
        }
        else{
            System.out.println("El resultado es incorrecto");
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BtnFinish)));
        driver.findElement(By.xpath(BtnFinish)).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(EtiqFinish)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TitleFin)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MsjConfirm)));

        System.out.println("");
        System.out.println(" Mensaje de Confirmación");
        System.out.println("Msj Obtenido: " + driver.findElement(By.xpath(TitleFin)).getText());

        String txtObt = driver.findElement(By.xpath(TitleFin)).getText();
        String txtEsp= "THANK YOU FOR YOUR ORDER";

        if (txtObt.equals(txtEsp)){
            System.out.println("Exito");
        }
        else{
            System.out.println("Error ");
        }

        driver.get("https://www.saucedemo.com/");
        System.out.println("Fin del test");
        driver.close();

    }
}
