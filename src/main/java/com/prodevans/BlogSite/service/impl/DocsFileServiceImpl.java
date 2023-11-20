package com.prodevans.BlogSite.service.impl;

import com.prodevans.BlogSite.Repository.DocsFileRepository;
import com.prodevans.BlogSite.exception.CustomException;
import com.prodevans.BlogSite.model.DocsFile;
import com.prodevans.BlogSite.service.DocsFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocsFileServiceImpl implements DocsFileService {
    private DocsFileRepository docsFileRepository;

    @Autowired
    public DocsFileServiceImpl(DocsFileRepository docsFileRepository) {
        this.docsFileRepository = docsFileRepository;
    }

    /**
     * @param docsFile
     * @return
     * @throws Exception
     */
    @Override
    public DocsFile saveFile(DocsFile docsFile) throws Exception {
        try {
            return docsFileRepository.save(docsFile);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    /**
     * @param docsFile
     * @return
     * @throws CustomException
     */
    @Override
    public DocsFile updateFile(DocsFile docsFile) throws CustomException {
        DocsFile docsFile1 = docsFileRepository.findById(docsFile.getId()).orElseThrow(() -> new CustomException("Data not present"));

        return null;
    }

    /**
     * @param docsFile
     * @return
     */
    @Override
    public DocsFile deleteFile(DocsFile docsFile) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<DocsFile> getAllFiles() {
        return null;
    }
}
