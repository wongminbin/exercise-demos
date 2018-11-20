package com.wong.binven.demo.mq.kafka.stream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import com.wong.binven.demo.core.Log;

/**
 * create by: HuangZhiBin
 * 2018年11月17日 下午6:04:01
 */

@Configuration
@EnableKafkaStreams
public class KafkaStreamConfig implements Log {
	
	@Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
	public KafkaStreamsConfiguration config(KafkaProperties kafkaProperties) {
		Map<String, Object> props = new HashMap<>();
		
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, Optional.ofNullable(kafkaProperties.getStreams().getBootstrapServers())
				.orElse(kafkaProperties.getBootstrapServers()));
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, kafkaProperties.getStreams().getApplicationId());
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class.getName());
        
        // setting offset reset to earliest so that we can re-run the demo code with the same pre-loaded data
        // Note: To re-run the demo, you need to use the offset reset tool:
        // https://cwiki.apache.org/confluence/display/KAFKA/Kafka+Streams+Application+Reset+Tool
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new KafkaStreamsConfiguration(props);
	}
	
	
	@Bean
	public KStream<String, String> kStream(StreamsBuilder kStreamBuilder) {
		
		KStream<String, String> stream = kStreamBuilder.stream("streams-plaintext-input");

        KTable<String, String> counts = stream
            .flatMapValues(value -> Arrays.asList(value.toLowerCase(Locale.getDefault()).split(" ")))
            .groupBy((key, value) -> value)
            .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"))
            .mapValues(value->value.toString());
            
        // need to override value serde to Long type
        counts.toStream().to("streams-wordcount-output");

		return stream;
	}
	
//	  @Bean
//    public KafkaStreams create(KafkaStreamsConfiguration config) {
//
//        StreamsBuilder builder = new StreamsBuilder();
//
//        KStream<String, String> source = builder.stream("streams-plaintext-input");
//
//        KTable<String, Long> counts = source
//            .flatMapValues(value -> Arrays.asList(value.toLowerCase(Locale.getDefault()).split(" ")))
//            .groupBy((key, value) -> value)
//            .count();
//
//        // need to override value serde to Long type
//        counts.toStream().to("streams-wordcount-output", Produced.with(Serdes.String(), Serdes.Long()));
//
//        final KafkaStreams streams = new KafkaStreams(builder.build(), config.asProperties());
//        final CountDownLatch latch = new CountDownLatch(1);
//
//        // attach shutdown handler to catch control-c
//        Runtime.getRuntime().addShutdownHook(new Thread("streams-wordcount-shutdown-hook") {
//            @Override
//            public void run() {
//                streams.close();
//                latch.countDown();
//            }
//        });
//
//        try {
//            streams.start();
//            latch.await();
//        } catch (Throwable e) {
//            System.exit(1);
//        }
//        
//        return streams;
//    }
}
