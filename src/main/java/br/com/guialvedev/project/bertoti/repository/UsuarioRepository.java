package br.com.guialvedev.project.bertoti.repository;

import br.com.guialvedev.project.bertoti.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {



    @Query("select i from Usuario i where i.email = :email")
    public Usuario findByEmail(String email);


    @Query("select u from Usuario u where u.nomedeusuario = :username AND u.senha = :senha ")
    public Usuario buscarLogin(String username, String senha);


}
