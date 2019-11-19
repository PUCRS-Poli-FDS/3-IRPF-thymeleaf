package br.pucrs.irpf.controller;

import br.pucrs.irpf.model.Pessoa;
import br.pucrs.irpf.services.CadastroPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CadastroController {

    @Autowired
    CadastroPessoaService cadastroPessoaService;

    @GetMapping("/cadastro")
    public String casdastroPrincipal(@ModelAttribute Pessoa pessoa, BindingResult result, Model model) {
        Map<String, Object> mapModel = new HashMap<>();
        mapModel.put("paginaRetorno", "cadastro");
        if(!cadastroPessoaService.getPessoaList().isEmpty()) {
            mapModel.put("Pessoa", cadastroPessoaService.getPessoaList());
        }
        model.addAllAttributes(mapModel);
        return (String) mapModel.get("paginaRetorno");
    }


}
