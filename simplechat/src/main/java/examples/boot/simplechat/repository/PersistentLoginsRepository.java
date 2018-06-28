package examples.boot.simplechat.repository;

import examples.boot.simplechat.domain.PersistentLogins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

// @Repository를 붙이지 않아도 Repository로 등록된다.
public interface PersistentLoginsRepository
        extends JpaRepository<PersistentLogins, String> {

    @Modifying
    @Query("delete from PersistentLogins pl where pl.username = ?1")
    public int deleteByUsername(String username);

}
