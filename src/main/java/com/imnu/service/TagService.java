package com.imnu.service;

import com.imnu.pojo.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getBlogTag();

    List<Tag> getAllTag();

    Tag getTag(Long id);

    Tag getTagByName(String name);

    int saveTag(Tag tag);

    int updateTag(Tag tag);

    int deleteTag(Long id);

    List<Tag> getTagByString(String tagIds);
}
