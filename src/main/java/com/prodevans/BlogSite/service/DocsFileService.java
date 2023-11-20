package com.prodevans.BlogSite.service;

import com.prodevans.BlogSite.exception.CustomException;
import com.prodevans.BlogSite.model.DocsFile;

import java.util.List;

public interface DocsFileService {
    public DocsFile saveFile(DocsFile docsFile) throws Exception;
    public DocsFile updateFile(DocsFile docsFile) throws CustomException;
    public DocsFile deleteFile(DocsFile docsFile);
    public List<DocsFile> getAllFiles();
}
