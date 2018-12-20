package com.mduhan.confluent.connect.kryo.serializer;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

/**
 * Implementation will convert object to kryo bytes
 * @author mduhan
 *
 */
public class KryoSerializer implements Serializer<Object> {

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {

  }

  @Override
  public byte[] serialize(String topic, Object data) {
    Kryo kryo = new Kryo();
    kryo.setRegistrationRequired(false);
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      Output output = new Output(byteArrayOutputStream);
      kryo.writeClassAndObject(output, data);
      output.flush();
      return byteArrayOutputStream.toByteArray();
    } catch (Exception e) {
      return new byte[0];
    }
  }

  @Override
  public void close() {

  }

}
