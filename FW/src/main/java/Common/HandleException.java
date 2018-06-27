package Common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2017-09-22.
 */
public class HandleException extends Exception {
    List<ParallelException> inners = new ArrayList<ParallelException>();

    public HandleException() {
        super();
    }

    public HandleException(String message) {
        super(message);
    }

    public Collection<ParallelException> Exceptions() {
        synchronized (inners) {
            return (Collection<ParallelException>) inners.listIterator();
        }

    }

    public void AddException(int itemIndex, String message) {
        synchronized (inners) {
            inners.add(new ParallelException(itemIndex, message));
        }
    }

    public void AddException(int itemIndex, Exception ex)
    {
        AddException(itemIndex, ex.toString());
    }

    public int Count() {
        synchronized(inners)
        {
            return inners.size();
        }
    }

    @Override
    public String getMessage()
    {
        StringBuilder sb = new StringBuilder(super.getMessage());
        Exceptions().stream().forEach(e->sb.append(e.getMessage()).append('\n'));
        return sb.toString();
    }
}
