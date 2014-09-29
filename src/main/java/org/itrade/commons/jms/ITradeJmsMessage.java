package org.itrade.commons.jms;

public class ITradeJmsMessage {

    private String id;
    private String category;
    private ITradeMessageType type;
    private String content;
    private ITradeMessageStatus status;

    private String jmsMessageId;
    private String jmsCorrelationId;

    public ITradeJmsMessage() {
    }

    public String getId() {
        return id;
    }

    public ITradeJmsMessage setId(String id) {
        this.id = id;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ITradeJmsMessage setCategory(String category) {
        this.category = category;
        return this;
    }

    public ITradeMessageType getType() {
        return type;
    }

    public ITradeJmsMessage setType(ITradeMessageType type) {
        this.type = type;
        return this;
    }

    public ITradeMessageStatus getStatus() {
        return status;
    }

    public ITradeJmsMessage setStatus(ITradeMessageStatus status) {
        this.status = status;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ITradeJmsMessage setContent(String content) {
        this.content = content;
        return this;
    }

    public String getJmsMessageId() {
        return jmsMessageId;
    }

    public ITradeJmsMessage setJmsMessageId(final String jmsMessageId) {
        this.jmsMessageId = jmsMessageId;
        return this;
    }

    public String getJmsCorrelationId() {
        return jmsCorrelationId;
    }

    public ITradeJmsMessage setJmsCorrelationId(String jmsCorrelationId) {
        this.jmsCorrelationId = jmsCorrelationId;
        return this;
    }

    @Override
    public String toString() {
        return "ITradeJmsMessage{" +
                "type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", content='" + content + '\'' +
                ", jmsMessageId='" + jmsMessageId + '\'' +
                ", jmsCorrelationId='" + jmsCorrelationId + '\'' +
                '}';
    }
}
