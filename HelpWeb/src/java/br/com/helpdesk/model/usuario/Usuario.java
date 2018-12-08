package br.com.helpdesk.model.usuario;

/**
 * Usuario [MODEL][USUARIO] Classe responsável por realizar o constrole,
 * gerenciamento e regra de negócios dos dados de Usuario.
 *
 * @author Ricardo Guntzell
 */
public class Usuario {

    private int id;
    private Nivel nivel;
    private String usuarioDescricao;
    private String usuarioSenha;
    private String usuarioAtivo;

    /*Atributo auxiliar*/
    private String resposta;

    public Usuario(int id, Nivel nivel, String usuarioDescricao, String usuarioSenha, String usuarioAtivo) {
        this.id = id;
        this.nivel = nivel;
        this.usuarioDescricao = usuarioDescricao;
        this.usuarioSenha = usuarioSenha;
        this.usuarioAtivo = usuarioAtivo;
    }

    public Usuario(int id) {
        this.id = id;
    }

    public Usuario(Nivel nivel, String usuarioDescricao, String usuarioSenha) {
        this.nivel = nivel;
        this.usuarioDescricao = usuarioDescricao;
        this.usuarioSenha = usuarioSenha;
    }
    

    public Usuario(int id, String usuarioDescricao) {
        this.id = id;
        this.usuarioDescricao = usuarioDescricao;
    }

    public Usuario(String usuarioDescricao, String usuarioSenha) {
        this.usuarioDescricao = usuarioDescricao;
        this.usuarioSenha = usuarioSenha;
    }

    public Usuario() {
    }

    public Usuario(int id, String usuarioDescricao, String usuarioSenha) {
        this.id = id;
        this.usuarioDescricao = usuarioDescricao;
        this.usuarioSenha = usuarioSenha;
    }

    public Usuario(int id, Nivel nivel, String usuarioDescricao, String usuarioSenha) {
        this.id = id;
        this.nivel = nivel;
        this.usuarioDescricao = usuarioDescricao;
        this.usuarioSenha = usuarioSenha;
    }

    public Usuario(int id, Nivel nivel, String usuarioDescricao) {
        this.id = id;
        this.nivel = nivel;
        this.usuarioDescricao = usuarioDescricao;
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public String getUsuarioDescricao() {
        return usuarioDescricao;
    }

    public void setUsuarioDescricao(String usuarioDescricao) {
        this.usuarioDescricao = usuarioDescricao;
    }

    public String getUsuarioSenha() {
        return usuarioSenha;
    }

    public void setUsuarioSenha(String usuarioSenha) {
        this.usuarioSenha = usuarioSenha;
    }

    public String getUsuarioAtivo() {
        return usuarioAtivo;
    }

    public void setUsuarioAtivo(String usuarioAtivo) {
        this.usuarioAtivo = usuarioAtivo;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public boolean validaUsuario() {
        boolean status = false;

        //Bloco descrição do usuário.
        if (this.getUsuarioDescricao().equals("")) {
            this.setResposta("Informe o usuário!");
        } else if (this.getUsuarioDescricao().length() < 5) {
            this.setResposta("A descrição do usuário deve conter ao menos 5 caracteres!");
        } //Bloco descrição da senha.
        else if (this.getUsuarioSenha().equals("")) {
            this.setResposta("Informe a senha!");
        } else if (this.getUsuarioSenha().length() < 5) {
            this.setResposta("A senha do usuário deve conter ao menos 5 caracteres!");
        } else if (this.getNivel().getForca() == 0) {
            this.setResposta("Informe o nível do usuário!");
        } else {
            status = true;
        }

        return status;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nivel=" + nivel + ", usuarioDescricao=" + usuarioDescricao + ", usuarioSenha=" + usuarioSenha + ", usuarioAtivo=" + usuarioAtivo + ", resposta=" + resposta + '}';
    }
}
