package br.com.krdannylo.todoList.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired // Spring fica responsável por gerenciar o ciclo de vida (Instanciar, fazer o
             // que for possível para que eu possa acessar meu repository)
  private IUserRepository userRepository;

  @PostMapping("/")
  public UserModel create(@RequestBody UserModel userModel) {
    var userCreated = this.userRepository.save(userModel);
    return userCreated;
  }
}
