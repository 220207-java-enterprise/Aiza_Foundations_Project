//package com.revature.foundations.services;

import com.revature.foundations.daos.UserDAO;
import com.revature.foundations.dtos.requests.NewUserRequest;
import com.revature.foundations.dtos.responses.AppUserResponse;
import com.revature.foundations.models.ErsUser;
import com.revature.foundations.models.ErsUserRoles;
import com.revature.foundations.util.exceptions.InvalidRequestException;
import com.revature.foundations.util.exceptions.ResourceConflictException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//public class ReimbService {


//    ------------------------------------------------------------------------------------------
    // TODO start
//    private ErsReimbursementsDAO  ersRDAO;
//
//    public ReimbService(ErsReimbursementsDAO ersRDAO) {
//        this.ersRDAO = ersRDAO;
//    }
//    public List<AppUserResponse> getAllReimb() {
//        return ersRDAO.getAll()
//                .stream()
//                .map(AppUserResponse::new) // intermediate operation//TODO ????
//                .collect(Collectors.toList()); // terminal operation
//    }
//    public ErsUser reimbInput(NewReimbRequest newReimbRequest) {
//
//        ErsReimbursements newReimb = newReimbRequest.extractUser();
//
//
//    }
//}
