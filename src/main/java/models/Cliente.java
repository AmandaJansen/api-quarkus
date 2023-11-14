package models;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data

public class Cliente {
    private String nome;
    private String cpf;
}
