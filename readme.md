
### kafka queue example

##### you can find two types of configurations for kafka in the project: 
- application.properties
- java code configurations in the package name "com.erdemnayin.kafka.config"

#### use docker-compose.yaml for creating kafka, zookeeper, kafkaUI with command: 

```console
docker-compose up
```

#### endpoint for resource "CustomMessage"

<pre>
     POST
    - localhost:8080/api/v1/customMessage
</pre>


#### example message body:

<pre>
{
    "message": "test message for kafka messaging"
}
</pre>

#### you can reach the KafkaUI in browser with the endpoint of

<pre>
     localhost:9090
</pre>
