package t1708m.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t1708m.hellospring.service.HeroService;
import t1708m.hellospring.entity.Hero;
import t1708m.hellospring.repository.HeroRepository;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/heroes")
public class HeroController {

    @Autowired
    HeroService heroService;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<List<Hero>> search(@RequestParam String name) {
        List<Hero> heroList = heroService.search(name);
        return new ResponseEntity<>(heroList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Hero>> list() {
        List<Hero> heroList = heroService.getListHero();
        return new ResponseEntity<>(heroList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Hero> detail(@PathVariable long id) {
        Hero hero = heroService.findById(id);
        if (hero == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(hero, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Hero> store(@RequestBody Hero obj) {
        try {
            Hero createdHero = heroService.createNew(obj);
            return new ResponseEntity<>(createdHero, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Hero> update(@PathVariable long id, @RequestBody Hero obj) {
        Hero existHero = heroService.findById(id);
        if (existHero == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            existHero.setName(obj.getName());
            existHero.setDescription(obj.getDescription());
            existHero.setHistory(obj.getHistory());
            existHero.setStatus(obj.getStatus());
            heroService.updateHero(existHero);
            return new ResponseEntity<>(existHero, HttpStatus.OK);
        }
    }
}
