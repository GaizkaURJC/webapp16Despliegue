package com.daw.daw.dto;

public record ReservaDTO(
                Long id,
                String userName,
                String userEmail,
                String bussinesName,
                int num_personas,
                String eventDescript,
                String estado) {
}
