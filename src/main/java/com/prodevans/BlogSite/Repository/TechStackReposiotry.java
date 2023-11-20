package com.prodevans.BlogSite.Repository;

import com.prodevans.BlogSite.model.TechStack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechStackReposiotry extends JpaRepository<TechStack,Integer> {
}
