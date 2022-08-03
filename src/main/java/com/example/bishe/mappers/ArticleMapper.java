package com.example.bishe.mappers;

import com.example.bishe.domain.Article;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface ArticleMapper {
    public List<Article> selectAllArticle();
    public int deleteArticle(Integer id);
    public int insertArticle(Article article);
    public int updataArticle(Article article);
    public Article selectById(Integer id);
    public List<Article> selectByTypeCode(String typeCode);
}
