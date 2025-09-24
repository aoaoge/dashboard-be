package org.example.librarybe.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.example.librarybe.common.Result;
import org.example.librarybe.controller.request.BaseRequest;
import org.example.librarybe.controller.request.CategroteListRequest;
import org.example.librarybe.entity.Categrote;
import org.example.librarybe.impl.CategroteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("categrote")
public class CategroteController {

    @Autowired
    CategroteService categroteService;

    @PostMapping("add")
    public Result addCategrote(@RequestBody Categrote categrote) {
        categrote.setUpdateTime(LocalDate.from(LocalDateTime.now()));
        categroteService.addCategrote(categrote);
        return Result.success("新增成功");
    }

    @GetMapping("delete")
    public Result deleteCategrote(Integer id) {
        categroteService.deleteCategrote(id);
        return Result.success("删除成功");
    }

    @PostMapping("update")
    public Result updateCategrote(@RequestBody Categrote categrote) {
        categroteService.updateCategrote(categrote);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Categrote categrote = categroteService.getById(id);
        return Result.success(categrote);
    }

    @GetMapping("list")
    public Result getList(CategroteListRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Categrote> data = categroteService.getList(baseRequest);
        return Result.success(data);
    }

}
