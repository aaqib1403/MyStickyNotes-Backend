package com.project.MyStickyNotes.Services;


import com.project.MyStickyNotes.Converters.DetailsEntityDtoMapper;
import com.project.MyStickyNotes.Converters.registerentitydtomapper;
import com.project.MyStickyNotes.Dto.*;
import com.project.MyStickyNotes.Entities.DetailsEntity;
import com.project.MyStickyNotes.Entities.RegisterEntity;
import com.project.MyStickyNotes.Repositories.DetailsRepository;
import com.project.MyStickyNotes.Repositories.RegisterUsersRepository;
import com.project.MyStickyNotes.Util.DataResponse;
import com.project.MyStickyNotes.Util.InitialRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(value = "transactionManager")
public class DetailsService {

    @Autowired
    RegisterUsersRepository registerUsersRepository;
    @Autowired
    DetailsEntityDtoMapper detailsEntityDtoMapper;
    @Autowired
    public DetailsRepository detailsRepository;
    @Autowired
    RegisterService registerService;
    @Autowired
    registerentitydtomapper registerentitydto;
    @PersistenceContext
    EntityManager getEntityManager;


//
//    public List<DetailsDto> fetchByUsername(String username) {
//
//        //return detailsEntityDtoMapper.detailsEntityTodetailsDto(registerentitydto.registerEntityToregisterDto(registerUsersRepository.findAllByUsername(username)));
//
//    	List<DetailsEntity> list = detailsRepository.findAllByRegisterEntity(username);
//    	return detailsEntityDtoMapper.detailsEntityTodetailsDto(list);
//    }


    public RegisterDto fetchByUsername(String username) {

        //return detailsEntityDtoMapper.detailsEntityTodetailsDto(registerentitydto.registerEntityToregisterDto(registerUsersRepository.findAllByUsername(username)));

        //List<DetailsEntity> list = detailsRepository.findAllDetailsByUsername(username);
        return registerentitydto.registerEntityToregisterDto(registerUsersRepository.findDetailsByUsername(username));

    }


    public List<DetailsDto>     fetchByUsernameAndDates(UsernameAndDates usernameAndDates,
                                                    int pageNumber, int pageSize,
                                                    InitialRequestResponse initialRequestResponse,
                                                    DataResponse dataResponse) {
        if (initialRequestResponse != null) {
            String pattern = "yyyy-MM-dd";
            DateFormat df = new SimpleDateFormat(pattern);
            if (usernameAndDates.getFromDate() == null) {
                usernameAndDates.setFromDate(df.format(new Date()));
            }
            if (usernameAndDates.getToDate() == null) {
                usernameAndDates.setToDate(usernameAndDates.getFromDate());
            }
        }

        TypedQuery<DetailsEntity> query = null;

        if (usernameAndDates.getStatus() == null) {
            if (initialRequestResponse != null) {
                query = getEntityManager.createQuery("\n" +
                        " from DetailsEntity d where d.registerEntity.username = '" + usernameAndDates.getUsername() + "' and (d.createdon >= '" + usernameAndDates.getFromDate() + "' AND\n" +
                        "d.createdon  <= '" + usernameAndDates.getToDate() + "')", DetailsEntity.class);

            }

        }
        if (usernameAndDates.getStatus() == null) {
            if (initialRequestResponse == null) {
                query = getEntityManager.createQuery("\n" +
                        " from DetailsEntity d where d.registerEntity.username = '" + usernameAndDates.getUsername() + "' and (d.createdon >= '" + usernameAndDates.getFromDate() + "' AND\n" +
                        "d.createdon  <= '" + usernameAndDates.getToDate() + "')", DetailsEntity.class);

            }

        }
        if (usernameAndDates.getStatus() == null) {
            if (initialRequestResponse == null && usernameAndDates.getFromDate()==null) {
                query = getEntityManager.createQuery("\n" +
                        " from DetailsEntity where registerEntity.username = '" + usernameAndDates.getUsername() + "'", DetailsEntity.class);
            }
        }


        if (usernameAndDates.getStatus() != null && usernameAndDates.getFromDate() != null) {
            query = getEntityManager.createQuery("\n" +
                    " from DetailsEntity d where d.registerEntity.username = '" + usernameAndDates.getUsername() + "' and d.status = '" + usernameAndDates.getStatus() + "' and (d.createdon >= '" + usernameAndDates.getFromDate() + "' AND\n" +
                    "d.createdon  <= '" + usernameAndDates.getToDate() + "')", DetailsEntity.class);
        }


        if (usernameAndDates.getStatus() != null && usernameAndDates.getFromDate() == null) {
            query = getEntityManager.createQuery("\n" +
                    " from DetailsEntity d where d.registerEntity.username = '" + usernameAndDates.getUsername() + "' and d.status = '" + usernameAndDates.getStatus() + "'", DetailsEntity.class);
        }


       int totalrecords = query.getResultList().size();
        if(initialRequestResponse!=null){
            initialRequestResponse.setTotalsize(totalrecords);
        }
        if(dataResponse!=null){
            dataResponse.setTotalsize(totalrecords);
        }

        query.setFirstResult((pageNumber -1 ) * pageSize);
        query.setMaxResults(pageSize);


        List<DetailsEntity> li = query.getResultList();

        return detailsEntityDtoMapper.detailsEntityTodetailsDto(li);


    }


    public int countRows(TypedQuery<DetailsEntity> query){
        return query.getResultList().size();
    }

    public int deleteById(Deletedto deletedto) {

        int deletedrows = detailsRepository.deleteById(deletedto.getId());
        return deletedrows;

    }


    public int markById(MarkByDto markByDto) {

        int deletedrows = detailsRepository.markById(markByDto);
        return deletedrows;

    }



    public int editById(EditDto editDto) {
        return detailsRepository.editById(editDto);

    }

    public Boolean saveDetails(DetailsDto detailsDto, String username) {

        DetailsEntity d = detailsEntityDtoMapper.detailsDtoTodetailsEntity(detailsDto);

        RegisterEntity r = registerService.fetchUser(username);
        if (r != null) {
            d.setRegisterEntity(r);
            if (detailsRepository.save(d) != null) {


                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

}
