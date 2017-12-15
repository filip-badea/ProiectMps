import javax.mail.*;
import java.util.Properties;
public class MailReader

{

    Folder inbox;

    //Constructor of the calss.
    public MailReader()
    {
 /*  Set the mail properties  */
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        try
        {
 /*  Create the session and get the store for read the mail. */
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com","filip.marian.badea@gmail.com ", "thedogthatbarks21");

 /*  Mention the folder name which you want to read. */
            inbox = store.getFolder("Inbox");
            System.out.println("No of Unread Messages : " + inbox.getUnreadMessageCount());
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}