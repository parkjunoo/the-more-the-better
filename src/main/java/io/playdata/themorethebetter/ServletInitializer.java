package io.playdata.themorethebetter;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TheMoreTheBetterApplication.class);
	}

}
<<<<<<< HEAD
      
=======
>>>>>>> c66bf4292fe92d24eda3698a6c35f503efae8366
