package org.example.librarybe.impl;

import org.example.librarybe.controller.request.BaseRequest;
import org.example.librarybe.controller.request.CategroteListRequest;
import org.example.librarybe.entity.Categrote;
import org.example.librarybe.mapper.CategroteMapper;
import org.example.librarybe.service.ICateroteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategroteService implements ICateroteService {

    @Autowired
    CategroteMapper categroteMapper;

    @Override
    public void addCategrote(Categrote categrote) {
        categroteMapper.addCategrote(categrote);
    }

    @Override
    public void deleteCategrote(Integer id) {
        categroteMapper.deleteCategrote(id);
    }

    @Override
    public void updateCategrote(Categrote categrote) {
        categroteMapper.updateCategrote(categrote);
    }

    @Override
    public Categrote getById(Integer id) {
        return categroteMapper.getById(id);
    }

    @Override
    public List<Categrote> getList(CategroteListRequest baseRequest) {
        return categroteMapper.getList(baseRequest);
    }
}
