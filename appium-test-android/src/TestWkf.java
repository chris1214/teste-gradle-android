import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by christopher on 17/10/18.
 */
public class TestWkf {

    AndroidDriver driver;

    @Before
    public void start() throws MalformedURLException {
        //Diretorio onde contem a apk do aplicativo que sera automatizado
        File diretorioAplicacao = new File("/home/christopher/projetos/Android/APK");
        //Nome do aplicativo que deve ser
        File arquivoAplicacao = new File(diretorioAplicacao, "wkf-android-1.0.15-69-91-release.apk");

        DesiredCapabilities capacidade = new DesiredCapabilities();
        //Define que a plataforma que o teste sera executado e Android
        capacidade.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capacidade.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
        //Define que o aplicativo sera executado pelo emulador do Android
        capacidade.setCapability(MobileCapabilityType.DEVICE_NAME, "Android SDK built for x86_64");
        //Permissions
        capacidade.setCapability("autoGrantPermissions", "true");
        //Define qual o caminho onde esta o apk do aplicativo que sera automatizado
        capacidade.setCapability(MobileCapabilityType.APP, arquivoAplicacao.getAbsolutePath());
        capacidade.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "br.com.workfinity.workfinity.*");

        //Instancia o driver do Android apontando para o ip do servidor Appium e passando as configuracoes defindas acima
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capacidade);
    }

    @Test
    public void test() {
        // setUrl
        driver.findElement(By.id("configure_form_tenant_url_edit_text")).sendKeys("qa.workfinity.com.br");
        driver.findElement(By.id("configure_form_button")).click();

        // Entrar
        driver.findElement(By.id("br.com.workfinity.workfinity:id/enter_button")).click();

        //Menu
        driver.findElement(By.id("br.com.workfinity.workfinity:id/main_toolbar")).findElement(By.className("android.widget.ImageButton")).click();

        //sair
        driver.findElement(By.id("br.com.workfinity.workfinity:id/design_menu_item_text")).click();
    }
}
