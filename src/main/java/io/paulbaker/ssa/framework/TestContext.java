package io.paulbaker.ssa.framework;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by paul on 9/24/15.
 */
@Configuration
public class TestContext {

  private final Logger logger = Logger.getLogger(this.getClass());

  @Autowired
  private ApplicationContext applicationContext;

  @Bean
  @Scope("TestCaseScope")
  public WebDriver webDriver() {
    logger.info("Instantiating webdriver.");
    EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(new FirefoxDriver());
    logger.info("Registering event listeners.");
    applicationContext.getBeansOfType(WebDriverEventListener.class).values().forEach(eventListener -> {
      logger.info("Registering " + eventListener.getClass());
      eventFiringWebDriver.register(eventListener);
    });
    return eventFiringWebDriver;
  }


}
