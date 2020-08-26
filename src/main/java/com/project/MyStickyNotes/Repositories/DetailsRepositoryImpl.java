package com.project.MyStickyNotes.Repositories;

import com.project.MyStickyNotes.Dto.DetailsDto;
import com.project.MyStickyNotes.Dto.EditDto;
import com.project.MyStickyNotes.Dto.MarkByDto;
import com.project.MyStickyNotes.Entities.DetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class DetailsRepositoryImpl implements DetailsRepositoryCustom {

    @PersistenceContext
    EntityManager getEntityManager;

    @Override
    @Transactional(value = "transactionManager")
    public int deleteById(int[] id) {
        CriteriaBuilder cb = getEntityManager.getCriteriaBuilder();

        CriteriaDelete<DetailsEntity> criteriaQuery = cb.createCriteriaDelete(DetailsEntity.class);
        List<Integer> idList = Arrays.stream(id).boxed().collect(Collectors.toList());
        Root<DetailsEntity> root = criteriaQuery.from(DetailsEntity.class);
        criteriaQuery.where(root.get("id").in(idList));


        return getEntityManager.createQuery(criteriaQuery).executeUpdate();


    }

    @Override
    public int editById(EditDto editDto) {
        CriteriaBuilder cb = getEntityManager.getCriteriaBuilder();
        CriteriaUpdate<DetailsEntity> criteriaUpdate = cb.createCriteriaUpdate(DetailsEntity.class);
        Root<DetailsEntity> root = criteriaUpdate.from(DetailsEntity.class);
        criteriaUpdate.set("taskdetails", editDto.getTaskdetails());
        criteriaUpdate.set("status", editDto.getStatus());
        criteriaUpdate.where(cb.equal(root.get("id"), editDto.getId()));

        return getEntityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    @Override
    public int markById(MarkByDto markByDto) {
        CriteriaBuilder cb = getEntityManager.getCriteriaBuilder();
        CriteriaUpdate<DetailsEntity> criteriaUpdate = cb.createCriteriaUpdate(DetailsEntity.class);
        Root<DetailsEntity> root = criteriaUpdate.from(DetailsEntity.class);
        List<Integer> idList = Arrays.stream(markByDto.getId()).boxed().collect(Collectors.toList());
        criteriaUpdate.set("status", markByDto.getCheckedStatus());
//        for(int i= 0 ; i< markByDto.getId().length;i++){
//            criteriaUpdate.where(cb.equal(root.get("id"),markByDto.getId()[i]));
//            getEntityManager.createQuery(criteriaUpdate).executeUpdate();
//        }


        criteriaUpdate.where(root.get("id").in(idList));


        return getEntityManager.createQuery(criteriaUpdate).executeUpdate();

    }


}
