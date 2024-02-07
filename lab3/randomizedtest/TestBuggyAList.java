package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  @Test
    public void testThreeAddThreeRemove() {
      AListNoResizing<Integer> correctArray = new AListNoResizing<>();
      BuggyAList<Integer> buggyArray = new BuggyAList<>();
      correctArray.addLast(4);
      correctArray.addLast(5);
      correctArray.addLast(6);
      buggyArray.addLast(4);
      buggyArray.addLast(5);
      buggyArray.addLast(6);
      Assert.assertEquals(correctArray.removeLast(), buggyArray.removeLast());
      Assert.assertEquals(correctArray.removeLast(), buggyArray.removeLast());
      Assert.assertEquals(correctArray.removeLast(), buggyArray.removeLast());
  }

  @Test
  public void randomizedTest() {
    AListNoResizing<Integer> Expected = new AListNoResizing<>();
    BuggyAList<Integer> Actual = new BuggyAList<>();
    int N = 5000;
    for (int i = 0; i < N; i++) {
      int operationNumber = StdRandom.uniform(0, 4);
      if (operationNumber == 0) {
        // addLast()
        int randVal = StdRandom.uniform(0, 100);
        Expected.addLast(randVal);
        Actual.addLast(randVal);
      } else if (operationNumber == 1) {
        // size()
        int size1 = Expected.size();
        int size2 = Actual.size();
        assertEquals(size1, size2);
      } else if (operationNumber == 2 && Expected.size() > 0 && Actual.size() > 0) {
        // getLast() and removeLast()
        assertEquals(Expected.getLast(), Actual.getLast());
        Expected.removeLast();
        Actual.removeLast();
      } else if (operationNumber == 3 && Expected.size() > 0) {
        // get(int i)
        int ith = StdRandom.uniform(0, Expected.size());
        int get1 = Expected.get(ith);
        int get2 = Actual.get(ith);
        assertEquals(get1, get2);
      }
    }
  }
}
