package at.technikumwien.mse25.awt.hotelmario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"at.technikumwien.mse25.awt.hotelmario",
		"io.swagger"
})
public class HotelmarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelmarioApplication.class, args);
	}
}
