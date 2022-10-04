package com.glq1218.controller;

import com.glq1218.domain.ResponseResult;
import com.glq1218.domain.dto.LinkListDto;
import com.glq1218.domain.dto.TagListDto;
import com.glq1218.domain.vo.PageVo;
import com.glq1218.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content/link")
public class LinkController {
    @Autowired
    private LinkService linkService;
    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, LinkListDto linkListDto){
        return linkService.pageLinkList(pageNum,pageSize,linkListDto);
    }
}
