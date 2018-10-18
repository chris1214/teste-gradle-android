import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by christopher on 17/10/18.
 */
public class FirstTest {

    @Test
    public void test() throws MalformedURLException {

        //Diretorio onde contem a apk do aplicativo que sera automatizado
        File diretorioAplicacao = new File("/home/christopher/projetos/Android/APK");
        //Nome do aplicativo que deve ser
        File arquivoAplicacao = new File(diretorioAplicacao, "CalculaMediaFinal.apk");

        DesiredCapabilities capacidade = new DesiredCapabilities();
        //Define que a plataforma que o teste sera executado e Android
        capacidade.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capacidade.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
        //Define que o aplicativo sera executado pelo emulador do Android
        capacidade.setCapability(MobileCapabilityType.DEVICE_NAME, "Android SDK built for x86_64");
        //Define qual o caminho onde esta o apk do aplicativo que sera automatizado
        capacidade.setCapability(MobileCapabilityType.APP, arquivoAplicacao.getAbsolutePath());

        //Instancia o driver do Android apontando para o ip do servidor Appium e passando as configuracoes defindas acima
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capacidade);

        //Informa as notas do campo 1, 2 e 3
        driver.findElement(By.id("txtNota1")).sendKeys("7");
        driver.findElement(By.id("txtNota2")).sendKeys("6");
        driver.findElement(By.id("txtNota3")).sendKeys("8");
        //Clica no botao Calcular
        driver.findElement(By.id("com.exemplo.calculamediafinal:id/btnCalcular")).click();
        //Compara o valor gerado no cmapo media final com o valor esperado pelo teste
        Assert.assertEquals("7.0", driver.findElement(By.id("com.exemplo.calculamediafinal:id/txtMediaFinal")).getText());

        driver.quit();
    }

}
