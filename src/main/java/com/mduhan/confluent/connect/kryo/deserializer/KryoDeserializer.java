package com.mduhan.confluent.connect.kryo.deserializer;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

/**
 * Implementation will deserialize kry bytes to actual object 
 * @author mduhan
 *
 */
public class KryoDeserializer implements Deserializer<Object> {

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {

  }

  @Override
  public Object deserialize(String topic, byte[] data) {
	if(data==null || data.length==0){
		return null;
	}
    Kryo kryo = new Kryo();
    kryo.setRegistrationRequired(false);
    try (Input input = new Input(new ByteArrayInputStream(data))) {
      return kryo.readClassAndObject(input);
    }
  }

  @Override
  public void close() {

  }

}
