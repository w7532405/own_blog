package com.imnu.dao;

import com.imnu.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagDao {

    List<Tag> getBlogTag();

    List<Tag> getAllTag();

    Tag getTag(Long id);

    Tag getTagByName(String name);

    int saveTag(Tag tag);

    int updateTag(Tag tag);

    int deleteTag(Long id);

}
