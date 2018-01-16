package Services.models;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServiceLayer<T> {

    ArrayList<T> getAll();

    void create(T element);
    T read(int id);
    void update(int id, T element, T oldElement);
    void delete(int id);

}
