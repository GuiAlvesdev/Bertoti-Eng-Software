package br.com.guialvedev.project.bertoti.controllers;


import br.com.guialvedev.project.bertoti.model.Car;
import br.com.guialvedev.project.bertoti.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/cadastrar-carro")
    public ModelAndView InserirCarro(Car car){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("car/cadastro");
        mv.addObject("car", new Car());
        return mv;

    }

    @PostMapping("InsertCar")
    public ModelAndView inserirCarro(@Valid Car car, BindingResult br){
        ModelAndView mv = new ModelAndView();
        if(br.hasErrors()){
            mv.setViewName("car/cadastro");
            mv.addObject("car");
        }else{
            mv.setViewName("redirect: /car/listCarros");
            carRepository.save(car);

        }
        return mv;

    }

    @GetMapping("carros-adicionados")
    public ModelAndView listagemCarros(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("car/listCar");
        mv.addObject("carList", carRepository.findAll());
        return mv;

    }


    @GetMapping("alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("car/alterar");
        Car car = carRepository.getReferenceById(id);
        mv.addObject("car", car);
        return mv;
    }

    
    @PostMapping("/alterar")
    public ModelAndView alterar(Car car){
        ModelAndView mv = new ModelAndView();
        carRepository.save(car);
        mv.setViewName("redirect: /listCarros");
        return mv;
    }


    @GetMapping("/excluir/{id}")
    public String excluirCarro(@PathVariable("id") Integer id){
        carRepository.deleteById(id);
        return "redirect:/carros-adicionados";
    }

    @PostMapping("pesquisar-carro")
    public ModelAndView pesquisarCarro(@RequestParam(required = false) String placa){
        ModelAndView mv = new ModelAndView();
        List<Car> listCarros;
        if(placa == null || placa.trim().isEmpty()){
            listCarros = carRepository.findAll();
        }else{
            listCarros = carRepository.findByPlaca(placa);
        }
        mv.addObject("ListaDeCarros", listCarros);
        mv.setViewName("car/pesquisa-resultado");
        return mv;
    }





}
