package Common;

/**
 * Created by Administrator on 2017-09-21.
 */
public class ParallelException extends Exception {
    private int Index ;
    public int getIndex() {
        return Index;
    }
    public void setIndex(int index) {
        Index = index;
    }
    public ParallelException(int itemIndex) { Index = itemIndex; }
    public ParallelException(int itemIndex, String message) {
        super(message);
        Index = itemIndex;
    }
}
