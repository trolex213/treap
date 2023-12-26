// Troy Zhang

import org.junit.Assert;
import org.junit.Test;

public class TestTreap {

    @Test
    public void testToString(){

        Treap<Integer> testTree = new Treap <>(42);
        testTree.add (4 ,14);
        testTree.add (2 ,31);
        testTree.add (6 ,70);
        testTree.add (1 ,84);
        testTree.add (3 ,12);
        testTree.add (5 ,83);
        testTree.add (7 ,26);

        //System.out.println(testTree.toString());

        String treeOutput  = "   ( key = 1 , priority = 84)\n" +
                "         null\n" +
                "         ( key = 5 , priority = 83)\n" +
                "            ( key = 2 , priority = 31)\n" +
                "               null\n" +
                "               ( key = 4 , priority = 14)\n" +
                "                  ( key = 3 , priority = 12)\n" +
                "                     null\n" +
                "                     null\n" +
                "                  null\n" +
                "            ( key = 6 , priority = 70)\n" +
                "               null\n" +
                "               ( key = 7 , priority = 26)\n" +
                "                  null\n" +
                "                  null";

        Assert.assertTrue(testTree.toString().equals(treeOutput));


    }


    @Test
    public void testDelete(){

        Treap<Integer> testTree = new Treap <>(42);
        testTree.add (4 ,14);
        testTree.add (2 ,31);
        testTree.add (6 ,70);
        testTree.add (1 ,84);
        testTree.add (3 ,12);
        testTree.add (5 ,83);
        testTree.add (7 ,26);

        //Deleting the node with key = 5
        testTree.delete(5);

        String treeAfterDelete = "   ( key = 1 , priority = 84)\n" +
                "         null\n" +
                "         ( key = 6 , priority = 70)\n" +
                "            ( key = 2 , priority = 31)\n" +
                "               null\n" +
                "               ( key = 4 , priority = 14)\n" +
                "                  ( key = 3 , priority = 12)\n" +
                "                     null\n" +
                "                     null\n" +
                "                  null\n" +
                "            ( key = 7 , priority = 26)\n" +
                "               null\n" +
                "               null";



        Assert.assertTrue(testTree.toString().equals(treeAfterDelete));


    }


}