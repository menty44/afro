package com.blaqueyard.kichap.repository;

/**
 * Created by admin on 5/27/18.
 */

import com.blaqueyard.kichap.model.Incomingsmscallback;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Fredrick Oluoch
 *         http://www.blaqueyard.com
 *         0720106420 | 0722508906
 *         email: menty44@gmail.com
 */

public interface SmsRepository extends JpaRepository<Incomingsmscallback, Long> {


}
