package io.github.guoshiqiufeng.cloud.stream.binder.redis.support.converter;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;

/**
 * A message transformer for redis, primarily designed to support Header message sending.
 *
 * @author yanghq
 * @version 1.0
 * @since 2024/9/13 11:39
 */
public class MessagingMessageConverter implements MessageConverter {

    private volatile MessageConverter messagingConverter = new SimpleMessageConverter();

    @Override
    public Object fromMessage(Message<?> messageArg, Class<?> targetClass) {
        Message<?> message = messageArg;
        if (this.messagingConverter != null) {
            Message<?> converted = this.messagingConverter.toMessage(message.getPayload(), message.getHeaders());
            if (converted != null) {
                message = converted;
            }
        }

        return RedisSerializer.java().serialize(message);
    }

    @Override
    public Message<?> toMessage(@NotNull Object payload, MessageHeaders headers) {
        if (payload instanceof byte[] bytes) {
            Object deserialize = RedisSerializer.java().deserialize(bytes);
            if (deserialize instanceof Message<?> record) {
                payload = record.getPayload();
                headers = record.getHeaders();
            }
        }
        if (headers != null) {
            MessageHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(headers, MessageHeaderAccessor.class);
            if (accessor != null && accessor.isMutable()) {
                return MessageBuilder.createMessage(payload, accessor.getMessageHeaders());
            }
        }
        return MessageBuilder.withPayload(payload).copyHeaders(headers).build();
    }

}
