import java.sql.SQLException;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {
    private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        String subject = "queue";
        Destination destination = session.createQueue(subject);
        MessageConsumer consumer = session.createConsumer(destination);
        Message message = consumer.receive();
        if (message instanceof TextMessage textMessage) {
            System.out.println("Received message : " + textMessage.getText());
            if (textMessage.getText().equals("All records are sent")) {

                System.out.println(output());
            }
        }

        connection.close();
    }
        public static String output() {
            try {
                DATABASE DATABASE = new DATABASE();
                return DATABASE.pass();
            } catch (SQLException e) {
                e.printStackTrace();
                return e.toString();
            }
        }
}
