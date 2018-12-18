/**
 * Copyright 2016 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 **/

package com.mduhan.confluent.connect.kryo.converter;

import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaAndValue;
import org.apache.kafka.connect.errors.DataException;
import org.apache.kafka.connect.storage.Converter;

import com.mduhan.confluent.connect.kryo.deserializer.KryoDeserializer;
import com.mduhan.confluent.connect.kryo.serializer.KryoSerializer;


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
