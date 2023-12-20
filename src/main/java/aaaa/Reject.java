package aaaa;

import java.util.Objects;

import aaaa.Reject;

public class Reject {
    private String reason;
    private String comment;

    public Reject() {}

    public Reject(String reason, String comment) {
        this.reason = reason;
        this.comment = comment;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Reject [reason=" + reason + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(reason);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reject other = (Reject) obj;
        return Objects.equals(reason, other.reason);
    }
}
