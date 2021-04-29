package tests;

import models.UserSimple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class LombokTest {
    public Logger logger = LogManager.getLogger();

    @Test
    public void lombokTest() {
        UserSimple user = UserSimple.builder()
                .firstname("Test")
                .surname("01")
                .build();
        UserSimple user1 = UserSimple.builder()
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
