
import com.finanzas.MainApp;
import com.finanzas.SpringConfig;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class AppLauncher {
    private static ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        springContext = new SpringApplicationBuilder(SpringConfig.class).run(args);
        MainApp.setSpringContext(springContext);
        javafx.application.Application.launch(MainApp.class, args);
    }
}
