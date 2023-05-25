package br.com.guialvedev.project.bertoti.controllers;


import br.com.guialvedev.project.bertoti.exceptions.ServiceExc;
import br.com.guialvedev.project.bertoti.model.Car;
import br.com.guialvedev.project.bertoti.model.Usuario;
import br.com.guialvedev.project.bertoti.repository.UsuarioRepository;
import br.com.guialvedev.project.bertoti.service.ServiceUsuario;
import br.com.guialvedev.project.bertoti.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@Controller
public class UsuarioController {


        @Autowired
        private UsuarioRepository usuarioRepository;

        @Autowired
        private ServiceUsuario serviceUsuario;


        @GetMapping("/")
        public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/login");
        mv.addObject("usuario", new Usuario());
        return mv;

    }


        @GetMapping("/cadastro")
        public ModelAndView cadastrar(){
                ModelAndView mv = new ModelAndView();
                mv.addObject("usuario", new Usuario());
                mv.setViewName("login/cadastro");
                return mv;

        }

        @PostMapping("salvarUsuario")
        public ModelAndView cadastrar(Usuario usuario) throws Exception {
            ModelAndView mv = new ModelAndView();
            serviceUsuario.salvarUsuario(usuario);
            mv.setViewName("redirect:/");
            return mv;
        }


        @PostMapping("/login")
        public ModelAndView login(@Valid Usuario usuario, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, ServiceExc {
            ModelAndView mv = new ModelAndView();
            mv.addObject("usuario", new Usuario());
            if (br.hasErrors()) {
                mv.setViewName("login/login");
            }

            Usuario userLogin = serviceUsuario.loginUsuario(usuario.getNomedeusuario(), Util.md5(usuario.getSenha()));
            if (userLogin == null) {
                mv.addObject("msg", "usuario nao encontrado");
            } else {
                session.setAttribute("usuarioLogado", userLogin);
                return index();
            }
            return mv;
        }



    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home/index");
        mv.addObject("car", new Car());
        return mv;


    }


    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session){
            session.invalidate();
            return login();
    }





















}
