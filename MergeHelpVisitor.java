/** Helper function for MergeVisitor **/
public class MergeHelpVisitor implements IntListVisitor{
    private ConsIntList intlist1;
    MergeHelpVisitor(ConsIntList other){intlist1 = other;}

    @Override
    public Object forEmptyIntList(EmptyIntList host) {
        return null;
    }

    @Override
    public Object forConsIntList(ConsIntList host) {
        if (host.first() <= intlist1.first()) {
            return new ConsIntList(host.first(), (IntList) intlist1.visit(new MergeVisitor(host.rest())));
        } else {
            return new ConsIntList(intlist1.first(), (IntList) host.visit(new MergeVisitor(intlist1.rest())));
        }
    }
}
