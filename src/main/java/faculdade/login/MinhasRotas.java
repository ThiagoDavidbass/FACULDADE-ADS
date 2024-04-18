package faculdade.login;
import faculdade.login.repository.ClienteDAO;
import jakarta.transaction.Transactional;
import model.entidades.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Controller
public class MinhasRotas implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/cadastrocliente").setViewName("cadastrocliente");
    }
    //mapeando para que a requisição cai neste metodo quando for acessar pelo browser com Verbos Rest
    // Testar com - http://localhost:8080/casa
    @RequestMapping(value="/casa", method = RequestMethod.GET)
    public String carregarHello(ModelMap model){
        model.addAttribute("nomeCasa", "Morando em prédio.");
        return "hello";
    }

    // https://vitormoschetti.medium.com/primeiro-crud-com-spring-boot-5b7abd118ded
    @Autowired
    private ClienteDAO clienteRepository;
//    @ResponseBody
    @RequestMapping(value="/cadastro", method = RequestMethod.GET)
    public String  salvarClienteController(@ModelAttribute Cliente cli,Model padrao) {
        padrao.addAttribute("cliente", new Cliente("Digitar algo aqui"));
        System.out.println("Acionado método salvarClienteController().");
        System.out.println("Nome lido no HTML: " );
        System.out.println("Nome digitado no html:" + cli.getNome());
        clienteRepository.salvarCliente(cli);
        return "cadastro";
    }
}
