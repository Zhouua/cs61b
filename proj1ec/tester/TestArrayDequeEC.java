package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;
import student.StudentArrayDeque;
public class TestArrayDequeEC {
    @Test
    public void randomTest() {
        int count = 5000;
        StudentArrayDeque<Integer> stArray = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> tArray = new ArrayDequeSolution<>();
        for (int i = 0; i < count; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            switch (operationNumber) {
                case 0:
                    // addFirst
                    Integer item1 = StdRandom.uniform(0, 100);
                    stArray.addFirst(item1);
                    tArray.addFirst(item1);
                    System.out.println("addFirst(" + item1 + ")");
                    break;
                case 1:
                    //  addLast
                    Integer item2 = StdRandom.uniform(0, 100);
                    stArray.addLast(item2);
                    tArray.addLast(item2);
                    System.out.println("addLast(" + item2 + ")");
                    break;
                case 2:
                    // removeFirst
                    if (stArray.isEmpty()) break;
                    Integer expected1 = tArray.removeFirst();
                    Integer actual1 = stArray.removeFirst();
                    System.out.println("removeFirst()");
                    Assert.assertEquals("removeFirst is wrong!", expected1, actual1);
                    break;
                case 3:
                    // removeLast
                    if (stArray.isEmpty()) break;
                    Integer expected2 = tArray.removeLast();
                    Integer actual2 = stArray.removeLast();
                    System.out.println("removeLast()");
                    Assert.assertEquals("removeLast is wrong!", expected2, actual2);
                    break;
            }
        }
    }
}
