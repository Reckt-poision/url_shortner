package com.jpmc.midascore;

import com.jpmc.midascore.foundation.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {

    @KafkaListener(topics = "${general.kafka-topic}")
    public void listen(Transaction transaction) {
        // Set a breakpoint here during TaskTwoTests and capture the first 4 amounts.
    }
}
