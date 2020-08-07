package m3_publishsubscribe;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.64.128");
        f.setUsername("admin");
        f.setPassword("admin");
        Connection ch = f.newConnection();
        Channel c = ch.createChannel();

        c.exchangeDeclare("log", BuiltinExchangeType.FANOUT);

       // String queue = UUID.randomUUID().toString();
       // c.queueDeclare(queue, false, true, true, null);
        String queue = c.queueDeclare().getQueue();
        c.queueBind(queue, "logs", "");

        DeliverCallback deliverCallback = new DeliverCallback() {

            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                String msg = new String(message.getBody());
                System.out.println(msg);
            }
        };

        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {

            }
        };

        c.basicConsume(queue, true, deliverCallback, cancelCallback);
    }
}
