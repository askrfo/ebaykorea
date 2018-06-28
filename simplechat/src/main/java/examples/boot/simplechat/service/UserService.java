package examples.boot.simplechat.service;

import examples.boot.simplechat.domain.User;
import examples.boot.simplechat.domain.UserConnection;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User addUser(User user);
    User getUserByEmail(String email);

    public User getSocialUser(String type, String providerUserId);
    public UserConnection addUserConnection(UserConnection userConnection);
}
