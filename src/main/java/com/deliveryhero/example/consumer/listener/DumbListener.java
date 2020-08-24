package com.deliveryhero.example.consumer.listener;

import com.deliveryhero.example.consumer.db.writer.PostgresWriter;
import com.google.protobuf.InvalidProtocolBufferException;
import example.simple.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DumbListener {
    @Autowired
    private PostgresWriter writer;

    @KafkaListener(topics = "${KAFKA_TOPIC}", groupId = "${KAFKA_CONSUMER_GROUP}")
    public void list(byte[] message) {
        log.info("message received : {}", message);
        Login.LoginMessage parsedProto = null;
        try {
            parsedProto = Login.LoginMessage.parseFrom(message);
        } catch (InvalidProtocolBufferException e) {
            log.error("Protocol Buffer parsing issue occured: {}", e.getMessage());
        }
        log.info("message got parsed as following Protobuf : {}", parsedProto);

        if(parsedProto!= null) {
            writer.writeData(parsedProto);
        }
    }
}
