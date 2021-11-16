import junit.framework.Assert;
import junit.framework.TestCase;  // imported from the class files in junit.jar
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** A JUnit test case class.
 * Every method starting with the word "test" will be called when running this test class with JUnit.
 */
public class IntListTest extends TestCase {

    EmptyIntList e = IntList.empty();

    IntList l1 = e.cons(7).cons(5).cons(3);
    IntList l11 = e.cons(3).cons(5).cons(7); // the reverse of l1
    IntList l12 = e.cons(7).cons(5);           // l1 without 3
    IntList l13 = e.cons(8).cons(5).cons(3); // l1 replacing 7 with 8
    IntList l14 = e.cons(7).cons(7).cons(5).cons(5).cons(3).cons(3); //l1 twice for merge
    IntList l15 = e.cons(8).cons(4).cons(2);
    IntList l16 = e.cons(8).cons(7).cons(5).cons(4).cons(3).cons(2);
    IntList l17 = e.cons(8).cons(5).cons(7).cons(2).cons(3).cons(4); //l16 rearranged for mergesort
    IntList l2 = e.cons(3).cons(7).cons(5);  // structurally equal to l5 below
    IntList l3 = IntList.countDown(50);
    IntList l4 = IntList.countUp(50);
    IntList l5 = e.cons(3).cons(7).cons(5);  // structurally equal to l2 above


    /** Tests Lisp-like toString() method */
    public void testToString() {
        assertEquals("empty list string", "()", e.toString());
        assertEquals("Non-empty list string", l1.toString(), l2.sort().toString());
    }

    /** Tests equals(Object o) method */
    public void testEquals() {
        assertTrue("empty list reflexivity", e.equals(e));
        assertTrue("Non-empty IntList", l2.equals(l5));
    }

    /** Tests sort() method */
    public void testSort() {
        assertEquals("empty sort", e, e.sort());
        assertEquals("sort inductive test", l1.toString(), l2.sort().toString());
        assertEquals("big sort test [toString]", l4.toString(), l3.sort().toString());  // toString() equality
        assertEquals("big sort test", l4, l3.sort());
    }

    /** Tests LengthVisitor.  */
    public void testLength() {
        assertEquals("empty list test", e, EmptyIntList.ONLY );
        assertEquals("LengthVisitor empty test", 0, e.visit(LengthVisitor.ONLY));
        assertEquals("LengthVisitor inductive test", 3, l1.visit(LengthVisitor.ONLY));
    }

    /** Tests ScalarProductVisitor. */
    public void testScalaProduct() {
        assertEquals("ScalarProductVisitor empty test", 0, e.visit(new ScalarProductVisitor(e)));
        assertEquals("LengthVisitor inductive test", 71, l1.visit(new ScalarProductVisitor(l2)));
    }

    /** Tests ReverseVisitor **/
    public void testReverseVisitor(){
        assertEquals("ReverseVisitor empty test", e, e.visit(ReverseVisitor.Singleton));
        assertEquals("ReverseVisitor inductive test", l11.toString(), l1.visit(ReverseVisitor.Singleton).toString());
    }

    /** Tests NotGreaterThan **/
    public void testNotGreaterThan(){
        assertEquals("notGreaterThan empty test", e, e.visit(new NotGreaterThanVisitor(5)));
        assertEquals("NotGreaterThan inductive test", l1.toString(), l1.visit(new NotGreaterThanVisitor(8)).toString());
    }

    /** Tests remove **/
    public void testRemove (){
        assertEquals("Remove empty test", e, e.visit(new RemoveVisitor(10)));
        assertEquals("Remove Inductive Test", l12.toString(), l1.visit(new RemoveVisitor(3)).toString());
    }

    /** Tests subst **/
    public void testSubst (){
        assertEquals("Subst empty test", e, e.visit(new SubstVisitor(10,12)));
        assertEquals("Subst Inductive Test", l13.toString(), l1.visit(new SubstVisitor(7, 8)).toString());
    }

    /** Tests merge **/
    public void testMerge (){
        assertEquals("Merge empty test", e, e.visit(new MergeVisitor(e)));
        assertEquals("Merge inductive test 1", l1.toString(), e.visit(new MergeVisitor(l1)).toString());
        assertEquals("Merge inductive test 2", l1.toString(), l1.visit(new MergeVisitor(e)).toString());
        assertEquals("Merge Inductive Test 3", l14.toString(), l1.visit(new MergeVisitor(l1)).toString());
        assertEquals("Merge Inductive Test 4", l16.toString(), l1.visit(new MergeVisitor(l15)).toString());

    }

    /** Tests mergeSort **/
    public void testMergeSort (){
        assertEquals("MergeSort empty test", e, e.visit(MergeSortVisitor.ONLY));
        assertEquals("MergeSort inductive test", l1, l1.visit(MergeSortVisitor.ONLY));
        assertEquals("MergeSort inductive Test", l1, l2.visit(MergeSortVisitor.ONLY));
        assertEquals("MergeSort inductive Test", l16, l17.visit(MergeSortVisitor.ONLY));

    }
}
