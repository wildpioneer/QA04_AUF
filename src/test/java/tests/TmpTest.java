package tests;

import core.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TmpTest {
    private int attempt = 1;

    @Test
    public void A4Test() {
        System.out.println("Залогинится в систему...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test (dependsOnMethods = "A4Test", invocationCount = 4, threadPoolSize = 2)
    public void C2Test() {
        System.out.println("Создать новый проект...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //@Test (dependsOnMethods = "C2Test", retryAnalyzer = Retry.class)
    @Test (retryAnalyzer = Retry.class)
    public void D2Test() {
        System.out.println("Создать новый проект 2...");

        if (attempt < 3) {
            attempt++;
            throw new NullPointerException();
        }
    }

    @Test (dependsOnMethods = "D2Test", alwaysRun = true)
    public void C1Test() {
        System.out.println("Обновить существующий проект...");
    }

    @Test (dependsOnMethods = "C1Test")
    public void A40Test() {
        System.out.println("Удалить созданный проект...");
    }

    @Test (expectedExceptions = { NullPointerException.class})
    public void exceptionTest() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new NullPointerException();
    }
}
