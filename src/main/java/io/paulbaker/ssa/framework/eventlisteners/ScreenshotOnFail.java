package io.paulbaker.ssa.framework.eventlisteners;

import io.paulbaker.ssa.framework.SeleniumListener;
import org.apache.log4j.Logger;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.util.Objects;

/**
 * Created by paul on 9/24/15.
 */
@SeleniumListener
public class ScreenshotOnFail extends RunListener {

  private final Logger logger = Logger.getLogger(this.getClass());

  @Override
  public void testFailure(Failure failure) throws Exception {

  }

//  @Override
//  public void onException(Throwable throwable, WebDriver driver) {
//    if (Objects.isNull(driver)) {
//      logger.error("An exception occurred, but WebDriver is null. Cannot take screenshot.");
//    }
////    driver instanceof TakesScreenshot
//    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//    File screenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
//  }

}
