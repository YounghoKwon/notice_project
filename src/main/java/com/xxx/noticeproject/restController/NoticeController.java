package com.xxx.noticeproject.restController;

import com.sun.istack.NotNull;
import com.xxx.noticeproject.dto.NoticeDto;
import com.xxx.noticeproject.entity.Notice;
import com.xxx.noticeproject.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<NoticeDto.Notice>> list(@PageableDefault(size=20, sort="title",direction = Sort.Direction.ASC) Pageable pageable,  @RequestParam(value = "search_text") String searchText){
        return new ResponseEntity<>(noticeService.getNoticeList(searchText,pageable), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<NoticeDto.Notice> saveNotice(@Valid @RequestBody Notice notice){

        return new ResponseEntity<>( noticeService.modifyNotice(notice), HttpStatus.OK);
    }

    @PatchMapping(value = "/")
    public ResponseEntity<NoticeDto.Notice> modifyNotice(@Valid @RequestBody Notice notice){
        noticeService.modifyNotice(notice);
        return new ResponseEntity<>( noticeService.modifyNotice(notice), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteNotice(@NotNull @PathVariable Long id){
        noticeService.deleteNotice(id);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<NoticeDto.Notice> getNotice(@NotNull @PathVariable Long id){
        return new ResponseEntity<>(noticeService.getNotice(id), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestPart("fields") MultipartFile file) {

        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
