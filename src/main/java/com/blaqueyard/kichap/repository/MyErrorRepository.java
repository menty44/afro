package com.blaqueyard.kichap.repository;/**
 * Created by admin on 5/27/18.
 */

import com.blaqueyard.kichap.model.MyError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Fredrick Oluoch
 * http://www.blaqueyard.com
 * 0720106420 | 0722508906
 * email: menty44@gmail.com
 */

@Repository
public interface MyErrorRepository extends JpaRepository<MyError, Long> {
}
