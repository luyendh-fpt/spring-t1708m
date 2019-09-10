package t1708m.hellospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t1708m.hellospring.entity.Hero;
import t1708m.hellospring.repository.HeroRepository;

import java.util.Calendar;
import java.util.List;

@Service
public class HeroService {

    @Autowired
    HeroRepository heroRepository;

    public List<Hero> search(String name){
        return heroRepository.findAllByNameAndStatus(name, 1);
    }

    public Hero createNew(Hero hero) {
        hero.setId(Calendar.getInstance().getTimeInMillis());
        heroRepository.save(hero);
        return hero;
    }

    public Hero updateHero(Hero hero) {
        heroRepository.save(hero);
        return hero;
    }

    public List<Hero> getListHero() {
        return heroRepository.findAll();
    }

    public Hero findById(long id) {
        return heroRepository.findById(id).orElse(null);
    }
}
