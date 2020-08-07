package m3_publishsubscribe;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.64.128");
        f.setPort(5672);
        f.setUsername("admin");
        f.setPassword("admin");

        Channel c = f.newConnection().createChannel();

        c.exchangeDeclare("logs", BuiltinExchangeType.FANOUT);

        while (true) {
            System.out.println();
            String msg = new Scanner(System.in).nextLine();

            c.basicPublish("logs","",null, msg.getBytes());
        }
    }
}
