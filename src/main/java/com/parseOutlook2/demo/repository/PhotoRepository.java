package com.parseOutlook2.demo.repository;

import com.parseOutlook2.demo.model.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {
    Photo findPhotoByName(String name);
}
