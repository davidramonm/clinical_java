package modelo;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Validacao {

    public String mensagem;
    public Integer id;
    public Date date;

    public void validarId(String NumId) {
        this.mensagem = "";
        try {
            this.id = Integer.valueOf(NumId);
        } catch (NumberFormatException e) {
            this.mensagem = "Digite um ID válido!";
        }
    }

    public void validarDataNascimento(String Date) {
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern(Date);

    }

    public void validarPaciente(List<String> listadadosPaciente) {
        this.mensagem = "";
        validarId(listadadosPaciente.get(0));
        if (listadadosPaciente.get(1).length() < 3 && listadadosPaciente.get(1).length() > 45) {
            this.mensagem = "Nome deve ter entre 3 a 45 caracteres\n";
        }

        if (listadadosPaciente.get(6).length() > 15) {
            this.mensagem = "Estado Cívil deve ter menos que 15 caracteres\n";
        }
    }
}
