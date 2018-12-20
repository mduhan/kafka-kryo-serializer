kafka-kryo-serializer
---------

kafka-kryo-serializer offers sending object over kafka. It is very nuch space and time efficient
compression rate for kryo is between 70 to 80%.
This is very useful for sending huge payload >200 Mb via kafka.



## Cloning the kafka-kryo-serializer

First obtain the code by cloning the Git repository:

- $ git clone https://github.com/mduhan/kafka-kryo-serializer.git
- $ cd kafka-kryo-serializer


## Building kafka-kryo-serializer

- cd kafka-kryo-serializer
- mvn clean install

## Serialize message on kafka producer
   set serializer as kryo serializer
  - props.put("value.serializer", KryoSerializer.class);
  
## Deserialize message on consumer
   set serializer as kryo deserializer
 - props.put("value.serializer", KryoDeserializer.class);
 
## kafka sink connect
   
   set converter as kryo
 - com.mduhan.confluent.connect.kryo.converter.KryoConvertor   
 

