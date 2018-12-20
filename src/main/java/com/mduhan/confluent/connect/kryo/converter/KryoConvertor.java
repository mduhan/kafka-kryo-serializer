package com.mduhan.confluent.connect.kryo.converter;

import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaAndValue;
import org.apache.kafka.connect.errors.DataException;
import org.apache.kafka.connect.storage.Converter;

import com.mduhan.confluent.connect.kryo.deserializer.KryoDeserializer;
import com.mduhan.confluent.connect.kryo.serializer.KryoSerializer;

/**
 * Implementation that uses kryo to store data in kafka
 * @author mduhan
 *
 */
public class KryoConvertor implements Converter {

  private final KryoSerializer serializer = new KryoSerializer();
  private final KryoDeserializer deserializer = new KryoDeserializer();
  
  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    // TODO Auto-generated method stub

  }

  @Override
  public byte[] fromConnectData(String topic, Schema schema, Object value) {
    try {
      return serializer.serialize(topic, value);
    } catch (SerializationException e) {
      throw new DataException("Failed to serialize to a string: ", e);
    }
  }

  @Override
  public SchemaAndValue toConnectData(String topic, byte[] value) {
    try {
      return new SchemaAndValue(Schema.OPTIONAL_STRING_SCHEMA, deserializer.deserialize(topic, value));
    } catch (SerializationException e) {
      throw new DataException("Failed to deserialize kryo data : ", e);
    }
  }  

}
