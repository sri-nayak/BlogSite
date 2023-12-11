package com.prodevans.BlogSite.controller;


import com.prodevans.BlogSite.exception.DataIsNotFoundException;
import com.prodevans.BlogSite.model.TechStack;
import com.prodevans.BlogSite.service.TechStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/tools")
@CrossOrigin("*")

public class TechStackController {
    private TechStackService techStackService;
    @Autowired
    public TechStackController(TechStackService techStackService) {
        this.techStackService = techStackService;
    }
    @GetMapping("/getTool/{name}")
    public ResponseEntity getTools(@PathVariable String name){
        try {
            return new ResponseEntity(techStackService.getTechVersion(name),HttpStatus.FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    /**
     *
     * @return ResponseEntity
     *
     * it will throw Exception if no data found
     *  or else it will return  a List of data
     */
    @GetMapping("/getAll")
    public ResponseEntity getAllTools(){
        try {
            return new ResponseEntity(techStackService.getAllTechList(),HttpStatus.FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    /**
     *
     * @param techStack
     * @return ResponseEntity
     * if the requested data is already there it will throw will exception
     */
    @PostMapping("/setTool")
    public ResponseEntity saveTools(@RequestBody TechStack techStack){
        try {
            techStackService.addTech(techStack);
            return new ResponseEntity(techStackService.addTech(techStack),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    /**
     *
     * @param techStack
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity updateDetail(@RequestBody TechStack techStack){
        try {
            TechStack techStack1=techStackService.updateTechStack(techStack);
            return  new ResponseEntity(techStack1,HttpStatus.ACCEPTED);
        }catch (DataIsNotFoundException d){
            return new ResponseEntity(d.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
