package com.prodevans.BlogSite.Repository;

import com.prodevans.BlogSite.model.DocsFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocsFileRepository extends JpaRepository<DocsFile,Integer> {
    public List findByFileName(String name);
}
