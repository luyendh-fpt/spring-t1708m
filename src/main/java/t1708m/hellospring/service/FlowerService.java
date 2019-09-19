package t1708m.hellospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import t1708m.hellospring.entity.Flower;
import t1708m.hellospring.repository.FlowerRepository;

import java.util.Calendar;
import java.util.List;

@Service
public class FlowerService {
    @Autowired
    FlowerRepository flowerRepository;

    public Flower create(Flower flower) {
        return flowerRepository.save(flower);
    }

    public Page<Flower> getList(int page, int limit) {
        return flowerRepository.findAll(PageRequest.of(page - 1, limit));
    }

    public Flower getDetail(int id) {
        return flowerRepository.findById(id).orElse(null);
    }

    public Flower update(int id, Flower flower) {
        Flower existFlower = flowerRepository.findById(id).orElse(null);
        if (existFlower == null) {
            return null;
        }
        existFlower.setName(flower.getName());
        existFlower.setDescription(flower.getDescription());
        existFlower.setPrice(flower.getPrice());
        existFlower.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return flowerRepository.save(flower);
    }

    public boolean deleted(int id) {
        Flower existFlower = flowerRepository.findById(id).orElse(null);
        if (existFlower == null) {
            return false;
        }
        flowerRepository.delete(existFlower);
        return true;
    }
}
