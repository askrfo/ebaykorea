package examples.boot.simplechat.repository;

import examples.boot.simplechat.domain.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUserRepository
        extends JpaRepository<ChatUser, Long> {
}
