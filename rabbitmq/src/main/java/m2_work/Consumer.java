package m2_work;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.64.128");
        f.setUsername("admin");
        f.setPassword("admin");

        Channel c = f.newConnection().createChannel();
        c.queueDeclare("task_queue",true,false,false,null);

        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                String msg = new String(message.getBody());
                System.out.println("收到" + message);
                for (int i = 0; i < msg.length(); i++) {
                    if(msg.charAt(i) == '.') {
                        try {
                            Thread.sleep(1000);
                        }catch (InterruptedException e) {

                        }
                    }
                }
                c.basicAck(message.getEnvelope().getDeliveryTag(), false);
                System.out.println("消息处理完成\n");
            }
        };

        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {

            }
        };



        c.basicConsume("task_queue", false, deliverCallback, cancelCallback);
    }
}
