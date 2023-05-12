package com.architechz.project.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.architechz.project.models.Client;
import com.architechz.project.models.Driver;
import com.architechz.project.models.Manager;
import com.architechz.project.models.Mecanic;
import com.architechz.project.models.Operator;
import com.architechz.project.payload.RegisterRequests.ConductorRequest;
import com.architechz.project.payload.RegisterRequests.GerenteRequest;
import com.architechz.project.payload.RegisterRequests.MecanicoRequest;
import com.architechz.project.payload.RegisterRequests.OperadorRequest;
import com.architechz.project.payload.response.MessageResponse;
import com.architechz.project.service.Clientes.ClienteService;
import com.architechz.project.service.Conductores.*;
import com.architechz.project.service.Gerente.*;
import com.architechz.project.service.Mecanicos.*;
import com.architechz.project.service.Operadores.*;
import com.architechz.project.payload.InsertionRequests.Approve;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/adm")
public class AdmController {

  @Autowired
  OperadoresService OperadoresService;

  @Autowired
  MecanicosService MecanicosService;

  @Autowired
  ClienteService clienteService;

  @Autowired
  ConductoresService ConductoresService;

  @Autowired
  GerenteService GerenteService;

  /*
   * @Autowired
   * OperadoresService OperadoresService;
   */
  /////////////////////////////////////////////////////// Crear usuarios
  /////////////////////////////////////////////////////// ///////////////////////////////////////////////////////////////
  @PostMapping("/OperatorCreate")
  public ResponseEntity<?> OperadorSignup(@Valid @RequestBody OperadorRequest operadorRequest) {
    return ResponseEntity.ok(new MessageResponse(OperadoresService.addUser(operadorRequest)));
  }

  @PostMapping("/MecanicCreate")
  public ResponseEntity<?> MecanicoSignup(@Valid @RequestBody MecanicoRequest mecanicoRequest) {
    return ResponseEntity.ok(new MessageResponse(MecanicosService.addUser(mecanicoRequest)));
  }

  @PostMapping("/DriverCreate")
  public ResponseEntity<?> ConductorSignup(@Valid @RequestBody ConductorRequest conductorRequest) {
    return ResponseEntity.ok(new MessageResponse(ConductoresService.addUser(conductorRequest)));
  }

  @PutMapping("/uploadDriverFiles")
  public ResponseEntity<?> uploadDriverFiles(@RequestParam("licPhoto") MultipartFile license,
      @RequestParam("mecPhoto") MultipartFile mecReview, @RequestParam("id") Long id) {
    try {
      return ResponseEntity
          .ok(new MessageResponse(ConductoresService.updateDriverFiles(license.getBytes(), mecReview.getBytes(), id)));
    } catch (Exception e) {
      return new ResponseEntity<>(new MessageResponse(e.toString()), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/ManagerCreate")
  public ResponseEntity<?> GerenteSignup(@Valid @RequestBody GerenteRequest gerenteRequest) {
    return ResponseEntity.ok(new MessageResponse(GerenteService.addUser(gerenteRequest)));
  }

  /////////////////////////////////////////////////// Obtener lista de empleados
  /////////////////////////////////////////////////// /////////////////////////////////////////////////////
  @GetMapping("/GetOperators")
  public ResponseEntity<?> GetOperador() {
    return new ResponseEntity<>(OperadoresService.GetUser(), HttpStatus.OK);
  }

  @GetMapping("/GetMecanics")
  public ResponseEntity<?> GetMecanico() {
    return new ResponseEntity<>(MecanicosService.GetUser(), HttpStatus.OK);
  }

  @GetMapping("/GetDrivers")
  public ResponseEntity<?> GetConductor() {
    return new ResponseEntity<>(ConductoresService.GetUser(), HttpStatus.OK);
  }

  @GetMapping("/GetManager")
  public ResponseEntity<?> GetGerente() {
    return new ResponseEntity<>(GerenteService.GetUser(), HttpStatus.OK);
  }

  ////////////////////////////////////////////////// Delete Usuarios
  ////////////////////////////////////////////////// /////////////////////////////////////////////////////////////////
  @DeleteMapping("/DeleteOperator/{username}")
  public ResponseEntity<?> DelOperador(@PathVariable String username) {
    return ResponseEntity.ok(new MessageResponse(OperadoresService.delUser(username)));
  }

  @DeleteMapping("/DeleteMecanic/{username}")
  public ResponseEntity<?> DelMecanico(@PathVariable String username) {
    return ResponseEntity.ok(new MessageResponse(MecanicosService.delUser(username)));
  }

  @DeleteMapping("/DeleteDriver/{username}")
  public ResponseEntity<?> DelConductor(@PathVariable String username) {
    return ResponseEntity.ok(new MessageResponse(ConductoresService.delUser(username)));
  }

  @DeleteMapping("/DeleteManager/{username}")
  public ResponseEntity<?> DelGerente(@PathVariable String username) {
    return ResponseEntity.ok(new MessageResponse(GerenteService.delUser(username)));
  }

  ///////////////////////////////////////////////// Edit Usarios
  ///////////////////////////////////////////////// ////////////////////////////////////////////////////////////////////
  @PutMapping("/UpdateOperator")
  public ResponseEntity<?> UpdtOperador(@Valid @RequestBody Operator operadorRequest) {
    return ResponseEntity.ok(new MessageResponse(OperadoresService.UpdateUser(operadorRequest)));
  }

  @PutMapping("/UpdateMecanic")
  public ResponseEntity<?> UpdtMecanico(@Valid @RequestBody Mecanic operadorRequest) {
    return ResponseEntity.ok(new MessageResponse(MecanicosService.UpdateUser(operadorRequest)));
  }

  @PutMapping("/UpdateManager")
  public ResponseEntity<?> UpdtConductor(@Valid @RequestBody Manager operadorRequest) {
    return ResponseEntity.ok(new MessageResponse(GerenteService.UpdateUser(operadorRequest)));
  }

  @PutMapping("/UpdateDriver")
  public ResponseEntity<?> UpdtGerente(@Valid @RequestBody Driver operadorRequest) {
    return ResponseEntity.ok(new MessageResponse(ConductoresService.UpdateUser(operadorRequest)));
  }
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @PostMapping("/AcceptUser")
  public ResponseEntity<?> VerifyClient(@Valid @RequestBody Approve clientRequest) {
    return clienteService.aproveClient(clientRequest);
  }

  @GetMapping("/ClientstoAccept")
  public ResponseEntity<?> Clients() {
    List<Client> clients = clienteService.GetClientsApprove();
    if (!clients.isEmpty()) {
      return ResponseEntity.ok(clients);
    } else {
      return ResponseEntity.status(HttpStatus.OK).body("No hay nuevas peticiones...");
    }
  }

}
