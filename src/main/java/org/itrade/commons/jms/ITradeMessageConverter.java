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

    public final static String MSG_TYPE = "MSG_TYPE";
    public final static String MSG_DIRECTION = "MSG_DIRECTION";
    public final static String MSG_BODY = "MSG_BODY";

    @Override
    public Message toMessage(final Object object, final Session session) throws JMSException, MessageConversionException {
        Preconditions.checkArgument((object instanceof ITradeJmsMessage), "Object is not an instance of ITradeJmsMessage: {}", object);
        ITradeJmsMessage iTradeJmsMessage = (ITradeJmsMessage) object;
        return toMessage(iTradeJmsMessage, session);
    }

    public Message toMessage(final ITradeJmsMessage ecomJmsMessage, final Session session) throws JMSException {
        MapMessage message = session.createMapMessage();

        message.setStringProperty(MSG_TYPE, ecomJmsMessage.getType());
        message.setString(MSG_TYPE, ecomJmsMessage.getType());
        message.setStringProperty(MSG_DIRECTION, ecomJmsMessage.getOrientation());
        message.setString(MSG_DIRECTION, ecomJmsMessage.getOrientation());
        message.setStringProperty(MSG_BODY, ecomJmsMessage.getBody());
        message.setString(MSG_BODY, ecomJmsMessage.getBody());

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
        iTradeJmsMessage.setType(message.getStringProperty(MSG_TYPE));
        iTradeJmsMessage.setOrientation(message.getStringProperty(MSG_DIRECTION));
        iTradeJmsMessage.setBody(message.getStringProperty(MSG_BODY));

        return iTradeJmsMessage;
    }

    protected ITradeJmsMessage newITradeJmsMessage() {
        return new ITradeJmsMessage();
    }
}