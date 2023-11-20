package com.prodevans.BlogSite.service.impl;

import com.prodevans.BlogSite.Repository.TechStackReposiotry;
import com.prodevans.BlogSite.exception.CustomException;
import com.prodevans.BlogSite.exception.DataIsNotFoundException;
import com.prodevans.BlogSite.model.TechStack;
import com.prodevans.BlogSite.service.FileUploadService;
import com.prodevans.BlogSite.service.TechStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechStackServiceImpl implements TechStackService {
    private TechStackReposiotry techStackReposiotry;
    private FileUploadService fileUploadService;

    @Autowired
    public TechStackServiceImpl(TechStackReposiotry techStackReposiotry, FileUploadServiceImpl fileUploadService) {
        this.techStackReposiotry = techStackReposiotry;
        this.fileUploadService = fileUploadService;
    }

    /**
     * @param techStack
     * @return
     */
    @Override
    public TechStack addTech(TechStack techStack) throws CustomException {
        try {
            techStackReposiotry.findAll().stream()
                    .filter(techStack1 -> techStack.getToolName().equalsIgnoreCase(techStack1.getToolName()) && techStack1.getVersion().equalsIgnoreCase(techStack1.getVersion()))
                    .findFirst().orElseThrow(() -> new CustomException("Data Already Exist"));
            return techStackReposiotry.save(techStack);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "" + e.getStackTrace());
            throw new CustomException(e.getMessage());
        }
    }

    /**
     * @param techStack
     * @return techStack
     * @throws DataIsNotFoundException
     */
    @Override
    public TechStack updateTechStack(TechStack techStack) throws DataIsNotFoundException {
        TechStack techStacK = techStackReposiotry.findById(techStack.getStackId()).orElseThrow(() -> new DataIsNotFoundException("Data Is not present"));
        return techStacK;
    }

    /**
     * @param techStack
     * @return
     */
    @Override
    public boolean deleteTechStack(TechStack techStack) {
        techStackReposiotry.delete(techStack);
        return true;
    }

    /**
     * @param techStack
     * @return
     */
    @Override
    public TechStack getTechStack(TechStack techStack) {
        List<TechStack> techStackList = techStackReposiotry.findAll().stream().
                filter(techStack1 -> techStack.getToolName().equalsIgnoreCase(techStack1.getToolName())
                        ||
                        techStack1.getVersion().equalsIgnoreCase(techStack1.getVersion()))
                .findFirst().stream().toList();
        try {
            if (techStackList.isEmpty()) {
                return addTech(techStack);
            } else {
                return techStackList.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error Raised due to Database Transaction" + e.getStackTrace());
            return null;
        }
    }

    /**
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public List<TechStack> getTechVersion(String name) throws Exception {
        List<TechStack> techStackList = techStackReposiotry.findAll().stream().filter(techStack -> techStack.getToolName().equalsIgnoreCase(name)).toList();
        if (techStackList.isEmpty()) {
            throw new Exception("Data Not found");
        } else return techStackList;
    }

    /**
     * @return
     */
    @Override
    public List<TechStack> getAllTechList() {
        return techStackReposiotry.findAll().stream().toList();
    }
}
