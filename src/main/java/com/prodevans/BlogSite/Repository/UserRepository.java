package com.prodevans.BlogSite.Repository;

import com.prodevans.BlogSite.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    Users findByEmail(String name);
}
