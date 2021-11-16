/** merges this list with the input list other, assuming
 that this list and other are sorted in ascending order*/
public class MergeVisitor implements IntListVisitor{
    private IntList intlist1;
    MergeVisitor(IntList other){intlist1 = other;}
    int num = IntList.empty().toString().length();

    @Override
    public Object forEmptyIntList(EmptyIntList host) {
        return intlist1;
    }

    @Override
    public Object forConsIntList(ConsIntList host) {

        if (host.toString().length() == num) {
            return intlist1;
        }
        else if(intlist1.toString().length() == num){
            return host;
        }
        else{
            return host.visit(new MergeHelpVisitor((ConsIntList) intlist1));
        }
    }
}
