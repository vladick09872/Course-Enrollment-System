package com.example.Keycloak.controller;

import com.example.Keycloak.DTO.ComplaintShortDTO;
import com.example.Keycloak.model.Status;
import com.example.Keycloak.service.ComplaintService;
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
@RequiredArgsConstructor
@RequestMapping("/complaints")
public class ComplaintController {
    private final ComplaintService complaintService;

    @Operation(summary = "Create new complaint", description = "Add new complaint to system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Complaint created successfully",
                    content = @Content(schema = @Schema(implementation = ComplaintShortDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ComplaintShortDTO createComplaint(@RequestBody ComplaintShortDTO complaintDTO, @RequestParam String username) {
        return complaintService.createComplaint(complaintDTO, username);
    }

    @Operation(summary = "Get complaint for user", description = "Get users complaint")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Complaint successfully getting",
                    content = @Content(schema = @Schema(implementation = ComplaintShortDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/for/user")
    @PreAuthorize("hasRole('USER')")
    public List<ComplaintShortDTO> getComplaintForUser(@RequestParam String username) {
        return complaintService.getComplaintForUser(username);
    }

    @Operation(summary = "Get all complaint", description = " Get all complaints in system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All complaint successfully getting",
                    content = @Content(schema = @Schema(implementation = ComplaintShortDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'OFFICIAL')")
    public List<ComplaintShortDTO> getAllComplaints() {
        return complaintService.findAllComplaints();
    }

    @Operation(summary = "Assign complaint for official user", description = "Assign complaint for official user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Complaint successfully assigned",
                    content = @Content(schema = @Schema(implementation = ComplaintShortDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}/assign")
    @PreAuthorize("hasRole('OFFICIAL')")
    public ComplaintShortDTO assignComplaint(@PathVariable Long id, @RequestParam String username) {
        return complaintService.assignComplaint(id, username);
    }

    @Operation(summary = "Update status", description = "Update status for complaints")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Complaints status successfully updated",
                    content = @Content(schema = @Schema(implementation = ComplaintShortDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{complaintId}/status")
    @PreAuthorize("hasRole('OFFICIAL')")
    public ComplaintShortDTO updateComplaint(@PathVariable Long complaintId, @RequestBody Status status, @RequestParam String officialUsername) {
        return complaintService.updateComplaint(complaintId, status, officialUsername);
    }

    @Operation(summary = "Get complaint by ID", description = "Get complaint by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Complaint successfully getting",
                    content = @Content(schema = @Schema(implementation = ComplaintShortDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ComplaintShortDTO getComplaintById(@PathVariable Long id) {
        return complaintService.getComplaintById(id);
    }

    @Operation(summary = "Delete complaint", description = "Delete complaints")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Complaint successfully deleting",
                    content = @Content(schema = @Schema(implementation = ComplaintShortDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
    }
}

