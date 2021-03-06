package com.we.weblog.mapping;


import com.we.weblog.domain.Comment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    @Select({"select cid,created,author,email,content from t_comments"})
    List<Comment> selectAllComments();

    @Select({"select count(*) from t_comments"})
    int getNumberOfComment();

    @Select({"select article_id,author,content,created from t_comments where article_id = #{uid} "})
    List<Comment> getArticleById(@Param("uid") int uid);

    @Insert({"insert into t_comments (article_id,created,author,email,ip,content,parent) "+
    "values (#{c.article_id},#{c.created},#{c.author},#{c.email},#{c.ip},#{c.content},#{c.parent})"})
    int insertComment(@Param("c") Comment comment);


    @Delete({"delete from t_comments where cid = #{id}"})
    int deleteCommentById(@Param("id")Integer cid);


    @Select({"select * from t_comments where cid = #{c}"})
    Comment selectCommentById(@Param("c") Integer cid);

}
