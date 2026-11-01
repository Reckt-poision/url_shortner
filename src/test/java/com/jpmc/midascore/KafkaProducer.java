package com.jpmc.midascore;

import com.jpmc.midascore.foundation.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    private final String topic;
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public KafkaProducer(@Value("${general.kafka-topic}") String topic,
                         KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String transactionLine) {
        // Normalize to avoid NumberFormatException on stray newlines/spaces
        String normalized = transactionLine.replace("\r", "").replace("\n", "");
        String[] parts = normalized.split(",\\s*");

        long from = Long.parseLong(parts[0].trim());
        long to = Long.parseLong(parts[1].trim());
        float amount = Float.parseFloat(parts[2].trim());

        kafkaTemplate.send(topic, new Transaction(from, to, amount));
    }
}
