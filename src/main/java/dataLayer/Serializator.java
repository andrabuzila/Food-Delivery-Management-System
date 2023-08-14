package dataLayer;

import businessLayer.DeliveryService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializator {

    public void whenCustomSerializingAndDeserializing_ThenObjectIsTheSame(DeliveryService d)
            throws IOException, ClassNotFoundException {

        FileOutputStream fileOutputStream
                = new FileOutputStream("yourfile.txt");
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(d.getClass().getSimpleName());
        objectOutputStream.flush();
        objectOutputStream.close();
    }

}
