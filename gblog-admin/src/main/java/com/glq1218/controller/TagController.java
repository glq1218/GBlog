package com.glq1218.controller;

import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.dto.TagListDto;
import com.glq1218.domain.entity.Tag;
import com.glq1218.domain.vo.PageVo;
import com.glq1218.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        return tagService.pageTagList(pageNum, pageSize, tagListDto);
    }

    @PostMapping
    public ResponseResult<?> add(@RequestBody Tag tag) {
        return tagService.add(tag);
    }

    @DeleteMapping("/{ids}")
    public ResponseResult<?> delete(@PathVariable("ids") List<Long> ids){
        return tagService.delete(ids);
    }

    @GetMapping("/{id}")
    public ResponseResult<Tag> get(@PathVariable("id") Long id){
        return tagService.get(id);
    }

    @PutMapping()
    public ResponseResult<?> edit(@RequestBody Tag tag){
        return tagService.edit(tag);
    }

    @GetMapping("listAllTag")
    public ResponseResult<List<Tag>> listAllTag(){
        return tagService.listAllTag();
    }
}
