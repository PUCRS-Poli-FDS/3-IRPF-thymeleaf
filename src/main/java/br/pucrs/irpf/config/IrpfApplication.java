package br.pucrs.irpf.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={"br.pucrs.irpf"})
public class IrpfApplication {

    public static void main(String[] args) {
        SpringApplication.run(IrpfApplication.class, args);
    }

}
