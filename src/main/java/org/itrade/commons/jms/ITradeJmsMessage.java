package org.itrade.commons.jms;

public class ITradeJmsMessage {

    private String type;
    private String orientation;
    private String body;
    private String jmsMessageId;
    private String jmsCorrelationId;

    public ITradeJmsMessage() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getJmsMessageId() {
        return jmsMessageId;
    }

    public void setJmsMessageId(final String jmsMessageId) {
        this.jmsMessageId = jmsMessageId;
    }

    public String getJmsCorrelationId() {
        return jmsCorrelationId;
    }

    public void setJmsCorrelationId(String jmsCorrelationId) {
        this.jmsCorrelationId = jmsCorrelationId;
    }

    @Override
    public String toString() {
        return "ITradeJmsMessage{" +
                "type='" + type + '\'' +
                ", orientation='" + orientation + '\'' +
                ", body='" + body + '\'' +
                ", jmsMessageId='" + jmsMessageId + '\'' +
                ", jmsCorrelationId='" + jmsCorrelationId + '\'' +
                '}';
    }
}
