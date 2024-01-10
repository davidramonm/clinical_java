package DAL;

import java.sql.*;
import modelo.Contato;
import modelo.Endereco;
import modelo.Paciente;

public class PacienteDAO {

    public String mensagem;

    public void cadastrarPaciente(Paciente paciente) {
        this.mensagem = "";
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        if (!conexao.mensagem.equals("")) {
            this.mensagem = "";
            return;
        }
        try {
            String comSql = "insert into pacientes "
                    + "(nome, datanascimento, sexo, cpf, rg, estadocivil) "
                    + "values (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(comSql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, paciente.getNome());
            stmt.setDate(2, (Date) paciente.getDataNascimento());
            stmt.setString(3, paciente.getSexo());
            stmt.setString(4, paciente.getCpf());
            stmt.setString(5, paciente.getRg());
            stmt.setString(6, paciente.getEstadoCivil());
            stmt.execute();
            ResultSet resultset = stmt.getGeneratedKeys();
            if (resultset.next()) {
                int id = resultset.getInt(1);
                if (!paciente.getContatoList().isEmpty()) {
                    for (Contato c : paciente.getContatoList()) {
                        comSql = "insert into contatos "
                                + "(telefonefixo, telefonecelular, email, fk_idPaciente2) "
                                + "values (?, ?, ?, ?)";
                        stmt = con.prepareStatement(comSql);
                        stmt.setString(1, c.getTelefoneFixo());
                        stmt.setString(2, c.getTelefoneCelular());
                        stmt.setString(3, c.getEmail());
                        stmt.setInt(4, id);
                        stmt.execute();
                    }
                }
                if (resultset.next()) {
                    int id2 = resultset.getInt(1);
                    if (!paciente.getEnderecoList().isEmpty()) {
                        for (Endereco e : paciente.getEnderecoList()) {
                            comSql = "insert into enderecos "
                                    + "(cep, cidade, estado, logradouro, numero, bairro, complemento, fk_idPaciente) "
                                    + "values (?, ?, ?, ?, ?, ?, ?, ?)";
                            stmt = con.prepareStatement(comSql);
                            stmt.setString(1, e.getCep());
                            stmt.setString(2, e.getCidade());
                            stmt.setString(3, e.getEstado());
                            stmt.setString(4, e.getLogradouro());
                            stmt.setString(5, e.getNumero());
                            stmt.setString(6, e.getBairro());
                            stmt.setString(7, e.getComplemento());
                            stmt.setInt(8, id2);
                            stmt.execute();
                        }
                    }
                }

                this.mensagem = "Cadastro efetuado com sucesso!";
            }

        } catch (SQLException e) {
            this.mensagem = "Erro de conexao BD";
        } finally {
            conexao.desconectar();
        }
    }
}
