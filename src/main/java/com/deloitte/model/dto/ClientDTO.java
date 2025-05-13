package com.deloitte.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientDTO (@NotBlank String nome, @Email String email){
}
