package tests.uiTests;

import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class LombokTest {
    public Logger logger = LogManager.getLogger();

    @Test
    public void lombokTest() {
        User user = User.builder()
                .firstname("Test")
                .surname("01")
                .build();
        User user1 = User.builder()
                .firstname("Test")
                .surname("01")
                .build();

        logger.fatal("FATAL: Все плохо!!!!");
        logger.error("ERROR: Плохо но не все!!!");
        logger.info("INFO: Просто чтобы ты знал!!!");
        logger.debug("DEBUG: Для отладки!!!");
        logger.trace("TRACE: Абсолютно все!!!");
    }
}
