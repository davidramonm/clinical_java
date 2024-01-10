package modelo;

import DAL.PacienteDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static modelo.Paciente_.dataNascimento;

public class Controle {

    public String mensagem;

    public void cadastrarPaciente(List<String> listadadosPaciente) {
        this.mensagem = "";
        Validacao validacao = new Validacao();
        validacao.validarPaciente(listadadosPaciente);
        if (validacao.mensagem.equals("")) {
            Paciente paciente = new Paciente();
            Contato contato = new Contato();
            List<Contato> listaContato = new ArrayList<>();
            Endereco endereco = new Endereco();
            List<Endereco> listaEndereco = new ArrayList<>();

            paciente.setNome(listadadosPaciente.get(1));
            paciente.setDataNascimento((Date) dataNascimento);
            paciente.setSexo(listadadosPaciente.get(3));
            paciente.setCpf(listadadosPaciente.get(4));
            paciente.setRg(listadadosPaciente.get(5));
            paciente.setEstadoCivil(listadadosPaciente.get(6));

            contato.setTelefoneFixo(listadadosPaciente.get(7));
            contato.setTelefoneCelular(listadadosPaciente.get(8));
            contato.setEmail(listadadosPaciente.get(9));

            endereco.setCep(listadadosPaciente.get(10));
            endereco.setCidade(listadadosPaciente.get(11));
            endereco.setEstado(listadadosPaciente.get(12));
            endereco.setLogradouro(listadadosPaciente.get(13));
            endereco.setNumero(listadadosPaciente.get(14));
            endereco.setBairro(listadadosPaciente.get(15));
            endereco.setComplemento(listadadosPaciente.get(16));

            listaContato.add(contato);
            listaEndereco.add(endereco);

            paciente.setContatoList(listaContato);
            paciente.setEnderecoList(listaEndereco);

            PacienteDAO pacienteDAO = new PacienteDAO();
            pacienteDAO.cadastrarPaciente(paciente);
            this.mensagem = pacienteDAO.mensagem;
        } else {
            this.mensagem = validacao.mensagem;
        }
    }

}
