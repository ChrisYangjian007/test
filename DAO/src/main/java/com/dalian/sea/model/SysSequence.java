package com.dalian.sea.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_sequence")
public class SysSequence implements Serializable {
    @Id
    @Column(name = "sequence_id")
    private Long sequenceId;

    @Column(name = "main_id")
    private Long mainId;

    @Column(name = "box_id")
    private Long boxId;

    private static final long serialVersionUID = 1L;

    /**
     * @return sequence_id
     */
    public Long getSequenceId() {
        return sequenceId;
    }

    /**
     * @param sequenceId
     */
    public void setSequenceId(Long sequenceId) {
        this.sequenceId = sequenceId;
    }

    /**
     * @return main_id
     */
    public Long getMainId() {
        return mainId;
    }

    /**
     * @param mainId
     */
    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    /**
     * @return box_id
     */
    public Long getBoxId() {
        return boxId;
    }

    /**
     * @param boxId
     */
    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysSequence other = (SysSequence) that;
        return (this.getSequenceId() == null ? other.getSequenceId() == null : this.getSequenceId().equals(other.getSequenceId()))
            && (this.getMainId() == null ? other.getMainId() == null : this.getMainId().equals(other.getMainId()))
            && (this.getBoxId() == null ? other.getBoxId() == null : this.getBoxId().equals(other.getBoxId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSequenceId() == null) ? 0 : getSequenceId().hashCode());
        result = prime * result + ((getMainId() == null) ? 0 : getMainId().hashCode());
        result = prime * result + ((getBoxId() == null) ? 0 : getBoxId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sequenceId=").append(sequenceId);
        sb.append(", mainId=").append(mainId);
        sb.append(", boxId=").append(boxId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}