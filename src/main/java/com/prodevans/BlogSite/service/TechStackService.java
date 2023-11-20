package com.prodevans.BlogSite.service;

import com.prodevans.BlogSite.exception.CustomException;
import com.prodevans.BlogSite.exception.DataIsNotFoundException;
import com.prodevans.BlogSite.model.TechStack;

import java.util.List;

public interface TechStackService {
    public TechStack addTech(TechStack techStack) throws CustomException;
    public TechStack updateTechStack(TechStack techStack) throws DataIsNotFoundException;
    public boolean deleteTechStack(TechStack techStack);
    TechStack getTechStack(TechStack techStack) ;

    List<TechStack> getTechVersion(String name) throws Exception;

    public List<TechStack> getAllTechList();
}
