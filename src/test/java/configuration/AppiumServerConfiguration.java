package configuration;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * Appium server configuration.
 */
@Configuration
public class AppiumServerConfiguration {

	@Value("${appium.install.location}")
	private String appiumInstallLocation;

	@Value("${appium.node.module}")
	private String appiumNodeModule;

	@Value("${appium.node.executable}")
	private String appiumNodeExecutable;

	@Value("${appium.main.js}")
	private String appiumMainJS;

	@Value("${appium.use.any.free.port}")
	private boolean appiumUseAnyFreePort;

	@Value("${appium.port}")
	private int port;

	@Bean
	public AppiumDriverLocalService appiumDriverLocalService() {
		if(appiumUseAnyFreePort) {
			return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
					.usingAnyFreePort()
					.usingDriverExecutable(new File(appiumInstallLocation + appiumNodeExecutable))
					.withAppiumJS(new File(appiumInstallLocation + appiumMainJS)));
		}
		return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingPort(port)
				.usingDriverExecutable(new File(appiumInstallLocation + appiumNodeExecutable))
				.withAppiumJS(new File(appiumInstallLocation + appiumMainJS)));
	}
}
