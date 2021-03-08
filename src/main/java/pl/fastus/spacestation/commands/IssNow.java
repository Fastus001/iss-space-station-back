
package pl.fastus.spacestation.commands;

import lombok.Data;

public class IssNow {

    private IssPosition issPosition;
    private Integer timestamp;
    private String message;

    public IssPosition getIssPosition() {
        return issPosition;
    }

    public void setIssPosition(IssPosition issPosition) {
        this.issPosition = issPosition;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "IssNow{" +
                "issPosition=" + issPosition +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }
}
