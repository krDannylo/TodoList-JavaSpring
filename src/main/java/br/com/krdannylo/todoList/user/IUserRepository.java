package br.com.krdannylo.todoList.user;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {

  UserModel findByUsername(String username); // Spring Data JPA: é capaz de entender que esse método é um select
                                             // de username no
                                             // banco por conta do findBy (query method mechanism)
}