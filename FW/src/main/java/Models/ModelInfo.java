package Models;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-07-24.
 */
public class ModelInfo implements Serializable, Cloneable {
    protected int ID;
    public int getID() {
        return ID;
    }
    public void setID(int _ID) {
        this.ID = _ID;
    }
    protected int TotalCount;
    public int getTotalCount() {
        return TotalCount;
    }
    public void setTotalCount(int _TotalCount) {
        this.TotalCount = _TotalCount;
    }
    public Object clone() {
        ModelInfo o = null;
        try {
            o = (ModelInfo) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
}
