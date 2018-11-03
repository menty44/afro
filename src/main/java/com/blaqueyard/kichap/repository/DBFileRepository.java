package com.blaqueyard.kichap.repository;


/**
 * Created by admin on 11/3/18.
 */

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */


import com.blaqueyard.kichap.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, Long> {
//public interface DBFileRepository extends JpaRepository<DBFile, String> {

}
