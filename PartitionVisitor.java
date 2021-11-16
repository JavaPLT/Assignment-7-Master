import java.util.ArrayList;
import java.util.List;

/** Partitions a IntList into 2 **/
public class PartitionVisitor implements IntListVisitor{
    public static final PartitionVisitor ONLY = new PartitionVisitor();
    private PartitionVisitor() { }

    @Override
    public Object forEmptyIntList(EmptyIntList host) {
        return host;
    }

    @Override
    public Object forConsIntList(ConsIntList host) {
        List<ConsIntList> list = new ArrayList<>();
        int num = (int) host.visit(LengthVisitor.ONLY);
        if(num == 1){
            return host;
        }
        else if((num % 2) == 0){
            int num1 = num / 2;
            int i = 1;
            ConsIntList half1 = new ConsIntList(host.first(), IntList.empty());
            ConsIntList half2 = (ConsIntList) host.rest();
            while(i != num1){
                half1 = half1.cons(half2.first());
                half2 = (ConsIntList) half2.rest();
                i += 1;
            }
            list.add(half1);
            list.add(half2);
            return list;
        }
        else{
            int num1 = num / 2 + 1;
            int i = 1;
            ConsIntList half1 = new ConsIntList(host.first(), IntList.empty());
            ConsIntList half2 = (ConsIntList) host.rest();
            while(i != num1){
                half1 = half1.cons(half2.first());
                half2 = (ConsIntList) half2.rest();
                i += 1;
            }
            list.add(half1);
            list.add(half2);
            return list;
        }
    }
}
