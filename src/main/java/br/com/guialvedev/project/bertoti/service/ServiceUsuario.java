package br.com.guialvedev.project.bertoti.service;


import br.com.guialvedev.project.bertoti.exceptions.CriptoExistsException;
import br.com.guialvedev.project.bertoti.exceptions.EmailExistsException;
import br.com.guialvedev.project.bertoti.exceptions.ServiceExc;
import br.com.guialvedev.project.bertoti.model.Usuario;
import br.com.guialvedev.project.bertoti.repository.UsuarioRepository;
import br.com.guialvedev.project.bertoti.util.Util;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class ServiceUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void salvarUsuario(Usuario usuario) throws  Exception{

        try {
            if (usuarioRepository.findByEmail(usuario.getEmail()) != null){
                throw new EmailExistsException("Ja existe email cadastrado para:" + usuario.getEmail());
            }
            usuario.setSenha(Util.md5(usuario.getSenha()));

        }catch (NoSuchAlgorithmException e){
            throw new CriptoExistsException("Erro na criptografia");

        }
        usuarioRepository.save(usuario);

    }


    public Usuario loginUsuario(String username, String senha) throws ServiceExc {
        Usuario userLogin = usuarioRepository.buscarLogin(username, senha);
        return userLogin;
    }




}
