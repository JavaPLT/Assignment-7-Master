/** Constructs a list that is the reversal of this with the help of a recursive helper**/
class ReverseVisitor implements IntListVisitor{
    public static final ReverseVisitor Singleton = new ReverseVisitor();
    private ReverseVisitor() {}

    @Override
    public Object forEmptyIntList(EmptyIntList host) {
        return host;
    }

    @Override
    public Object forConsIntList(ConsIntList host) {
        return host.visit(new ReverseHelpVisitor(EmptyIntList.ONLY));
    }
}

/** Takes in two IntLists and recursively building up one of them as an accumulator which allows it to
 * build up the reverse of the original IntList
 * **/
class ReverseHelpVisitor implements IntListVisitor{
    private IntList accum;
    ReverseHelpVisitor(IntList o){accum = o;}

    @Override
    public Object forEmptyIntList(EmptyIntList host) {
        return accum;
    }

    @Override
    public Object forConsIntList(ConsIntList host) {
        return host.rest().visit(new ReverseHelpVisitor(accum.cons(host.first())));
    }
}
