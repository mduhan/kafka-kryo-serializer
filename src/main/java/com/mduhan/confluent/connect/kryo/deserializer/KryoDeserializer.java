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

package com.mduhan.confluent.connect.kryo.deserializer;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

public class KryoDeserializer implements Deserializer<Object> {

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {

  }

  @Override
  public Object deserialize(String topic, byte[] data) {
    Kryo kryo = new Kryo();
    try (Input input = new Input(new ByteArrayInputStream(data))) {
      return kryo.readClassAndObject(input);
    }
  }

  @Override
  public void close() {

  }

}
