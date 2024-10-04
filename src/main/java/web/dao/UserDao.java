package web.dao;

import web.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getAll();

    Optional<User> get(int id);

    void create(User user);

    void update(User user);

    void delete(int id);
}
