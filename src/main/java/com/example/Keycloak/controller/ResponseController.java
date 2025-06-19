package com.example.Keycloak.controller;

import com.example.Keycloak.DTO.ComplaintShortDTO;
import com.example.Keycloak.DTO.ResponseShortDTO;
import com.example.Keycloak.service.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responses")
@RequiredArgsConstructor
public class ResponseController {
    private final ResponseService responseService;

    @Operation(summary = "Create response for complaint", description = "Creating response for this complaint")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Response successfully created",
                    content = @Content(schema = @Schema(implementation = ComplaintShortDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping("/create")
    @PreAuthorize("hasRole('OFFICIAL')")
    public ResponseShortDTO createResponse(@RequestBody ResponseShortDTO responseDTO, @RequestParam String officialUsername) {
        return responseService.createResponse(responseDTO, officialUsername);
    }

    @Operation(summary = "Get responses for complaint", description = "Getting responses for complaint")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Response successfully getting",
                    content = @Content(schema = @Schema(implementation = ComplaintShortDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}/responses")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ResponseShortDTO> getResponsesForComplain(@PathVariable Long id) {
        return responseService.getResponsesForComplaint(id);
    }
}
