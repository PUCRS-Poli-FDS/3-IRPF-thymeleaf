package br.pucrs.irpf.controller;

import br.pucrs.irpf.model.Pessoa;
import br.pucrs.irpf.services.CadastroPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PessoaController {

    @Autowired
    CadastroPessoaService cadastroPessoaService;

    @GetMapping("/pessoa")
    public String pessoaPrincipal(Model model) {
        Map<String, Object> mapModel = new HashMap<>();
        mapModel.put("paginaRetorno", "pessoa");
        model.addAllAttributes(mapModel);
        return (String) mapModel.get("paginaRetorno");
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Pessoa pessoa, BindingResult result, Model model) {
        Map<String, Object> mapModel = new HashMap<>();
        cadastroPessoaService.addPessoa(pessoa);
        mapModel.put("paginaRetorno", "pessoa");
        model.addAllAttributes(mapModel);
        return (String) mapModel.get("paginaRetorno");
    }

    @GetMapping("/calculaImposto")
    public String calculaImposto(@ModelAttribute Pessoa pessoa, BindingResult result, Model model) {
        Map<String, Object> mapModel = new HashMap<>();
        mapModel.put("paginaRetorno", "calculaImposto");
        if(!cadastroPessoaService.getPessoaList().isEmpty()) {
            mapModel.put("Pessoa", cadastroPessoaService.calculaImpostoPessoas(cadastroPessoaService.getPessoaList()));
        }
        model.addAllAttributes(mapModel);
        return (String) mapModel.get("paginaRetorno");
    }

}
