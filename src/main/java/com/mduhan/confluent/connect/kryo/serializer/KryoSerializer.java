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

package com.mduhan.confluent.connect.kryo.serializer;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

public class KryoSerializer implements Serializer<Object> {

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {

  }

  @Override
  public byte[] serialize(String topic, Object data) {
    Kryo kryo = new Kryo();
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
