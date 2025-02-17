package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("languages")
public class Languages {
    private List<Language> languages = new ArrayList<>(){{
        add(new Language("Java"));
        add(new Language("C#"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language){
        this.languages.add(language);
        return language;
    }

    @GetMapping
    public List<Language> getAll(){
        return languages;
    }

    @GetMapping("/{name}")
    public Language getLanguage(@PathVariable String name){
        for (Language language : languages){
            if (language.getName().equalsIgnoreCase(name)){
                return language;
            }
        }
        return null;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateLanguage(@PathVariable String name, @RequestBody Language language) {
        for (Language updatedlanguage : languages) {
            if (name.equalsIgnoreCase(updatedlanguage.getName())) {
                updatedlanguage.setName(language.getName());
                return updatedlanguage;
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    public Language deleteLanguage(@PathVariable String name){
        for (Language language : languages){
            if (language.getName().equals(name)){
                int i = languages.indexOf(language);
                return languages.remove(i);
            }
        }
        return null;
    }

}
