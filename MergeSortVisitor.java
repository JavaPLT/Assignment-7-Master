import java.util.ArrayList;
import java.util.List;

/** Sorts an IntList formulated as a visitor. **/
public class MergeSortVisitor implements IntListVisitor{
    public static final MergeSortVisitor ONLY = new MergeSortVisitor();
    private MergeSortVisitor() { }

    @Override
    public Object forEmptyIntList(EmptyIntList host) {
        return host;
    }

    @Override
    public Object forConsIntList(ConsIntList host) {
        List<ConsIntList> list = new ArrayList<ConsIntList>();
        list = (List<ConsIntList>) host.visit(PartitionVisitor.ONLY);
        if((int) list.get(0).visit(LengthVisitor.ONLY) != 1){
            list.set(0, (ConsIntList) list.get(0).visit(MergeSortVisitor.ONLY));
        }
        if((int) list.get(1).visit(LengthVisitor.ONLY) != 1){
            list.set(1, (ConsIntList) list.get(1).visit(MergeSortVisitor.ONLY));
        }
        return list.get(0).visit(new MergeVisitor(list.get(1)));
    }
}
