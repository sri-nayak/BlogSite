package com.prodevans.BlogSite.Repository;


import com.prodevans.BlogSite.model.Event;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface EventRepository extends JpaRepository<Event,Integer> {
    @Query(value = "select event_id ,description,topic_name,event_date from event;",nativeQuery = true)
    public List<Event> getAllEvent();
    public List<Event> findByEventDateGreaterThanEqual(Date date);
}
