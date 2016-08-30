package Usuario;
import Util.Mensagem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "usuarioController")
@ViewScoped
public class UsuarioController {

    Usuario usuario = new Usuario();
    Integer tela = 0;
    String pesquisa = "";
    UsuarioService usuarioService = new UsuarioService();
    
    
}
