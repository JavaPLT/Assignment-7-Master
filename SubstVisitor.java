/** Returns a list of all elements in this list
 with oldN replaced by newN. */
public class SubstVisitor implements IntListVisitor{
    private int oldNum;
    private int newNum;
    SubstVisitor(int oldN, int newN){
        oldNum = oldN;
        newNum = newN;
    }

    @Override
    public Object forEmptyIntList(EmptyIntList host) {
        return host;
    }

    @Override
    public Object forConsIntList(ConsIntList host) {
        if(host.first() != oldNum){
            return (new ConsIntList(host.first(), (IntList) host.rest().visit(new SubstVisitor(oldNum, newNum))));
        }
        else{
            return(new ConsIntList(newNum, (IntList) host.rest().visit(new SubstVisitor(oldNum, newNum))));
        }
    }
}
