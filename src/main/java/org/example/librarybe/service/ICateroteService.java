package org.example.librarybe.service;

import org.example.librarybe.controller.request.BaseRequest;
import org.example.librarybe.controller.request.CategroteListRequest;
import org.example.librarybe.entity.Categrote;

import java.util.List;

public interface ICateroteService {
    void addCategrote(Categrote categrote);

    void deleteCategrote(Integer id);

    void updateCategrote(Categrote categrote);

    Categrote getById(Integer id);

    List<Categrote> getList(CategroteListRequest baseRequest);
}
