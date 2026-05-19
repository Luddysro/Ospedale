/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ospedale.Controller;

import Data.HospitalizationRepository;
import Data.Storage;
import Data.UserRepository;
import Ospedale.Controller.Utils.Response;
import Ospedale.Controller.Utils.Status;
import Ospedale.DTO.CreateHospitalizationDTO;
import Ospedale.Model.Hospitalization;
import Ospedale.Services.HospitalizationService;

/**
 *
 * @author luddy
 */
public class HospitalizationController {

    private final HospitalizationService hospitalizationService;

    public HospitalizationController(HospitalizationService hospitalizationService) {
        this.hospitalizationService = hospitalizationService;
    }

    public HospitalizationController(Storage storage) {
        this(new HospitalizationService(
                new HospitalizationRepository(storage),
                new UserRepository(storage)
        ));
    }

    public Response createHospitalization(CreateHospitalizationDTO dto) {
        try {
            Hospitalization hospitalization = hospitalizationService.createHospitalization(dto);
            return new Response("Hospitalization created: " + hospitalization.getId(), Status.CREATED);
        } catch (IllegalArgumentException e) {
            return new Response(e.getMessage(), Status.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new Response(e.getMessage(), Status.NOT_FOUND);
        }
    }
}
