package com.mduhan.confluent.connect;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import com.esotericsoftware.kryo.Kryo;
import com.mduhan.confluent.connect.kryo.deserializer.KryoDeserializer;
import com.mduhan.confluent.connect.kryo.serializer.KryoSerializer;

/**
 * Unit test for Kryo
 */
public class KryoTest
{
	private ThreadLocal<Producer<String, Object>> producer;
	
	private Kryo kryo;
	
	private KryoTo object;
	
	
	protected void setUpProducer() {
		Properties props = new Properties();
	    props.put("bootstrap.servers", "localhost:9092");
	    props.put("acks", "0");
	    props.put("key.serializer", StringSerializer.class);
	    props.put("value.serializer", KryoSerializer.class);
	    props.put("max.request.size", "15728640 ");
	    props.put("buffer.memory", "15728640 ");
	    props.put("batch.size", "15728640 ");
	    props.put("max.block.ms", Long.MAX_VALUE);
	    props.put("request.timeout.ms", Integer.MAX_VALUE);

	    producer = new ThreadLocal<Producer<String, Object>>() {
	      @Override
	      protected Producer<String, Object> initialValue() {
	        return new KafkaProducer<>(props);
	      }
	    };

	    kryo = new Kryo();
	    kryo.setRegistrationRequired(false);
	    
	    object = new KryoTo();
    	object.setField1("value1");
    	object.setField2("value2");
	}
	
	protected void setUpConsumer() {
		Properties props = new Properties();
	    props.put("bootstrap.servers", "localhost:9092");
	    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KryoDeserializer.class);
	    props.put("buffer.memory", "15728640 ");
	    props.put("request.timeout.ms", Integer.MAX_VALUE);

	}
	
    /**
     * This test will serialize KryoTo with kryo and send it to kafka
     */
    @Test
    public void kafkaSerializePush()
    {
    	setUpProducer();
    	producer.get().send(new ProducerRecord<>("kryo-topic", "data-id", object));
        producer.get().flush();
        
        assertTrue( true );
    }
    
    /**
     * This test will serialize KryoTo with kryo and send it to kafka
     */
    @Test
    public void kafkaDeserialize()
    {
    	setUpConsumer();
    	
    	// create consumer with above property and get message
	    // type cast message to KryoTo
        
        assertTrue( true );
    }
}
