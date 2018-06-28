package examples.boot.simplechat.repository;

import examples.boot.simplechat.domain.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository를 붙이지 않아도 Repository로 등록된다.
public interface UserConnectionRepository
        extends JpaRepository<UserConnection, Long> {

}
