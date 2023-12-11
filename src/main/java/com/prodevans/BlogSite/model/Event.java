package com.prodevans.BlogSite.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer eventId;
    private String topicName;
    private String description;
    @JsonFormat(pattern="dd/MM/yy")
    private Date eventDate;
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    private Set<Users> partiName;
    @ManyToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH},fetch = FetchType.EAGER)
    private Set<TechStack> tools;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<DocsFile> docsFileList;
}
