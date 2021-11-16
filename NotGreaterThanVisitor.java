/** returns a list of elements in this list that are
 less than or equal to bound . **/
public class NotGreaterThanVisitor implements IntListVisitor{
    private int num;
    NotGreaterThanVisitor(int bound){num = bound;}

    @Override
    public Object forEmptyIntList(EmptyIntList host) {return host;}

    @Override
    public Object forConsIntList(ConsIntList host) {
        if(host.first() > num){
            return host.rest().visit(NotGreaterThanVisitor.this);
        }
        else{
            return(new ConsIntList(host.first(), (IntList) host.rest().visit(NotGreaterThanVisitor.this)));
        }
    }
}
