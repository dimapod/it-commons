package org.itrade.commons.jms;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

public class ITradeMessageConverter implements MessageConverter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public final static String MSG_ID = "MSG_ID";
    public final static String MSG_CATEGORY = "MSG_CATEGORY";
    public final static String MSG_TYPE = "MSG_TYPE";
    public final static String MSG_STATUS = "MSG_STATUS";
    public final static String MSG_CONTENT = "MSG_CONTENT";

    @Override
    public Message toMessage(final Object object, final Session session) throws JMSException, MessageConversionException {
        Preconditions.checkArgument((object instanceof ITradeJmsMessage), "Object is not an instance of ITradeJmsMessage: {}", object);
        ITradeJmsMessage iTradeJmsMessage = (ITradeJmsMessage) object;
        return toMessage(iTradeJmsMessage, session);
    }

    public Message toMessage(final ITradeJmsMessage itradeJmsMessage, final Session session) throws JMSException {
        MapMessage message = session.createMapMessage();

        message.setStringProperty(MSG_ID, itradeJmsMessage.getId());
        message.setString(MSG_ID, itradeJmsMessage.getId());
        message.setStringProperty(MSG_CATEGORY, itradeJmsMessage.getCategory());
        message.setString(MSG_CATEGORY, itradeJmsMessage.getCategory());
        if (itradeJmsMessage.getType() != null) {
            message.setStringProperty(MSG_TYPE, itradeJmsMessage.getType().name());
            message.setString(MSG_TYPE, itradeJmsMessage.getType().name());
        }
        if (itradeJmsMessage.getStatus() != null) {
            message.setStringProperty(MSG_STATUS, itradeJmsMessage.getStatus().name());
            message.setString(MSG_STATUS, itradeJmsMessage.getStatus().name());
        }
        message.setStringProperty(MSG_CONTENT, itradeJmsMessage.getContent());
        message.setString(MSG_CONTENT, itradeJmsMessage.getContent());

        return message;
    }

    @Override
    public ITradeJmsMessage fromMessage(final Message message) throws JMSException, MessageConversionException {
        return fromMessage(message, new ITradeJmsMessage());
    }

    public <T extends ITradeJmsMessage>T fromMessage(final Message message, T iTradeJmsMessage) throws JMSException, MessageConversionException {
        // Correlation
        iTradeJmsMessage.setJmsMessageId(message.getJMSMessageID());
        iTradeJmsMessage.setJmsCorrelationId(iTradeJmsMessage.getJmsCorrelationId());

        // Mapping
        iTradeJmsMessage.setId(message.getStringProperty(MSG_ID));
        iTradeJmsMessage.setCategory(message.getStringProperty(MSG_CATEGORY));
        iTradeJmsMessage.setType(ITradeMessageType.valueOf(message.getStringProperty(MSG_TYPE)));
        iTradeJmsMessage.setStatus(ITradeMessageStatus.valueOf(message.getStringProperty(MSG_STATUS)));
        iTradeJmsMessage.setContent(message.getStringProperty(MSG_CONTENT));

        return iTradeJmsMessage;
    }
}