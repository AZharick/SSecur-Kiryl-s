package misthyx.initial;

import misthyx.initial.util.PswEncoder;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InitialApplication implements CommandLineRunner {

	public static final String ANSI_GREEN = "\u001b[32;1m";
	public static final String ANSI_RESET = "\u001B[0m";

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(InitialApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);

		System.out.println(PswEncoder.encodePsw("Alex"));
		System.out.println(ANSI_GREEN + "\n    *** App started and running... ***\n"+ANSI_RESET);
	}

	@Override
	public void run(String... args) {
	}

}