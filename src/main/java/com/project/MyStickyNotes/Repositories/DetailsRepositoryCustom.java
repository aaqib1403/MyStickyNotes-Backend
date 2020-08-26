package com.project.MyStickyNotes.Repositories;

import com.project.MyStickyNotes.Dto.EditDto;
import com.project.MyStickyNotes.Dto.MarkByDto;
import com.project.MyStickyNotes.Entities.DetailsEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;


public interface DetailsRepositoryCustom {

     int deleteById(int[] id);
     int editById(EditDto editDto);

     int markById(MarkByDto markByDto);


}
