package DeborahAlvarenga_20240487.DeborahAlvarenga_20240487;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoModuloDeborahAlvarenga20240487Application {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())

		);
		SpringApplication.run(ProyectoModuloDeborahAlvarenga20240487Application.class, args);
	}

}
