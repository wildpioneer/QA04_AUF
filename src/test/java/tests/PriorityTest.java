package tests;

import org.testng.annotations.Test;

public class PriorityTest {

    @Test (priority = 1)
    public void pr1Test() {
        System.out.println("pr1Test");
    }

    @Test (priority = 2)
    public void pr2Test() {
        System.out.println("pr2Test");
    }

    @Test (priority = 1)
    public void pr3Test() {
        System.out.println("pr3Test");
    }
}
