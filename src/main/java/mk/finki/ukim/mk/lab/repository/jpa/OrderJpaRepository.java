package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);

    List<Order> findAllByBalloonColorLike(String text);
    List<Order> findAllByBalloonColorOrBalloonSizeLike(String text, String text1);
    List<Order> findAllByOrderId(Long id);

    @Query("select o from Order o where o.user.id = :userId")
    List<Order> findAllByUserId(@Param("userId") Long userId);

    @Query("select o from Order o where o.user.name = :userName")
    List<Order> findAllByUserName(@Param("userName") String username);


}
